/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.entities.ContactPhone;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.primefaces.context.RequestContext;

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
    private ViewConfigResolver viewConfigResolver;

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private ContactInformationRepository contactInContactRepository;

    private Contact contact;

    private Boolean primaryMobile = Boolean.FALSE;
    private Boolean primaryPhone = Boolean.FALSE;
    private Boolean primaryFax = Boolean.FALSE;

    public void editPrimaryMobile(Contact contact, ContactPhone phone) {

        init();

        this.contact = contact;
        if (phone != null) {
            setEntity(phone);
        } else {
            setEntity(new ContactPhone());
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
            setEntity(new ContactPhone());
        }

        getEntity().setContact(contact);
        getEntity().getSubTypes().add("PHONE");
        getEntity().getRoles().add("PRIMARY");


        primaryPhone = Boolean.TRUE;

        openDialogImpl();
    }

    public void editPrimaryFax(Contact contact, ContactPhone phone) {
        init();

        this.contact = contact;
        if (phone != null) {
            setEntity(phone);
        } else {
            setEntity(new ContactPhone());
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
     */
    public void closeDialog() {
        //Save edilmemiş bir contact için sadece primaryler eklenebilir.
        if (primaryPhone) {
            contact.setPrimaryPhone(getEntity());
        } else if (primaryMobile) {
            contact.setPrimaryMobile(getEntity());
        } else if (primaryFax) {
            contact.setPrimaryFax(getEntity());
        }

        if (contact.isPersisted()) {
            //Önce bileşeni save edelim.
            contactInContactRepository.save(getEntity());

            //Demek ki yeni giriş değiş dolayısı ile verileri saklayalım
            contactRepository.save(contact);
        }

        RequestContext.getCurrentInstance().closeDialog(null);
    }

    /**
     * startPopup dialog adını döndürür.
     *
     * @return
     */
    public String getDialogName() {
        String viewId = viewConfigResolver.getViewConfigDescriptor(ContactPages.PhoneEditor.class).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
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
    public void create(Contact contact) {
        init();

        this.contact = contact;
        setEntity( new ContactPhone());
        getEntity().setContact(contact);

        openDialogImpl();
    }

    @Override
    public void edit(ContactPhone information) {
        init();

        this.contact = information.getContact();
        setEntity(information );

        openDialogImpl();

    }

    @Override
    public void delete(ContactPhone information) {
        contactInContactRepository.remove(information);
    }

    @Override
    public boolean acceptType(ContactInformation information) {
        return information instanceof ContactPhone;
    }

}
