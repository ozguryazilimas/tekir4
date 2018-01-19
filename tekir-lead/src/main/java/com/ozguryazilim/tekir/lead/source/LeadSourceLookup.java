package com.ozguryazilim.tekir.lead.source;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.LeadSource;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTreeControllerBase;

import com.ozguryazilim.tekir.entities.LeadSource_;
import com.ozguryazilim.tekir.lead.config.LeadPages;

@Lookup(dialogPage = LeadPages.LeadSourceLookup.class)
public class LeadSourceLookup extends LookupTreeControllerBase<LeadSource, LeadSource> {

	@Inject
	private LeadSourceRepository repository;

	@Override
	protected RepositoryBase<LeadSource, LeadSource> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return LeadSource_.name.getName();
	}

}
