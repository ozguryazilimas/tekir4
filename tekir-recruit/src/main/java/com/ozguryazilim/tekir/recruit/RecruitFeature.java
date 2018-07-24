package com.ozguryazilim.tekir.recruit;

import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author deniz
 */
@Feature(permission = "recruit", forEntity = JobAdvert.class )
@Page(type = PageType.VIEW, page = RecruitPages.RecruitView.class )
@Page(type = PageType.MASTER_VIEW, page = RecruitPages.RecruitMasterView.class)
@Page( type = PageType.EDIT, page = RecruitPages.Recruit.class )
public class RecruitFeature extends AbstractFeatureHandler {
    
}
