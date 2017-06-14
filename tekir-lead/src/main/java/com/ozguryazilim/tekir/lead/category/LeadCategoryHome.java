package com.ozguryazilim.tekir.lead.category;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.LeadCategory;
import com.ozguryazilim.telve.data.TreeRepositoryBase;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;

@ParamEdit
public class LeadCategoryHome extends TreeBase<LeadCategory> {

	@Inject
	private LeadCategoryRepository repository;
	
	@Override
	protected TreeRepositoryBase<LeadCategory> getRepository() {
		return repository;
	}

}
