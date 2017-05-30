/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactInformation;
import javax.inject.Inject;

/**
 * Contact Address Editor
 * 
 * @author Hakan Uygun
 */
@ContactInformationEditor(handle = ContactAddress.class, page = ContactPages.AddressEditor.class)
public class AddressEditor extends AbstractContactInformationEditor<ContactAddress>{

    private Boolean primaryAddress = Boolean.FALSE;
    

    @Inject
    private ContactInformationRepository contactInformationRepository;
    
    @Inject
    private ContactRepository contactRepository;
    
    public void editPrimaryAddress(Contact contact, ContactAddress address) {

        init();

        setContact(contact);
        if (address != null) {
            setEntity(address);
        } else {
            setEntity(createNewModel());
        }

        getEntity().setContact(contact);
        getEntity().getRoles().add(ContactInformationConsts.Roles.PRIMARY);

        primaryAddress = Boolean.TRUE;

        openDialog();
    }
    
    /**
     * Yeni contact'ı save eder.
     *
     * @return
     */
    @Override
    public boolean onBeforeClose() {
        //Save edilmemiş bir contact için sadece primaryler eklenebilir.
        if (primaryAddress) {
            getContact().setPrimaryAddress(getEntity());
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
    protected void init() {
        primaryAddress = Boolean.FALSE;
    }

    @Override
    public String getIcon(ContactAddress information) {
        return "fa fa-map-o";
    }

    @Override
    public String getEditorCaption() {
        return "";
    }

    @Override
    public ContactAddress createNewModel() {
        return new ContactAddress();
    }

    @Override
    public boolean acceptType(ContactInformation information) {
        return information instanceof ContactAddress;
    }
    
}
