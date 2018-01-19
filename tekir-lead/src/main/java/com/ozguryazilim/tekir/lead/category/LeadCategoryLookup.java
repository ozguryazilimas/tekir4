package com.ozguryazilim.tekir.lead.category;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.LeadCategory;
import com.ozguryazilim.tekir.lead.config.LeadPages;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTreeControllerBase;

import com.ozguryazilim.tekir.entities.LeadCategory_;

@Lookup(dialogPage = LeadPages.LeadCategoryLookup.class)
public class LeadCategoryLookup extends LookupTreeControllerBase<LeadCategory, LeadCategory> {

	@Inject
	private LeadCategoryRepository repository;

	@Override
	protected RepositoryBase<LeadCategory, LeadCategory> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return LeadCategory_.name.getName();
	}

}
