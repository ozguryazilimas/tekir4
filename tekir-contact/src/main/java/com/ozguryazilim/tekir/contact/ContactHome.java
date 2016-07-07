package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * Home Control Class
 *
 * @author
 */
@FormEdit(browsePage = ContactPages.ContactBrowse.class, editPage = ContactPages.Contact.class, viewContainerPage = ContactPages.ContactView.class, masterViewPage = ContactPages.ContactMasterView.class)
public class ContactHome extends FormBase<Contact, Long> {

    @Inject
    private ContactRepository repository;

    @Inject
    private ContactInformationRepository informationRepository;
    
    private List<String> selectedRoles = new ArrayList<>();

    public Class<? extends ViewConfig> newPerson() {
        Person p = new Person();
        p.getContactRoles().add("CONTACT");
        p.getContactRoles().add("PERSON");
        setEntity(p);
        selectedRoles.clear();
        return ContactPages.Contact.class;
    }

    public Class<? extends ViewConfig> newCorporation() {
        Corporation p = new Corporation();
        p.getContactRoles().add("CONTACT");
        p.getContactRoles().add("CORPORATION");
        setEntity(p);
        selectedRoles.clear();
        return ContactPages.Contact.class;
    }

    @Override
    public boolean onBeforeSave() {
        //Eğer person ise name alanını düzeltmek lazım
        if (getEntity() instanceof Person) {
            getEntity().setName(((Person) getEntity()).getFirstName() + " " + ((Person) getEntity()).getLastName());
        }

        //Önce kullanıcı seçimli olmayan rolleri bir toparlayalım
        List<String> ls = getEntity().getContactRoles().stream()
                .filter(p -> !getContactRoles().contains(p))
                .collect(Collectors.toList());
        
        //Şimdi kullanıcın seçtiklerini ekleyelim
        ls.addAll(selectedRoles);
        
        //Şimdi de yeni durumu yerleştirelim.
        getEntity().getContactRoles().clear(); 
        getEntity().getContactRoles().addAll(ls);
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onAfterLoad() {
        
        selectedRoles = getEntity().getContactRoles().stream()
                            .filter(p -> getContactRoles().contains(p))
                            .collect(Collectors.toList());
        
        return super.onAfterLoad();
    }

    
    
    @Override
    protected RepositoryBase<Contact, ContactViewModel> getRepository() {
        return repository;
    }

    /**
     * Geriye ilgili contact'a ait iletişim bilgilerini döndürür.
     *
     * @return
     */
    public List<ContactInformation> getContactInformations() {
        return informationRepository.findByContact(getEntity());
    }

    public List<String> getContactRoles() {
        return ContactRoleRegistery.getSelectableContactRoles();
    }

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }
    
}
