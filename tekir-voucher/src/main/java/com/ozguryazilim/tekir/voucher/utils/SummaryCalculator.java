package com.ozguryazilim.tekir.voucher.utils;

import com.ozguryazilim.tekir.entities.TaxDefinition;
import com.ozguryazilim.tekir.entities.TaxType;
import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;
import com.ozguryazilim.tekir.entities.VoucherSummaryBase;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author oyas
 */
public class SummaryCalculator<E extends VoucherBase, I extends VoucherCommodityItemBase, S extends VoucherSummaryBase> {

    public void calcSummaries(Supplier<E> entitySupplier,
            Supplier<List<I>> itemsSupplier,
            Supplier<Map<String, S>> summariesSupplier,
            Supplier<S> newSummarySupplier,
            Consumer<BigDecimal> totalConsumer,
            boolean calcWithHolding) {

        Map<String, S> summaries = summariesSupplier.get();
        List<I> items = itemsSupplier.get();
        E entity = entitySupplier.get();

        summaries.clear();

        //Burayı nasıl yapacağız?
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalTax = BigDecimal.ZERO;

        //Tevkifat işini nasıl hesaplayacağız.
        for (VoucherCommodityItemBase it : items) {

            List<TaxDefinition> taxs = getOrderedTaxList(it.getCommodity().getTax1(), it.getCommodity().getTax2(), it.getCommodity().getTax3(), calcWithHolding);

            //KDV Matrahı ( KDV hariç önceki vergilerin de toplanması.
            BigDecimal vatTotal = BigDecimal.ZERO.add(it.getLineTotal());
            //Hesaplanacak olan KDV
            BigDecimal vatAmt = BigDecimal.ZERO;
            BigDecimal hundred = new BigDecimal(100);
            BigDecimal itemTotalTax = BigDecimal.ZERO;

            //TODO: KDV Matrah lazım, KDV Tevkifat laxım.
            for (TaxDefinition tax : taxs) {
                BigDecimal taxAmt = BigDecimal.ZERO;
                
                if (tax.getType() != TaxType.KDV && tax.getType() != TaxType.TEVKIFAT) {
                    //IOV/OTV v.b. vergileri hesaplayalım.
                    taxAmt = it.getLineTotal().multiply(tax.getRate().divide(hundred));
                    itemTotalTax = itemTotalTax.add(taxAmt);

                    //Satır toplamının üzerine hesaplanan vergiyi de ekleyelim.
                    vatTotal = vatTotal.add(taxAmt);
                } else if (tax.getType() == TaxType.KDV) {
                    //KDV Matrah üzerinden KDV hesaplayalım
                    taxAmt = vatTotal.multiply(tax.getRate().divide(hundred));
                    itemTotalTax = itemTotalTax.add(taxAmt);
                    vatAmt = vatAmt.add(taxAmt);

                } else if (tax.getType() == TaxType.TEVKIFAT ) {
                    //KDV Tevkifatını hesaplayalım
                    taxAmt = vatAmt.multiply(tax.getRate().divide(hundred));
                    itemTotalTax = itemTotalTax.subtract(taxAmt);
                }

                S sm = summaries.get("Tax." + tax.getCode());
                if (sm == null) {
                    sm = newSummarySupplier.get();
                    sm.setRowKey("Tax." + tax.getCode());
                    sm.setInfo(tax.getName());
                    sm.setMaster(entity);
                    sm.setAmount(taxAmt);
                    summaries.put(sm.getRowKey(), sm);
                } else {
                    sm.setAmount(sm.getAmount().add(taxAmt));
                }
            }

            it.setTaxTotal(itemTotalTax);
            totalTax = totalTax.add(itemTotalTax);
        }

        //Saklamadan hemen önce toplam tutarı hesaplayıp fiş üzerine yazalım.
        Function<I, BigDecimal> totalMapper = item -> item.getTotal();
        total = items.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

        Function<I, BigDecimal> discMapper = item -> item.getDiscount();
        BigDecimal discount = items.stream().map(discMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

        Function<I, BigDecimal> lineTotMapper = item -> item.getLineTotal();
        BigDecimal lineTot = items.stream().map(lineTotMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

        S sm = newSummarySupplier.get();
        sm.setRowKey("TaxTotal");
        sm.setMaster(entity);
        sm.setAmount(totalTax);
        summaries.put(sm.getRowKey(), sm);

        sm = newSummarySupplier.get();
        sm.setRowKey("Discount");
        sm.setMaster(entity);
        sm.setAmount(discount);
        summaries.put(sm.getRowKey(), sm);

        sm = newSummarySupplier.get();
        sm.setRowKey("DiscountRate");
        sm.setMaster(entity);
        if (total.equals(BigDecimal.ZERO)){
            sm.setAmount(BigDecimal.ZERO);
        }
        else{
            sm.setAmount(discount.multiply(BigDecimal.valueOf(100)).divide(total, MathContext.DECIMAL32));
        }
        summaries.put(sm.getRowKey(), sm);

        sm = newSummarySupplier.get();
        sm.setRowKey("BeforeDiscountTotal");
        sm.setMaster(entity);
        sm.setAmount(total);
        summaries.put(sm.getRowKey(), sm);

        sm = newSummarySupplier.get();
        sm.setRowKey("BeforeTaxTotal");
        sm.setMaster(entity);
        sm.setAmount(lineTot);
        summaries.put(sm.getRowKey(), sm);

        //Genel Toplam
        sm = newSummarySupplier.get();
        sm.setRowKey("GrandTotal");
        sm.setMaster(entity);
        sm.setAmount(lineTot.add(totalTax));
        summaries.put(sm.getRowKey(), sm);

        //Sonuç toplamı da geri döndürelim.
        totalConsumer.accept(lineTot.add(totalTax));
    }

    /**
     * Sıralanmış bir şekilde vergi tür listesi
     *
     * @param tax1
     * @param tax2
     * @param tax3
     * @return
     */
    private List<TaxDefinition> getOrderedTaxList(TaxDefinition tax1, TaxDefinition tax2, TaxDefinition tax3, boolean calcWithHolding) {
        List<TaxDefinition> result = new ArrayList<>();

        if (tax1 != null) {
            if( tax1.getType() != TaxType.TEVKIFAT || ( tax1.getType() == TaxType.TEVKIFAT && calcWithHolding )){
                result.add(tax1);
            }
        }

        if (tax2 != null) {
            if( tax2.getType() != TaxType.TEVKIFAT || ( tax2.getType() == TaxType.TEVKIFAT && calcWithHolding )){
                result.add(tax2);
            }
        }

        if (tax3 != null) {
            if( tax3.getType() != TaxType.TEVKIFAT || ( tax3.getType() == TaxType.TEVKIFAT && calcWithHolding )){
                result.add(tax3);
            }
        }

        result.sort(new Comparator<TaxDefinition>() {
            @Override
            public int compare(TaxDefinition t1, TaxDefinition t2) {

                Integer t1Point = getTaxTypePoint(t1.getType());
                Integer t2Point = getTaxTypePoint(t2.getType());

                return t1Point.compareTo(t2Point);
            }
        });

        return result;
    }

    /**
     * Veri türüne göre sıralama puanı
     *
     * @param tt
     * @return
     */
    private int getTaxTypePoint(TaxType tt) {

        switch (tt) {
            case KDV:
                return 2;
            case TEVKIFAT:
                return 3;
            default:
                return 1;
        }
    }
}
