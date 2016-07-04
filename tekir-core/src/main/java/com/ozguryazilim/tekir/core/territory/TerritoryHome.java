package com.ozguryazilim.tekir.core.territory;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.Territory;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class TerritoryHome extends ParamBase<Territory, Long> {

	@Inject
	private TerritoryRepository repository;

	public TerritoryRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final TerritoryRepository repository) {
		this.repository = repository;
	}
}