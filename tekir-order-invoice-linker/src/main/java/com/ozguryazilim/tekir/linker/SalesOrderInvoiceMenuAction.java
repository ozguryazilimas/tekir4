package com.ozguryazilim.tekir.linker;

import com.ozguryazilim.tekir.entities.InvoiceItem;
import com.ozguryazilim.tekir.entities.OrderItem;
import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.invoice.sales.SalesInvoiceHome;
import com.ozguryazilim.tekir.order.sales.SalesOrderHome;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.utils.VoucherItemUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ozguryazilim.telve.messages.Messages;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 *
 * @author oyas
 */
@Named
@RequestScoped
public class SalesOrderInvoiceMenuAction implements Serializable {
    
    @Inject
    private SalesOrderHome orderHome;

    @Inject
    private SalesInvoiceHome invoiceHome;

    @Inject
    private VoucherMatcherService matcherService;

    public Class<? extends ViewConfig> newInvoice() {

        //Once yeni kayıt oluşturtalım
        Class<? extends ViewConfig> result = invoiceHome.create();

        SalesOrder entity = (SalesOrder) orderHome.getEntity();

        //Account
        invoiceHome.getEntity().setAccount(entity.getAccount());

        //Nereden üretildiğinin linkini koyalım
        FeaturePointer fp = orderHome.getFeaturePointer();

        invoiceHome.getEntity().setStarter(fp);

        //invoiceHome.setMatchable(matcherService.findMatchableByFeature(fp));
        //Process bilgisi v.s.
        invoiceHome.getEntity().setTopic(entity.getTopic());
        invoiceHome.getEntity().setProcess(entity.getProcess());
        invoiceHome.getEntity().setGroup(entity.getGroup());
        invoiceHome.getEntity().setPaymentPlan(entity.getPaymentPlan());
        invoiceHome.getEntity().setShippingDate(entity.getShippingDate());
        invoiceHome.getEntity().setShippingNote(entity.getShippingNote());

        //Detayları dolduralım
        for (OrderItem qi : entity.getItems()) {
            InvoiceItem oi = new InvoiceItem();
            VoucherItemUtils.copyCommodityItem(qi, oi);
            oi.setMaster(invoiceHome.getEntity());
            invoiceHome.getEntity().getItems().add(oi);
        }

        invoiceHome.calculateSummaries();

        FacesMessages.info("orderInvoice.messages.OrderSuccessful", Messages.getMessage("orderInvoice.messages.invoice.CREATE", entity.getVoucherNo(), entity.getTopic()));
        return result;
    }
}
