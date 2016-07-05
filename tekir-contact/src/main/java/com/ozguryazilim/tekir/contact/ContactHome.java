package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.data.RepositoryBase;
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

    public Class<? extends ViewConfig> newPerson() {
        Person p = new Person();
        setEntity(p);
        return ContactPages.Contact.class;
    }

    public Class<? extends ViewConfig> newCorporation() {
        Corporation p = new Corporation();
        setEntity(p);
        return ContactPages.Contact.class;
    }

    @Override
    public boolean onBeforeSave() {
        //Eğer person ise name alanını düzeltmek lazım
        if( getEntity() instanceof Person){
            getEntity().setName( ((Person)getEntity()).getFirstName() + " " + ((Person)getEntity()).getLastName());
        }
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected RepositoryBase<Contact, ContactViewModel> getRepository() {
        return repository;
    }
}
