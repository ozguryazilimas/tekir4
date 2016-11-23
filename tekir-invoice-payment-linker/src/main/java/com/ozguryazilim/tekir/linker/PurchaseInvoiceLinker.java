/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.linker;

import com.ozguryazilim.tekir.invoice.purchase.PurchaseInvoiceFeature;
import com.ozguryazilim.tekir.payment.purchase.PaymentHome;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.VoucherRedirectHandler;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.telve.feature.FeatureQualifier;
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
@FeatureQualifier(feauture = PurchaseInvoiceFeature.class)
public class PurchaseInvoiceLinker implements VoucherRedirectHandler, Serializable{

    public static final Logger LOG = LoggerFactory.getLogger(PurchaseInvoiceLinker.class);
    
    @Inject
    private PaymentHome paymentHome;
    
    @Inject @Voucher
    private PurchaseInvoiceFeature feature;
    
    @Override
    public Class<? extends ViewConfig> redirect(VoucherStateChange event) {
        LOG.debug("Purchase Order Invoice Linker");
        /*
        if( "PAID".equals( event.getTo().getName()) || "PARTIAL".equals( event.getTo().getName())){
            
            if (event.getPayload() instanceof PurchaseInvoice) {
            
                //Once yeni kayıt oluşturtalım
                Class<? extends ViewConfig> result = paymentHome.create();
                
                
                PurchaseInvoice entity = (PurchaseInvoice)event.getPayload();
                
                //Account
                paymentHome.getEntity().setAccount(entity.getAccount());
                
                //Nereden üretildiğinin linkini koyalım
                FeaturePointer fp = new FeaturePointer();
        
                fp.setBusinessKey(entity.getVoucherNo());
                fp.setPrimaryKey(entity.getId());
                fp.setFeature(feature.getName());
                
                paymentHome.getEntity().setStarter(fp);
                
                //Process bilgisi v.s.
                paymentHome.getEntity().setTopic(entity.getTopic());
                paymentHome.getEntity().setProcess(entity.getProcess());
                paymentHome.getEntity().setGroup(entity.getGroup());
                
                
                //Detayları dolduralım
                
                //FIXME: i18n
                FacesMessages.info("Fatura ödenecek!", "" + entity.getVoucherNo() + " " + entity.getTopic() + " fatura için ödeme girişi yapılacak!");
                return result;
            }
        }
        */
        return null;
    }
    
}
