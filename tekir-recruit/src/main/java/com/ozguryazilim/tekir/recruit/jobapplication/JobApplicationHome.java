package com.ozguryazilim.tekir.recruit.jobapplication;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.quick.QuickRecordController;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erdem Uslu
 */
@FormEdit(feature = JobApplicationFeature.class)
@AutoCode(cosumer = "JobApplication", caption = "module.caption.JobApplicaiton", serial = "JAP")
public class JobApplicationHome extends FormBase<JobApplication, Long> {

    private static Logger LOG = LoggerFactory.getLogger(JobApplicationHome.class);

    @Inject
    private JobApplicationRepository repository;

    @Inject
    private QuickRecordController quickRecordController;

    @Inject
    private Identity identity;
    
    @Inject
    private AutoCodeService codeService;

    @Override
    protected RepositoryBase<JobApplication, ?> getRepository() {
        return repository;
    }

    @Override
    public void createNew() {
        super.createNew();
        getEntity().setOwner(identity.getLoginName());
        getEntity().setSerial(codeService.getNewSerialNumber(JobApplication.class.getSimpleName()));
    }

    /**
     * Yeni Başvuru Adayı için quickPanelContect'i başvuru aday formuna ayarlar.
     */
    public void createApplicantQuickPanel() {
        quickRecordController.setName("applicantQuickRecord");
    }
    
    /**
     * NoteWidget için gerekli.
     *
     * @return
     */
    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getSerial());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }

    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase entityBase) {
        return FeatureUtils.getFeaturePointer(entityBase);
    }

}
