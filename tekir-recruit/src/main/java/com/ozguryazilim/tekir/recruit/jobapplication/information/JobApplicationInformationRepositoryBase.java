package com.ozguryazilim.tekir.recruit.jobapplication.information;

import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.ViewModel;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

import java.util.List;
import org.apache.deltaspike.data.api.Query;
/**
 *
 * @author serdar
 */
public abstract class JobApplicationInformationRepositoryBase<E extends EntityBase, R extends ViewModel>
        extends RepositoryBase<E, R>
        implements CriteriaSupport<E> {
    
    @Query("select c from EvaluationNote c where application = ?1")
    public abstract List<E> findByJobApplication(JobApplication application);

    @Query("delete c from EvaluationNote c where application = ?1")
    public abstract void deleteByJobApplication(JobApplication jobApplication);

}
