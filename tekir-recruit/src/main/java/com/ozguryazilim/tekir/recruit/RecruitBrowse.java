package com.ozguryazilim.tekir.recruit;

import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.entities.JobAdvert_;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.SuggestionItem;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.StringListFilter;
import com.ozguryazilim.telve.query.filters.UserFilter;
import com.ozguryazilim.telve.suggestion.SuggestionRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author yusuf
 */
@Browse(feature = RecruitFeature.class)
public class RecruitBrowse extends BrowseBase<JobAdvert, RecruitViewModel> {

    @Inject
    private RecruitRepository recruitRepository;

    @Inject
    private Identity identity;
    
    @Inject
    private SuggestionRepository suggestionRepository;
    
    List<String> suggestionList;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<JobAdvert, RecruitViewModel> queryDefinition) {
        
        suggestionList=new ArrayList<>();
        List<SuggestionItem> si=(List<SuggestionItem>) suggestionRepository.findByGroupAndKey(JobAdvert.SUGGESTIONSTATUSGROUP, JobAdvert.SUGGESTIONSTATUSKEY);
        
        for (int i = 0; i < si.size(); i++) {
            suggestionList.add(si.get(i).getData());
        }
        
        queryDefinition
                .addFilter(new StringFilter<>(JobAdvert_.topic, "JobAdvert.label.Topic"))
                .addFilter(new DateFilter<>(JobAdvert_.startDate, "JobAdvert.label.startDate"))
                .addFilter(new DateFilter<>(JobAdvert_.endDate, "JobAdvert.label.endDate"))
                .addFilter(new TagFilter<>("skills", "JobAdvert.label.Skills", "JobAdvert"))
                .addFilter(new UserFilter<>(JobAdvert_.owner, "JobAdvert.label.Owner"))
                .addFilter(new StringFilter<>(JobAdvert_.info, "JobAdvert.label.Info"))
                .addFilter(new StringListFilter<>(JobAdvert_.status,suggestionList,"JobAdvert.label.Status",""));

        queryDefinition
                .addColumn(new LinkColumn<>(JobAdvert_.serial, "JobAdvert.label.Serial"), true)
                .addColumn(new LinkColumn<>(JobAdvert_.topic, "JobAdvert.label.Topic"), true)
                .addColumn(new TextColumn<>(JobAdvert_.info, "JobAdvert.label.Info"), true)
                .addColumn(new DateColumn<>(JobAdvert_.startDate, "JobAdvert.label.startDate"), true)
                .addColumn(new DateColumn<>(JobAdvert_.endDate, "JobAdvert.label.endDate"), true)
                .addColumn(new TextColumn<>(JobAdvert_.status, "JobAdvert.label.Status"), true);
    }
    @Override
    protected RepositoryBase<JobAdvert, RecruitViewModel> getRepository() {
        return recruitRepository;
    }
    public JobAdvert getJobAdvert() {
        if (getSelectedItem() != null) {
            return recruitRepository.findBy(getSelectedItem().getId());
        } else {
            return null;
        }
    }

}
