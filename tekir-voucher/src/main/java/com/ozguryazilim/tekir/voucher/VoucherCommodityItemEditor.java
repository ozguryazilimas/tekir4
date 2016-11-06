/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.tekir.entities.Quantity;
import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 * Vocuher'lar için Commodity detay editorü.
 * 
 * PF Dialog FrameWork ile çalışır. Dolayısı ile Session bazlıdır.
 * 
 * @Dialog diye bir StereoType yapmak lazım.
 * 
 * @author Hakan Uygun
 */
@SessionScoped
@Named
public class VoucherCommodityItemEditor implements Serializable{
    
    private VoucherCommodityItemBase item;
    private VoucherCommodityItemEditorListener listener;
    private Currency currency;
    
    public void openDialog( VoucherCommodityItemBase item, Currency currency, VoucherCommodityItemEditorListener listener ){
        
        this.item = item;
        this.currency = currency;
        this.listener = listener;
        
        Map<String, Object> options = new HashMap<>();
        
        decorateDialog(options);
        
        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }

    
    public void closeDialog() {
        //Aslında burada kendini çağıran VoucherHome instance'a bir mesaj fırlatmak lazım.
        //Ya da en azından callback yapmak lazım Bunun için de buraya parametre olarak bean ismi ya da belli bir interface'i implemente eden biş ialabiliriz sanki.
        listener.saveItem(item);
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    /**
     * Dialogu hiç bir şey seçmeden kapatır.
     */
    public void cancelDialog() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    protected void decorateDialog(Map<String, Object> options){
        options.put("modal", true);
        //options.put("draggable", false);  
        options.put("resizable", false);
        options.put("contentHeight", 450);
    }
    
    protected String getDialogName() {
        return "/dialogs/commodityItemEditor";
    }

    public VoucherCommodityItemBase getItem() {
        return item;
    }

    public void setItem(VoucherCommodityItemBase item) {
        this.item = item;
    }

    public Commodity getCommodity() {
        return item.getCommodity();
    }

    public void setCommodity(Commodity commodity) {
        item.setCommodity(commodity);
        item.setQuantity(new Quantity(BigDecimal.ZERO, commodity.getDefaultUnit()));
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
     public void onAmountChange() {
        item.setTotal(item.getQuantity().getAmount().multiply(item.getPrice()));
    }
    
    public void onPriceChange() {
        item.setTotal(item.getQuantity().getAmount().multiply(item.getPrice()));
    }
    
    public void onTotalChange() {
        item.setPrice(item.getTotal().divide(item.getQuantity().getAmount()));
    }
    
    
}
