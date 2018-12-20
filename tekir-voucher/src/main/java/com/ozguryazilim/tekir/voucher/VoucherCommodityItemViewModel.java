package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.tekir.entities.Quantity;
import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author oyas
 */
public class VoucherCommodityItemViewModel<E extends VoucherBase> implements ViewModel, Serializable{

    private Long id;
    private E master;
    private Commodity commodity;
    private String info;
    private Quantity quantity;
    private BigDecimal price = BigDecimal.ZERO;
    private Integer discountRate = 0;
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal lineTotal = BigDecimal.ZERO;

    public VoucherCommodityItemViewModel(Long id, E master, Commodity commodity, String info, Quantity quantity, BigDecimal price, Integer discountRate, BigDecimal discount, BigDecimal total, BigDecimal lineTotal ) {
        this.id = id;
        this.master = master;
        this.commodity = commodity;
        this.info = info;
        this.quantity = quantity;
        this.price = price;
        this.discountRate = discountRate;
        this.discount = discount;
        this.total = total;
        this.lineTotal = lineTotal;
    }

    public VoucherCommodityItemViewModel(Long id, Long commodityId, String  commodityName, String info, Quantity quantity, BigDecimal price, Integer discountRate, BigDecimal discount, BigDecimal total, BigDecimal lineTotal) {
        this.id = id;
        Commodity com = new Commodity();
        com.setId(commodityId);
        com.setName(commodityName);
        this.commodity = com;
        this.info = info;
        this.quantity = quantity;
        this.price = price;
        this.discountRate = discountRate;
        this.discount = discount;
        this.total = total;
        this.lineTotal = lineTotal;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public E getMaster() {
        return master;
    }

    public void setMaster(E master) {
        this.master = master;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
    
    
    
}
