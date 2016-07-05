package com.ozguryazilim.tekir.contact.category;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTreeControllerBase;
import com.ozguryazilim.tekir.entities.ContactCategory;
import com.ozguryazilim.tekir.entities.ContactCategory_;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = ContactPages.ContactCategoryLookup.class)
public class ContactCategoryLookup
		extends
			LookupTreeControllerBase<ContactCategory, ContactCategory> {

	@Inject
	private ContactCategoryRepository repository;

	@Override
	protected RepositoryBase<ContactCategory, ContactCategory> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return ContactCategory_.name.getName();
	}
}