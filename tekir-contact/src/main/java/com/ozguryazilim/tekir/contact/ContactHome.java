package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.RelatedContact;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

/**
 * Home Control Class
 *
 * @author
 */
@FormEdit( feature = ContactFeature.class )
public class ContactHome extends FormBase<Contact, Long> {

    @Inject
    private Identity identity;
    
    @Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    @Inject
    private ContactRepository repository;

    @Inject
    private ContactInformationRepository informationRepository;

    @Inject
    private RelatedContactRepository relatedContactRepository;
    
    @Inject
    private PersonFeeder personFeeder;
    
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
    public boolean onAfterSave() {
        if( getEntity() instanceof Person ){
            personFeeder.feed((Person) getEntity());
        }
        return super.onAfterSave(); 
    }
    
    

    @Override
    public boolean onAfterLoad() {
        
        //FIXME: Burayı generic bir hale getirmek lazım                
        if( !identity.isPermitted("contact:select:" + getEntity().getOwner())){
            FacesMessages.error("Kayda erişim için yetkiniz yok!");
            createNew();
            viewNavigationHandler.navigateTo(ContactPages.ContactBrowse.class);
            return false;
        }
        
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

    
    public List<RelatedContact> getRelatedContacts() {
        return relatedContactRepository.findBySourceContact( getEntity());
    }
    
    //TODO:Method ismini düzeltelim
    public List<RelatedContact> getRelatedContactsRevers() {
        return relatedContactRepository.findByTargetContact( getEntity());
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
    
    /**
     * Seçili olan contact'ı bir account'a çevirmek için accout home'a gönder.
     */
    public void convertToAccount(){
        getEntity().getContactRoles().add("ACCOUNT");
        repository.save(getEntity());
    }
    
    
    public Boolean getIsAccount(){
        return getEntity().getContactRoles().contains("ACCOUNT");
    }
}
