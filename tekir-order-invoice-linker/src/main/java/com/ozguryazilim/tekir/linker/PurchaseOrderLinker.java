/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.linker;

import com.ozguryazilim.tekir.entities.InvoiceItem;
import com.ozguryazilim.tekir.entities.OrderItem;
import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.invoice.purchase.PurchaseInvoiceHome;
import com.ozguryazilim.tekir.order.purchase.PurchaseOrderFeature;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.VoucherRedirectHandler;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.utils.VoucherItemUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@Dependent
@FeatureQualifier(feauture = PurchaseOrderFeature.class)
public class PurchaseOrderLinker implements VoucherRedirectHandler, Serializable{
    
    public static final Logger LOG = LoggerFactory.getLogger(PurchaseOrderLinker.class);
    
    @Inject
    private PurchaseInvoiceHome invoiceHome;
    
    @Inject @Voucher
    private PurchaseOrderFeature feature;
    
    @Override
    public Class<? extends ViewConfig> redirect(VoucherStateChange event) {
        LOG.debug("Purchase Order Invoice Linker");
        if( "WON".equals( event.getTo().getName()) ){
            
            if (event.getPayload() instanceof PurchaseOrder) {
            
                //Once yeni kayıt oluşturtalım
                Class<? extends ViewConfig> result = invoiceHome.create();
                
                
                PurchaseOrder entity = (PurchaseOrder)event.getPayload();
                
                //Account
                invoiceHome.getEntity().setAccount(entity.getAccount());
                
                //Nereden üretildiğinin linkini koyalım
                FeaturePointer fp = new FeaturePointer();
        
                fp.setBusinessKey(entity.getVoucherNo());
                fp.setPrimaryKey(entity.getId());
                fp.setFeature(feature.getName());
                
                invoiceHome.getEntity().setStarter(fp);
                
                //Process bilgisi v.s.
                invoiceHome.getEntity().setTopic(entity.getTopic());
                invoiceHome.getEntity().setProcess(entity.getProcess());
                invoiceHome.getEntity().setGroup(entity.getGroup());
                invoiceHome.getEntity().setPaymentPlan(entity.getPaymentPlan());
                invoiceHome.getEntity().setShippingDate(entity.getShippingDate());
                invoiceHome.getEntity().setShippingNote(entity.getShippingNote());
                
                
                //Detayları dolduralım
                for( OrderItem qi : entity.getItems() ){
                    InvoiceItem oi = new InvoiceItem();
                    VoucherItemUtils.copyCommodityItem(qi, oi);
                    oi.setMaster(invoiceHome.getEntity());
                    invoiceHome.getEntity().getItems().add(oi);
                }
                
                invoiceHome.calculateSummaries();
                
                //FIXME: i18n
                FacesMessages.info("Kutlarız. Sipariş tamamlandı!", "" + entity.getVoucherNo() + " " + entity.getTopic() + " şipariş için fatura girişi yapılacak!");
                return result;
            }
        }
        return null;
    }
}
