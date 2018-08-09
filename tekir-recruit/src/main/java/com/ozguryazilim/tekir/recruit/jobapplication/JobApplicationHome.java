package com.ozguryazilim.tekir.recruit.jobapplication;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.recruit.applicant.ApplicantRepository;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erdem Uslu
 */
@FormEdit(feature = JobApplicationFeature.class)
public class JobApplicationHome extends FormBase<JobApplication, Long> {

    private static Logger LOG = LoggerFactory.getLogger(JobApplicationHome.class);

    @Inject
    private JobApplicationRepository repository;

    @Inject
    private ApplicantRepository applicantRepository;

    @Inject
    private Identity identity;

    @Override
    protected RepositoryBase<JobApplication, ?> getRepository() {
        return repository;
    }

    @Override
    public void createNew() {
        super.createNew();
        getEntity().setOwner(identity.getLoginName());
    }

    @Override
    public boolean onBeforeSave() {

        applicantRepository.saveAndFlushAndRefresh(getApplicant());
        
        return super.onBeforeSave();
    }

    public JobAdvert getAdvert() {
        return getEntity().getAdvert();
    }

    public Applicant getApplicant() {
        return getEntity().getApplicant();
    }

    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase entityBase) {
        return FeatureUtils.getFeaturePointer(entityBase);
    }

}
