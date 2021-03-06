package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.Applicant_;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import javax.inject.Inject;

/**
 * Applicant için Lookup Dialog Sınıfı.
 *  
 * @author Erdem Uslu
 */
@Lookup(dialogPage = RecruitPages.ApplicantPages.ApplicantLookup.class)
public class ApplicantLookup extends LookupTableControllerBase<Applicant, ApplicantViewModel> {

    @Inject
    private ApplicantRepository applicantRepository;
    
    @Inject
    private Identity identity;

    @Override
    protected void buildModel(LookupTableModel<ApplicantViewModel> model) {
        model.addColumn("code", "general.label.Code");
        model.addColumn("name", "general.label.Name");
    }

    @Override
    protected RepositoryBase<Applicant, ApplicantViewModel> getRepository() {
        return applicantRepository;
    }

    @Override
    public String getCaptionFieldName() {
        return Applicant_.name.getName();
    }
    
}
