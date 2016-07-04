package com.ozguryazilim.tekir.core.industry;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTreeControllerBase;
import com.ozguryazilim.tekir.entities.Industry;
import com.ozguryazilim.tekir.entities.Industry_;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = CorePages.IndustryLookup.class)
public class IndustryLookup
		extends
			LookupTreeControllerBase<Industry, Industry> {

	@Inject
	private IndustryRepository repository;

	@Override
	protected RepositoryBase<Industry, Industry> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return Industry_.name.getName();
	}
}