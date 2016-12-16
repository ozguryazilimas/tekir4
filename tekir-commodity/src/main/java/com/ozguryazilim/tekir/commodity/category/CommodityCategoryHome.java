package com.ozguryazilim.tekir.commodity.category;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.CommodityCategory;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class CommodityCategoryHome extends TreeBase<CommodityCategory> {

	@Inject
	private CommodityCategoryRepository repository;

	public CommodityCategoryRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final CommodityCategoryRepository repository) {
		this.repository = repository;
	}
}