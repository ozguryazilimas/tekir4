package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactInformation;
import javax.inject.Inject;

/**
 * E-Posta editorü
 * @author Hakan Uygun
 */
@ContactInformationEditor(handle = ContactEMail.class, page = ContactPages.EmailEditor.class)
public class EmailEditor extends AbstractContactInformationEditor<ContactEMail> {

    private Boolean primaryEmail = Boolean.FALSE;

    @Inject
    private ContactInformationRepository contactInformationRepository;
    
    @Inject
    private ContactRepository contactRepository;

    protected void init() {
        primaryEmail = Boolean.FALSE;
    }

    
    public void editPrimaryEmail(Contact contact, ContactEMail email) {

        init();

        setContact(contact);
        if (email != null) {
            setEntity(email);
        } else {
            setEntity(createNewModel());
        }

        getEntity().setContact(contact);
        getEntity().getRoles().add(ContactInformationConsts.Roles.PRIMARY);

        primaryEmail = Boolean.TRUE;

        openDialogImpl();
    }
    

    @Override
    public String getIcon(ContactEMail information) {
        return "fa fa-envelope-o";
    }

    @Override
    public String getEditorCaption() {
        return "";
    }

    @Override
    public boolean acceptType(ContactInformation information) {
        return information instanceof ContactEMail;
    }

    /**
     * Yeni contact'ı save eder.
     *
     * @return
     */
    @Override
    public boolean onBeforeClose() {
        //Save edilmemiş bir contact için sadece primaryler eklenebilir.
        if (primaryEmail) {
            getContact().setPrimaryEmail(getEntity());
        } 

        if (getContact().isPersisted()) {
            //Önce bileşeni save edelim.
            contactInformationRepository.save(getEntity());

            //Demek ki yeni giriş değiş dolayısı ile verileri saklayalım
            contactRepository.save(getContact());
        }

        return true;
    }

    @Override
    public ContactEMail createNewModel() {
        return new ContactEMail();
    }
}
