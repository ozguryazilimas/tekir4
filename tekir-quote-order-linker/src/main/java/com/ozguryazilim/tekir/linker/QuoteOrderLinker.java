/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.linker;

import com.ozguryazilim.tekir.entities.OrderItem;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.QuoteItem;
import com.ozguryazilim.tekir.order.sales.SalesOrderHome;
import com.ozguryazilim.tekir.quote.QuoteFeature;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.VoucherRedirectHandler;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
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
@FeatureQualifier(feauture = QuoteFeature.class)
public class QuoteOrderLinker implements VoucherRedirectHandler, Serializable{

    public static final Logger LOG = LoggerFactory.getLogger(QuoteOrderLinker.class);
    
    @Inject
    private SalesOrderHome salesOrderHome;
    
    @Inject @Voucher
    private QuoteFeature quoteFeature;
    
    @Override
    public Class<? extends ViewConfig> redirect(VoucherStateChange event) {
        LOG.debug("Quote Order Linker");
        if( "WON".equals( event.getTo().getName()) ){
            
            if (event.getPayload() instanceof Quote) {
            
                //Once yeni kayıt oluşturtalım
                Class<? extends ViewConfig> result = salesOrderHome.create();
                
                
                Quote entity = (Quote)event.getPayload();
                
                //Account
                salesOrderHome.getEntity().setAccount(entity.getAccount());
                
                //Nereden üretildiğinin linkini koyalım
                FeaturePointer fp = new FeaturePointer();
        
                fp.setBusinessKey(entity.getVoucherNo());
                fp.setPrimaryKey(entity.getId());
                fp.setFeature(quoteFeature.getName());
                
                salesOrderHome.getEntity().setStarter(fp);
                
                //Process bilgisi v.s.
                salesOrderHome.getEntity().setTopic(entity.getTopic());
                salesOrderHome.getEntity().setProcess(entity.getProcess());
                salesOrderHome.getEntity().setGroup(entity.getGroup());
                salesOrderHome.getEntity().setPaymentPlan(entity.getPaymentPlan());
                
                //Detayları dolduralım
                for( QuoteItem qi : entity.getItems() ){
                    OrderItem oi = new OrderItem();
                    oi.setCommodity(qi.getCommodity());
                    oi.setInfo(qi.getInfo());
                    oi.setMaster(salesOrderHome.getEntity());
                    oi.setPrice(qi.getPrice());
                    oi.setQuantity(qi.getQuantity());
                    oi.setTotal(qi.getTotal());
                    salesOrderHome.getEntity().getItems().add(oi);
                }
                
                salesOrderHome.calcSummaries();
                
                //FIXME: i18n
                FacesMessages.info("Kutlarız. Teklif Kabul edildi!", "" + entity.getVoucherNo() + " " + entity.getTopic() + " teklif için şipariş hazırlanacak!");
                return result;
            }
        }
        return null;
    }
    
}
