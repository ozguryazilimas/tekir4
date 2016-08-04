package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.commodity.config.CommodityPages;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.tekir.entities.Commodity_;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.StringFilter;
import javax.inject.Inject;

/**
 * Brwose Control Class
 * 
 * @author
 */
@Browse(browsePage = CommodityPages.CommodityBrowse.class, editPage = CommodityPages.Commodity.class, viewContainerPage = CommodityPages.CommodityView.class)
public class CommodityBrowse extends BrowseBase<Commodity, CommodityViewModel> {

	@Inject
	private CommodityRepository repository;

	@Override
	protected void buildQueryDefinition( QueryDefinition<Commodity, CommodityViewModel> queryDefinition) {
		queryDefinition
                    .addFilter(new StringFilter<>(Commodity_.code, "general.label.Code"))
                    .addFilter(new StringFilter<>(Commodity_.name, "general.label.Name"));
                
                queryDefinition
                    .addColumn(new LinkColumn<>(Commodity_.code, "general.label.Code"), true)
                    .addColumn(new LinkColumn<>(Commodity_.name, "general.label.Name"), true)
                    .addColumn(new TextColumn<>(Commodity_.info, "general.label.Info"), true);

	}

	@Override
	protected RepositoryBase<Commodity, CommodityViewModel> getRepository() {
		return repository;
	}
}