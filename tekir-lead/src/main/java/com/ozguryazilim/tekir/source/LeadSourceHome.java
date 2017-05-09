package com.ozguryazilim.tekir.source;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.LeadSource;
import com.ozguryazilim.telve.data.TreeRepositoryBase;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;

@ParamEdit
public class LeadSourceHome extends TreeBase<LeadSource> {
	
	@Inject
	private LeadSourceRepository repository;

	@Override
	protected TreeRepositoryBase<LeadSource> getRepository() {
		return repository;
	}

}
