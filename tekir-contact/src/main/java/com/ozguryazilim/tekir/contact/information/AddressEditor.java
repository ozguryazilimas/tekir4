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
import com.ozguryazilim.telve.auth.Identity;
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
    private Identity identity;    

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

        openDialogImpl();
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
    
    //Bu fonksiyon ile fatura adresi girişleri kullanıcı yetkisine bağlanmıştır.
    public boolean permissionCheck(ContactAddress contactAddress, String subType) {
        //Kullanıcının editoru hangi sınıf üzerinden açtığının (Emplooye,Contact)
        //bulunması için gerekli koşuldur.
        if (contactAddress.getContact().getContactRoles().contains("CONTACT")) {
            if (subType.equals("INVOICE")) {
                return !identity.isPermitted("contactAddresses:contactInvoiceAddress:" + getEntity().getContact().getOwner());
            }
            return false;
        } else if (contactAddress.getContact().getContactRoles().contains("EMPLOYEE")) {
            if (subType.equals("INVOICE")) {
                boolean valid = identity.isPermitted("employeeAddresses:employeeInvoiceAddress:" + getEntity().getContact().getOwner());
                return !valid;
            }
            return false;
        }
        return false;
    }
    
}
