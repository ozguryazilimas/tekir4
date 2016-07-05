package com.ozguryazilim.tekir.core.commodity;

import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@FormEdit(browsePage = CorePages.CommodityBrowse.class, editPage = CorePages.Commodity.class, viewContainerPage = CorePages.CommodityView.class, masterViewPage = CorePages.CommodityMasterView.class)
public class CommodityHome extends FormBase<Commodity, Long> {

	@Inject
	private CommodityRepository repository;

	@Override
	protected RepositoryBase<Commodity, CommodityViewModel> getRepository() {
		return repository;
	}
}