package com.ozguryazilim.tekir.recruit.jobapplication.evaluationnotes;

import com.ozguryazilim.tekir.entities.EvaluationNote;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.recruit.jobapplication.information.JobApplicationInformationRepositoryBase;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author serdar
 */
@Dependent
@Repository
public abstract class EvaluationNoteRepository
        extends JobApplicationInformationRepositoryBase<EvaluationNote, EvaluationNote> {

    public boolean findByApplicationAndOwner(JobApplication application, String owner) {
        return typedQuery("select c from EvaluationNote c where application = ?1 and owner =?2")
                .setParameter(1, application)
                .setParameter(2, owner)
                .getResultList().isEmpty();
    }

}
