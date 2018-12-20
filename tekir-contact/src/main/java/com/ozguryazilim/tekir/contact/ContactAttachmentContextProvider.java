package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.attachment.AttachmentContext;
import com.ozguryazilim.telve.attachment.AttachmentContextProvider;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Person ve Corporation Featureları için Context oluşturur.
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
public class ContactAttachmentContextProvider implements AttachmentContextProvider{

    @Inject
    private Identity identity;
    
    @Override
    public boolean canHandle(FeaturePointer featurePointer, Object payload) {

        //Payload yoksa kabul etmiyoruz.
        switch ( featurePointer.getFeature() ){
            case "PersonFeature" : break;
            case "CorporationFeature" : break;
            default: return false;
        }
        
        //Demek ki ilgilendiğimiz bir Feature şimdi payload doğrumu bakalım.
        return canHandle(payload);
        
    }

    @Override
    public boolean canHandle(Object payload) {
        //Feature pointer ile ilgilenmiyoryuz. Bize payload yeter.
        return payload instanceof Contact;
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
        
        if( payload instanceof Contact){
            Contact contact = (Contact)payload;
            
            result.setRoot( "/" + contact.getName() + " [" + contact.getCode() +"]/" );
            //Varsa parametrede setliyoruz.
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
