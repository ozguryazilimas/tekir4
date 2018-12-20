package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(permission = "activity", forEntity = Activity.class)
@Page(type = PageType.BROWSE, page = ActivityPages.ActivityBrowse.class)
@Page(type = PageType.VIEW, page = ActivityPages.ActivityView.class)
@Page(type = PageType.MASTER_VIEW, page = ActivityPages.ActivityMasterView.class)
@Page(type = PageType.EDIT, page = ActivityPages.Activity.class)
public class ActivityFeature extends AbstractFeatureHandler{
    
}
