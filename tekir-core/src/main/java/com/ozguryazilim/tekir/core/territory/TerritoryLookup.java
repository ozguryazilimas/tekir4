package com.ozguryazilim.tekir.core.territory;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.tekir.entities.Territory_;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = CorePages.TerritoryLookup.class)
public class TerritoryLookup
		extends
			LookupTableControllerBase<Territory, TerritoryViewModel> {

	@Inject
	private TerritoryRepository repository;

	@Override
	public void buildModel(LookupTableModel<TerritoryViewModel> model) {
		model.addColumn("code", "general.label.Code");
		model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<Territory, TerritoryViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return Territory_.name.getName();
	}
}