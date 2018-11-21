/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.telve.attachment.AttachmentContext;
import com.ozguryazilim.telve.attachment.AttachmentContextProvider;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messages.Messages;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Applicant için Attachment Context Provider'ı
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
public class ApplicantAttachmentContextProvider implements AttachmentContextProvider{
    
    @Inject
    private Identity identity;
    
    @Override
    public boolean canHandle(FeaturePointer featurePointer, Object payload) {

        //Payload yoksa kabul etmiyoruz.
        switch ( featurePointer.getFeature() ){
            case "ApplicantFeature" : break;
            case "JobApplicationFeature" : break;
            default: return false;
        }
        
        //Demek ki ilgilendiğimiz bir Feature şimdi payload doğrumu bakalım.
        return canHandle(payload);
        
    }

    @Override
    public boolean canHandle(Object payload) {
        //Feature pointer ile ilgilenmiyoryuz. Bize payload yeter.
        return payload instanceof Applicant;
    }
    
    @Override
    public boolean canHandle(FeaturePointer featurePointer) {
        //Sadece feature pointer yeterli değil. Payload'a lazım.
        return false;
    }

    @Override
    public int priority() {
        return 200;
    }

    @Override
    public AttachmentContext getContext(FeaturePointer featurePointer, Object payload) {
        AttachmentContext result = new AttachmentContext();
        
        if( payload instanceof Applicant){
            Applicant contact = (Applicant)payload;
            
            String base = "/" + contact.getName() + " [" + contact.getCode() +"]/" ;
            
            if( !"ApplicantFeature".equals(featurePointer.getFeature())){
                //Varsa parametrede setliyoruz.
                String fps = Messages.getMessage("feature.plural.caption." + featurePointer.getFeature());
                result.setRoot( base + fps + "/" + featurePointer.getBusinessKey());
            } else {
                result.setRoot( base );
            }
            result.setFeaturePointer(featurePointer);
        } else {
            throw new RuntimeException("Yanlış provider!");
        }
        
        if( identity != null ){
            result.setUsername(identity.getLoginName());
        }
        
        return result;
    }
}
