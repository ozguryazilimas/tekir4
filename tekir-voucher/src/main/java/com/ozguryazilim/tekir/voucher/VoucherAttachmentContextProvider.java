package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherProcessBase;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.attachment.AttachmentContext;
import com.ozguryazilim.telve.attachment.AttachmentContextProvider;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messages.Messages;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@ApplicationScoped
public class VoucherAttachmentContextProvider implements AttachmentContextProvider{

    @Inject
    private Identity identity;
    
    @Override
    public boolean canHandle(FeaturePointer featurePointer, Object payload) {
        //Generik bir provider feature bazında ilgilenmiyoruz. Dolayısı ile daha spesifik olanlara yer bırakıyoruz.
        return false;
    }

    @Override
    public boolean canHandle(Object payload) {
        //VoucerBase'den türemiş herşeyi kabul edelim.
        return ( payload instanceof VoucherBase );
    }
    
    @Override
    public boolean canHandle(FeaturePointer featurePointer) {
        //Biz feature değil payload ile ilgileniyoruz.
        return false;
    }

    @Override
    public int priority() {
        return 300;
    }

    @Override
    public AttachmentContext getContext(FeaturePointer featurePointer, Object payload) {
        AttachmentContext result = new AttachmentContext();
        
        String base = "";
        
        //FIXME: eğer ayarlardan flat istenmiş ise bu kısım olmayacak.
        if( payload instanceof VoucherProcessBase){
            //Account Aware bir Voucher bu
            VoucherProcessBase o = (VoucherProcessBase)payload;
            base = "/" + o.getAccount().getName() + " [" + o.getAccount().getCode() +"]";
        }
        
        if( payload instanceof VoucherBase){
            VoucherBase vo = (VoucherBase)payload;
            
            FeaturePointer fp = featurePointer;
            if( fp == null ){
                //Parametre gelmemiş ise payload'dan alalım
                fp = FeatureUtils.getFeaturePointer(vo);
            }
            
            //FIXME: i18n feature ismi için. Amma application local kullanmak lazım.
            String fps = Messages.getMessage("feature.plural.caption." + fp.getFeature());
            result.setRoot( base + "/" + fps + "/" + fp.getBusinessKey());
            result.setFeaturePointer(fp);
        } else {
            throw new RuntimeException("Yanlış provider!");
        }
        
        if( identity != null ){
            result.setUsername(identity.getLoginName());
        }
        
        return result;
    }

    
    
}
