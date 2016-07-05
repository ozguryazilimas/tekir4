package com.ozguryazilim.tekir.contact.relation;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.ContactRelation;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class ContactRelationHome extends ParamBase<ContactRelation, Long> {

	@Inject
	private ContactRelationRepository repository;

	public ContactRelationRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final ContactRelationRepository repository) {
		this.repository = repository;
	}
}