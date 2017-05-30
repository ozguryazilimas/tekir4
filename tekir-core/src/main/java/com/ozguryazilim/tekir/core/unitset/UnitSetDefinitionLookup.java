package com.ozguryazilim.tekir.core.unitset;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.UnitSetDefinition;
import com.ozguryazilim.tekir.entities.UnitSetDefinition_;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = CorePages.UnitSetDefinitionLookup.class)
public class UnitSetDefinitionLookup
		extends
			LookupTableControllerBase<UnitSetDefinition, UnitSetDefinitionViewModel> {

	@Inject
	private UnitSetDefinitionRepository repository;

	@Override
	public void buildModel(LookupTableModel<UnitSetDefinitionViewModel> model) {
		model.addColumn("code", "general.label.Code");
		model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<UnitSetDefinition, UnitSetDefinitionViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return UnitSetDefinition_.name.getName();
	}
}