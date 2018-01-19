/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.tekir.entities.Feed;
import com.ozguryazilim.tekir.feed.config.FeedPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(permission = "feeds", forEntity = Feed.class)
@Page(type = PageType.BROWSE, page =FeedPages.FeedBrowse.class)
public class FeedFeature extends AbstractFeatureHandler{
    
}
