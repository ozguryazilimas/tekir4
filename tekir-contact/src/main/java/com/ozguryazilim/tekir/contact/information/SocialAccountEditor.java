package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.entities.ContactSocialAccount;
import javax.inject.Inject;

/**
 * Internet sosyal medya v.b. hesap editorü
 * @author Hakan Uygun
 */
@ContactInformationEditor(handle = ContactSocialAccount.class, page = ContactPages.SocialAccountEditor.class)
public class SocialAccountEditor extends AbstractContactInformationEditor<ContactSocialAccount>{

    @Inject
    private ContactInformationRepository contactInformationRepository;
    
    @Override
    protected void init() {
        //Yapacak bişi var mı?
    }

    @Override
    public String getIcon(ContactSocialAccount information) {
        switch( information.getNetwork() ){
            case "fb" : return "fa fa-facebook";
            case "tw" : return "fa fa-twitter";
            case "skp" : return "fa fa-skype";
            case "g+" : return "fa fa-google-plus";
            case "in" : return "fa fa-linkedin";
            case "sl" : return "fa fa-slack";
            case "wu" : return "fa fa-whatsapp";
        }
        return "fa fa-link";
    }

    @Override
    public String getEditorCaption() {
        return "";
    }

    @Override
    public ContactSocialAccount createNewModel() {
        return new ContactSocialAccount();
    }

    @Override
    public boolean acceptType(ContactInformation information) {
        return information instanceof ContactSocialAccount;
    }
    
    public boolean onBeforeClose() {
        contactInformationRepository.save(getEntity());
        return true;
    }
    
}
