/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.opportunity.config.OpportunityPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 * Opportunity Feature Definition
 * @author Hakan Uygun
 */
@Feature(caption = "feature.caption.Opportunity", icon = "fa fa-book", permission = "opportunity", forEntity = Opportunity.class )
@Page( type = PageType.BROWSE, page = OpportunityPages.OpportunityBrowse.class )
@Page( type = PageType.VIEW, page = OpportunityPages.OpportunityView.class )
@Page( type = PageType.MASTER_VIEW, page = OpportunityPages.OpportunityMasterView.class )
@Page( type = PageType.EDIT, page = OpportunityPages.Opportunity.class )
public class OpportunityFeature extends AbstractFeatureHandler{
    
}
