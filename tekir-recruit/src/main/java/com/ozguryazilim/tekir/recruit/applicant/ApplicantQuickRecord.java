package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.quick.QuickRecord;
import com.ozguryazilim.telve.quick.QuickRecordBase;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Erdem Uslu
 */
@QuickRecord(page = RecruitPages.ApplicantPages.ApplicantQuickPanel.class, permission = "applicant", showonMenu = false)
public class ApplicantQuickRecord extends QuickRecordBase {

    @Inject
    private ApplicantHome applicantHome;
    
    @PostConstruct
    public void init() {
        applicantHome.createNew();
    }
    
    @Override
    public void save() {
        applicantHome.save();
        applicantHome.close();
        super.save();
    }

    @Override
    public void cancel() {
        applicantHome.close();
        super.cancel();
    }

    @Override
    protected boolean doSave() {
        return true;
    }
    
}
