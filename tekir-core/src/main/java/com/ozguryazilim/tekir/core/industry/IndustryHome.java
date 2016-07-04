package com.ozguryazilim.tekir.core.industry;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.Industry;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class IndustryHome extends TreeBase<Industry> {

	@Inject
	private IndustryRepository repository;

	public IndustryRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final IndustryRepository repository) {
		this.repository = repository;
	}
}