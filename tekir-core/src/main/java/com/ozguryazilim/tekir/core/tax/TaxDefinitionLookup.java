package com.ozguryazilim.tekir.core.tax;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.TaxDefinition;
import com.ozguryazilim.tekir.entities.TaxDefinition_;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = CorePages.TaxDefinitionLookup.class)
public class TaxDefinitionLookup
		extends
			LookupTableControllerBase<TaxDefinition, TaxDefinitionViewModel> {

	@Inject
	private TaxDefinitionRepository repository;

	@Override
	public void buildModel(LookupTableModel<TaxDefinitionViewModel> model) {
		model.addColumn("code", "general.label.Code");
		model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<TaxDefinition, TaxDefinitionViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return TaxDefinition_.name.getName();
	}
}