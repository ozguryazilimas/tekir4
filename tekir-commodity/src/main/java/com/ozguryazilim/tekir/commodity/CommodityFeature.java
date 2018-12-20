package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.commodity.config.CommodityPages;
import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(permission = "commodity", forEntity = Commodity.class)
@Page(type = PageType.BROWSE, page = CommodityPages.CommodityBrowse.class)
@Page(type = PageType.EDIT, page = CommodityPages.Commodity.class)
@Page(type = PageType.VIEW, page = CommodityPages.CommodityView.class)
@Page(type = PageType.MASTER_VIEW, page = CommodityPages.CommodityMasterView.class)
@AutoCode(cosumer = "Commodity", caption = "feature.caption.CommodityFeature", serial = "COMM" )
public class CommodityFeature extends AbstractFeatureHandler{
    
}
