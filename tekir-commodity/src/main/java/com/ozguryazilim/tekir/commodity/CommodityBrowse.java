package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.tekir.entities.Commodity_;
import com.ozguryazilim.tekir.entities.CommodityCategory_;
import com.ozguryazilim.tekir.entities.UnitSetDefinition_;
import com.ozguryazilim.tekir.entities.TaxDefinition_;
import com.ozguryazilim.telve.query.columns.BooleanColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.MoneyColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.BigDecimalFilter;
import com.ozguryazilim.telve.query.filters.BooleanFilter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;

import javax.inject.Inject;

/**
 * Brwose Control Class
 * 
 * @author
 */
@Browse(feature = CommodityFeature.class)
public class CommodityBrowse extends BrowseBase<Commodity, CommodityViewModel> {

	@Inject
	private CommodityRepository repository;

	@Override
	protected void buildQueryDefinition( QueryDefinition<Commodity, CommodityViewModel> queryDefinition) {

		BooleanFilter bf = new BooleanFilter<>(Commodity_.active, "general.label.Status", "general.boolean.active.");
		bf.setOperand(FilterOperand.All);		

		queryDefinition
		.addFilter(new StringFilter<>(Commodity_.code, "general.label.Code"))
		.addFilter(new StringFilter<>(Commodity_.name, "general.label.Name"))
		.addFilter(new SubStringFilter<>(Commodity_.unitSet, UnitSetDefinition_.baseUnit, "general.label.UnitSet"))
		.addFilter(new StringFilter<>(Commodity_.defaultUnit, "general.label.Unit"))
		.addFilter(new StringFilter<>(Commodity_.info, "general.label.Info"))
		.addFilter(new SubStringFilter<>(Commodity_.category, CommodityCategory_.name, "general.label.Category"))	
		.addFilter(new SubStringFilter<>(Commodity_.tax1, TaxDefinition_.name, "general.label.Tax"))
		.addFilter(new SubStringFilter<>(Commodity_.tax2, TaxDefinition_.name, "general.label.Tax"))
		.addFilter(new SubStringFilter<>(Commodity_.tax3, TaxDefinition_.name, "general.label.Tax"))
		.addFilter(new BigDecimalFilter<>(Commodity_.price, "general.label.Price"))
		.addFilter(bf);
		
		queryDefinition
		.addColumn(new LinkColumn<>(Commodity_.code, "general.label.Code"), true)
		.addColumn(new TextColumn<>(Commodity_.name, "general.label.Name"), true)
		.addColumn(new SubTextColumn<>(Commodity_.category, CommodityCategory_.name,"general.label.Category"), true)	
        .addColumn(new MoneyColumn<>(Commodity_.price, Commodity_.defaultCurrency, "general.label.Price"), true)
		.addColumn(new BooleanColumn<>(Commodity_.active, "general.label.Active", "general.boolean.active."),true)
		.addColumn(new SubTextColumn<>(Commodity_.tax1, TaxDefinition_.name, "general.label.Tax1"), false)
		.addColumn(new SubTextColumn<>(Commodity_.tax2, TaxDefinition_.name, "general.label.Tax2"), false)
		.addColumn(new SubTextColumn<>(Commodity_.tax3, TaxDefinition_.name, "general.label.Tax3"), false)
		.addColumn(new TextColumn<>(Commodity_.info, "general.label.Info"), false)
		.addColumn(new SubTextColumn<>(Commodity_.unitSet, UnitSetDefinition_.baseUnit, "general.label.UnitSet"), false)
		.addColumn(new TextColumn<>(Commodity_.defaultUnit,"general.label.Unit"), false);


		
	}

	@Override
	protected RepositoryBase<Commodity, CommodityViewModel> getRepository() {
		return repository;
	}
}