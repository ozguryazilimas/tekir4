package com.ozguryazilim.tekir.contact.corporationType;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.CorporationType;
import com.ozguryazilim.tekir.entities.CorporationType_;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = ContactPages.CorporationTypeLookup.class)
public class CorporationTypeLookup
		extends
			LookupTableControllerBase<CorporationType, CorporationTypeViewModel> {

	@Inject
	private CorporationTypeRepository repository;

	@Override
	public void buildModel(LookupTableModel<CorporationTypeViewModel> model) {
		model.addColumn("code", "general.label.Code");
		model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<CorporationType, CorporationTypeViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return CorporationType_.name.getName();
	}
}