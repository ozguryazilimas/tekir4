package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = ContactPages.ContactLookup.class)
public class ContactLookup
		extends
			LookupTableControllerBase<Contact, ContactViewModel> {

	@Inject
	private ContactRepository repository;

	@Override
	public void buildModel(LookupTableModel<ContactViewModel> model) {
		model.addColumn("code", "general.label.Code");
                model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<Contact, ContactViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return Contact_.name.getName();
	}
        
        
        @Override
    public void populateData() {
        String type = getModel().getProfileProperties().get("T");
        
                
        //Şimdide Repository'den sorgumuz yapıp datayı dolduruyoruz
        getModel().setData(repository.lookupQuery(getModel().getSearchText(), type));
    }
}