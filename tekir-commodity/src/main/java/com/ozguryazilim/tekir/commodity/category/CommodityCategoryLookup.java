package com.ozguryazilim.tekir.commodity.category;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTreeControllerBase;
import com.ozguryazilim.tekir.entities.CommodityCategory;
import com.ozguryazilim.tekir.entities.CommodityCategory_;
import com.ozguryazilim.tekir.commodity.config.CommodityPages;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = CommodityPages.CommodityCategoryLookup.class)
public class CommodityCategoryLookup
		extends
			LookupTreeControllerBase<CommodityCategory, CommodityCategory> {

	@Inject
	private CommodityCategoryRepository repository;

	@Override
	protected RepositoryBase<CommodityCategory, CommodityCategory> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return CommodityCategory_.name.getName();
	}
}