package com.ozguryazilim.tekir.contact.category;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.ContactCategory;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class ContactCategoryHome extends TreeBase<ContactCategory> {

	@Inject
	private ContactCategoryRepository repository;

	public ContactCategoryRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final ContactCategoryRepository repository) {
		this.repository = repository;
	}
}