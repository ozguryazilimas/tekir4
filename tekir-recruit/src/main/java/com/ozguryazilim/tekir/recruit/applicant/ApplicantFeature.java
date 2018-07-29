package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author yusuf
 */
@Feature(permission = "applicant", forEntity = Applicant.class)
@Page(type = PageType.BROWSE, page = RecruitPages.applicant.ApplicantBrowse.class)
@Page(type = PageType.EDIT, page = RecruitPages.applicant.Applicant.class)
public class ApplicantFeature extends AbstractFeatureHandler{
    
}

