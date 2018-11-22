/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.linker;

import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.opportunity.OpportunityFeature;
import com.ozguryazilim.tekir.quote.QuoteHome;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.VoucherRedirectHandler;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.ozguryazilim.telve.messages.Messages;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@Dependent
@FeatureQualifier(feauture = OpportunityFeature.class)
public class OpportunityQuoteLinker implements VoucherRedirectHandler, Serializable{

    public static final Logger LOG = LoggerFactory.getLogger(OpportunityQuoteLinker.class);
    
    @Inject
    private QuoteHome quoteHome;
    
    @Inject @Voucher
    private OpportunityFeature opportunityFeature;
    
    @Override
    public Class<? extends ViewConfig> redirect(VoucherStateChange event) {
        LOG.debug("Opportunity-Quote Linker");
        if( "WON".equals( event.getTo().getName()) ){
            
            if (event.getPayload() instanceof Opportunity) {
            
                //Once yeni kayıt oluşturtalım
                Class<? extends ViewConfig> result = quoteHome.create();
                
                
                Opportunity entity = (Opportunity)event.getPayload();
                
                //Account
                quoteHome.getEntity().setAccount(entity.getAccount());
                
                //Nereden üretildiğinin linkini koyalım
                FeaturePointer fp = new FeaturePointer();
        
                fp.setBusinessKey(entity.getVoucherNo());
                fp.setPrimaryKey(entity.getId());
                fp.setFeature(opportunityFeature.getName());
                
                quoteHome.getEntity().setStarter(fp);
                
                //Process bilgisi v.s.
                quoteHome.getEntity().setTopic(entity.getTopic());
                quoteHome.getEntity().setProcess(entity.getProcess());
                quoteHome.getEntity().setGroup(entity.getGroup());
                
                
                FacesMessages.info(Messages.getMessage("opportunity-quote-linker.messages.WON"),Messages.getMessageFromData(Messages.getCurrentLocale(),  "opportunity-quote-linker.messages.WonDetail$%&" + entity.getVoucherNo()
                        + "$%&" + entity.getTopic()));
                      return result;
            }
        }
        return null;
    }
    
}
