package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.commodity.config.CommodityPages;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.tekir.entities.Commodity_;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = CommodityPages.CommodityLookup.class)
public class CommodityLookup
		extends
			LookupTableControllerBase<Commodity, CommodityViewModel> {

	@Inject
	private CommodityRepository repository;

	@Override
	public void buildModel(LookupTableModel<CommodityViewModel> model) {
		model.addColumn("code", "general.label.Code");
                model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<Commodity, CommodityViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return Commodity_.name.getName();
	}
}