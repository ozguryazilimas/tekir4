/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.forms.SubView;
import com.ozguryazilim.telve.forms.SubViewPageBase;

/**
 *
 * @author oyas
 */
@SubView(containerPage = RecruitPages.ApplicantPages.ApplicantView.class, viewPage = RecruitPages.ApplicantPages.DocumentSubView.class, permission = "document", order = 42)
public class ApplicantDocumentSubView extends SubViewPageBase{
    @Override
    public void reload() {
        //Şimdilik ne yapılacak bilmiyorum
    }
}
