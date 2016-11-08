/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.commodity.config.CommodityPages;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(caption = "feature.caption.Commodity", icon = "fa fa-user", permission = "commodity", forEntity = Commodity.class)
@Page(type = PageType.BROWSE, page = CommodityPages.CommodityBrowse.class)
@Page(type = PageType.EDIT, page = CommodityPages.Commodity.class)
@Page(type = PageType.VIEW, page = CommodityPages.CommodityView.class)
@Page(type = PageType.MASTER_VIEW, page = CommodityPages.CommodityMasterView.class)
public class CommodityFeature extends AbstractFeatureHandler{
    
}
