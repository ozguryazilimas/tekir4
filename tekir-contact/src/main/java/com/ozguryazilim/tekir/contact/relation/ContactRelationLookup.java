package com.ozguryazilim.tekir.contact.relation;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.ContactRelation;
import com.ozguryazilim.tekir.entities.ContactRelation_;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = ContactPages.ContactRelationLookup.class)
public class ContactRelationLookup
		extends
			LookupTableControllerBase<ContactRelation, ContactRelationViewModel> {

	@Inject
	private ContactRelationRepository repository;

	@Override
	public void buildModel(LookupTableModel<ContactRelationViewModel> model) {
		model.addColumn("code", "general.label.Code");
		model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<ContactRelation, ContactRelationViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return ContactRelation_.name.getName();
	}
}