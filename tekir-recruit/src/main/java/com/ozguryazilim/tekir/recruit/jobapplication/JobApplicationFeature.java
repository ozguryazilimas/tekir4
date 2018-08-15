package com.ozguryazilim.tekir.recruit.jobapplication;

import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.recruit.config.RecruitPages.JobApplicationPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
/**
 *
 * @author yusuf
 */
@Feature(permission="jobApplication",forEntity = JobApplication.class)
@Page(type = PageType.VIEW, page = JobApplicationPages.JobApplicationView.class)
@Page(type = PageType.MASTER_VIEW, page = JobApplicationPages.JobApplicationMasterView.class)
@Page(type=PageType.BROWSE,page=JobApplicationPages.JobApplicationBrowse.class)
@Page(type=PageType.EDIT,page=JobApplicationPages.JobApplication.class)
public class JobApplicationFeature extends AbstractFeatureHandler{
    
}
