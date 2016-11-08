/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Feature(caption = "feature.caption.Activity", icon = "fa fa-user", permission = "activity", forEntity = Activity.class)
@Page(type = PageType.BROWSE, page = ActivityPages.ActivityBrowse.class)
public class ActivityFeature extends AbstractFeatureHandler{
    
}
