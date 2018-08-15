package com.ozguryazilim.tekir.recruit.jobapplication;

import com.ozguryazilim.tekir.entities.Applicant_;
import com.ozguryazilim.tekir.entities.JobAdvert_;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.entities.JobApplication_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.SuggestionItem;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.SubLinkColumn;
import com.ozguryazilim.telve.query.columns.SubDateColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringListFilter;
import com.ozguryazilim.telve.query.filters.SubDateFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;
import com.ozguryazilim.telve.query.filters.UserFilter;
import com.ozguryazilim.telve.suggestion.SuggestionRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author yusuf
 */
@Browse(feature = JobApplicationFeature.class)
public class JobApplicationBrowse extends BrowseBase<JobApplication, JobApplicationViewModel> {

    @Inject
    private JobApplicationRepository jobApplicationRepository;
    
    @Inject
    private SuggestionRepository suggestionRepository;
    
    List<String> suggestionList;


    @Override
    protected void buildQueryDefinition(QueryDefinition<JobApplication, JobApplicationViewModel> queryDefinition) {
        
        suggestionList=new ArrayList<>();
        List<SuggestionItem> si=(List<SuggestionItem>) suggestionRepository.findByGroupAndKey(JobApplication.SUGGESTIONSTATUSGROUP, JobApplication.SUGGESTIONSTATUSKEY);
        
        for (int i = 0; i < si.size(); i++) {
            suggestionList.add(si.get(i).getData());
        }

        queryDefinition
                .addFilter(new SubStringFilter<>(JobApplication_.advert,JobAdvert_.topic,"JobAdvert.label.Topic"))
                .addFilter(new DateFilter<>(JobApplication_.date,"JobApplication.label.Date",FilterOperand.All, DateValueType.LastMonth))
                .addFilter(new SubDateFilter<>(JobApplication_.advert,JobAdvert_.startDate,"JobAdvert.label.startDate",FilterOperand.All, DateValueType.LastMonth))
                .addFilter(new SubDateFilter<>(JobApplication_.advert,JobAdvert_.endDate,"JobAdvert.label.endDate",FilterOperand.All, DateValueType.LastMonth))
                .addFilter(new SubStringFilter<>(JobApplication_.applicant,Applicant_.name,"JobApplication.label.Name"))
                .addFilter(new UserFilter<>(JobApplication_.owner, "JobAdvert.label.Owner"))
                .addFilter(new StringListFilter<>(JobApplication_.state,suggestionList,"JobApplication.label.State",""));
        
        queryDefinition
                .addColumn(new SubTextColumn<>(JobApplication_.advert,JobAdvert_.topic,"JobAdvert.label.Topic"),true)
                .addColumn(new SubLinkColumn<>(JobApplication_.applicant,Applicant_.name,"JobApplication.label.Name"),true)
                .addColumn(new SubDateColumn<>(JobApplication_.advert,JobAdvert_.startDate,"JobAdvert.label.startDate"),true)
                .addColumn(new SubDateColumn<>(JobApplication_.advert,JobAdvert_.endDate,"JobAdvert.label.endDate"),true)
                .addColumn(new DateColumn<>(JobApplication_.date,"JobApplication.label.Date"),true)
                
                
              ;
                
    }

    @Override
    protected RepositoryBase<JobApplication, JobApplicationViewModel> getRepository() {
        return jobApplicationRepository;
    }

    public JobApplication getJobApplication() {
        if (getSelectedItem() != null) {
            return jobApplicationRepository.findBy(getSelectedItem().getId());
        } else {
            return null;
        }
    }
}
