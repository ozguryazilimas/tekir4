package com.ozguryazilim.tekir.recruit.jobapplication.evaluationnotes;

import com.ozguryazilim.tekir.entities.EvaluationNote;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.tekir.recruit.jobapplication.information.AbstractJobApplicationInformationEditor;
import com.ozguryazilim.tekir.recruit.jobapplication.information.JobApplicationInformationEditor;
import com.ozguryazilim.tekir.recruit.jobapplication.information.JobApplicationInformationRepositoryBase;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.EntityBase;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;

/**
 *
 * @author serdar
 */
@JobApplicationInformationEditor(page = RecruitPages.JobApplicationPages.EvaluationNotesEditor.class)
public class EvaluationNoteEditor extends AbstractJobApplicationInformationEditor<EvaluationNote> {

    @Inject
    private EvaluationNoteRepository evaluationNotesRepository;

    @Inject
    private Identity identity;

    @Override
    protected JobApplicationInformationRepositoryBase<EvaluationNote, ?> getRepository() {
        return evaluationNotesRepository;
    }

    @Override
    public String getIcon(EvaluationNote entity) {
        return "fa-briefcase";
    }

    @Override
    public void setJobApplicationOfEntity(JobApplication jobApplication) {
        getEntity().setOwner(identity.getLoginName());
        getEntity().setApplication(jobApplication);
    }

    public void editEvaluation() {

        getEntityList().forEach(en -> {
            if (en.getOwner().equals(identity.getLoginName())) {
                edit(en);
            }
        });
    }

}
