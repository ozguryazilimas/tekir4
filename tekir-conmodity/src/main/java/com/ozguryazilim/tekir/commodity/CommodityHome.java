package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.commodity.config.CommodityPages;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@FormEdit(browsePage = CommodityPages.CommodityBrowse.class, editPage = CommodityPages.Commodity.class, viewContainerPage = CommodityPages.CommodityView.class, masterViewPage = CommodityPages.CommodityMasterView.class)
public class CommodityHome extends FormBase<Commodity, Long> {

	@Inject
	private CommodityRepository repository;

	@Override
	protected RepositoryBase<Commodity, CommodityViewModel> getRepository() {
		return repository;
	}
}