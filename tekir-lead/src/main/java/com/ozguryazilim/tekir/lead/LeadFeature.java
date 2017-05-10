package com.ozguryazilim.tekir.lead;

import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.tekir.lead.config.LeadPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

@Feature(permission = "lead", forEntity = Lead.class)
@Page(type = PageType.BROWSE, page = LeadPages.LeadBrowse.class)
@Page(type = PageType.EDIT, page = LeadPages.Lead.class)
@Page(type = PageType.VIEW, page = LeadPages.LeadView.class)
@Page(type = PageType.MASTER_VIEW, page = LeadPages.LeadMasterView.class)
public class LeadFeature extends AbstractFeatureHandler {

}