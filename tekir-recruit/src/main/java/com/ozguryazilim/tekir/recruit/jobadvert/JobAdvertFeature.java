package com.ozguryazilim.tekir.recruit.jobadvert;

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
@Feature(permission = "recruit", forEntity = JobAdvert.class)
@Page(type = PageType.VIEW, page = RecruitPages.JobAdvertPages.JobAdvertView.class)
@Page(type = PageType.MASTER_VIEW, page = RecruitPages.JobAdvertPages.JobAdvertMasterView.class)
@Page(type = PageType.BROWSE, page = RecruitPages.JobAdvertPages.JobAdvertBrowse.class)
@Page(type = PageType.EDIT, page = RecruitPages.JobAdvertPages.JobAdvert.class)
public class JobAdvertFeature extends AbstractFeatureHandler {

}
