/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactBank;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactInformation;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@ContactInformationEditor(handle = ContactBank.class, page=ContactPages.BankEditor.class)
public class BankEditor extends AbstractContactInformationEditor<ContactBank> {
    @Inject
    private ContactRepository contactRepository;

    @Inject
    private ContactInformationRepository contactInformationRepository;

    private boolean primaryBank = false;
    
    public void editPrimaryBank(Contact contact, ContactBank bank) {

        init();

        setContact(contact);
        if (bank != null) {
            setEntity(bank);
        } else {
            setEntity(createNewModel());
        }

        getEntity().setContact(contact);
        //getEntity().getSubTypes().add(ContactInformationConsts.PhoneSubTypes.MOBILE);
        getEntity().getRoles().add(ContactInformationConsts.Roles.PRIMARY);

        primaryBank = true;
        
        openDialogImpl();
    }

    
    @Override
    protected void init() {
        
    }

    /**
     * Yeni contact'ı save eder.
     * @return 
     */
    @Override
    public boolean onBeforeClose() {
        
        //Save edilmemiş bir contact için sadece primaryler eklenebilir.
        if (primaryBank) {
            getContact().setPrimaryBank(getEntity());
        } 

        if (getContact().isPersisted()) {
            //FIXME: Burada başk abir primary var ise onunla yer değiştirmek lazım.
            
            //Önce bileşeni save edelim.
            contactInformationRepository.save(getEntity());

            //Demek ki yeni giriş değiş dolayısı ile verileri saklayalım
            contactRepository.save(getContact());
        }
        
        return true;
    }

    @Override
    public String getIcon(ContactBank information) {
        return "fa fa-bank";
    }

    @Override
    public String getEditorCaption() {
        return "Bank Editor - i18n";
    }

    @Override
    public boolean acceptType(ContactInformation information) {
        return information instanceof ContactEMail;
    }

    @Override
    public ContactBank createNewModel() {
        return new ContactBank();
    }
}
