/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.entities.ContactPhone;
import javax.inject.Inject;

/**
 * Contact Phone Editor.
 *
 * Yeni ya da var olan bir taneyi editlemek için controller sınıf
 *
 * @author Hakan Uygun
 */
@ContactInformationEditor(handle = ContactPhone.class, page=ContactPages.PhoneEditor.class)
public class PhoneEditor extends AbstractContactInformationEditor<ContactPhone> {

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private ContactInformationRepository contactInformationRepository;

    private Boolean primaryMobile = Boolean.FALSE;
    private Boolean primaryPhone = Boolean.FALSE;
    private Boolean primaryFax = Boolean.FALSE;

    public void editPrimaryMobile(Contact contact, ContactPhone phone) {

        init();

        setContact(contact);
        if (phone != null) {
            setEntity(phone);
        } else {
            setEntity(createNewModel());
        }

        getEntity().setContact(contact);
        getEntity().getSubTypes().add("MOBILE");
        getEntity().getRoles().add("PRIMARY");

        primaryMobile = Boolean.TRUE;

        openDialogImpl();
    }

    public void editPrimaryPhone(Contact contact, ContactPhone phone) {

        init();

        if (phone != null) {
            setEntity(phone);
        } else {
            setEntity(createNewModel());
        }

        getEntity().setContact(contact);
        getEntity().getSubTypes().add("PHONE");
        getEntity().getRoles().add("PRIMARY");


        primaryPhone = Boolean.TRUE;

        openDialogImpl();
    }

    public void editPrimaryFax(Contact contact, ContactPhone phone) {
        init();

        setContact(contact);
        if (phone != null) {
            setEntity(phone);
        } else {
            setEntity(createNewModel());
        }

        getEntity().setContact(contact);
        getEntity().getSubTypes().add("FAX");
        getEntity().getRoles().add("PRIMARY");

        primaryFax = Boolean.TRUE;

        openDialogImpl();
    }

    protected void init() {
        primaryMobile = Boolean.FALSE;
        primaryPhone = Boolean.FALSE;
        primaryFax = Boolean.FALSE;
    }

    /**
     * Yeni contact'ı save eder.
     * @return 
     */
    @Override
    public boolean onBeforeClose() {
        //Save edilmemiş bir contact için sadece primaryler eklenebilir.
        if (primaryPhone) {
            getContact().setPrimaryPhone(getEntity());
        } else if (primaryMobile) {
            getContact().setPrimaryMobile(getEntity());
        } else if (primaryFax) {
            getContact().setPrimaryFax(getEntity());
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
    public String getIcon(ContactPhone information) {
        if (information.getSubTypes().contains("MOBILE")) {
            return "fa fa-mobile";
        } else if (information.getSubTypes().contains("FAX")) {
            return "fa fa-fax";
        } else {
            return "fa fa-phone";
        }
    }

    @Override
    public String getEditorCaption() {
        return "Phone Editor - i18n";
    }

    @Override
    public boolean acceptType(ContactInformation information) {
        return information instanceof ContactEMail;
    }

    @Override
    public ContactPhone createNewModel() {
        return new ContactPhone();
    }

}
