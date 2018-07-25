package com.ozguryazilim.tekir.recruit;

import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.entities.JobAdvert_;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;
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

    @Override
    protected void buildQueryDefinition(QueryDefinition<JobAdvert, RecruitViewModel> queryDefinition) {
        queryDefinition
                .addFilter(new StringFilter<>(JobAdvert_.info, "general.label.Info"))
                .addFilter(new StringFilter<>(JobAdvert_.topic, "general.label.Topic"))
                .addFilter(new DateFilter<>(JobAdvert_.startDate, "general.label.startDate"))
                .addFilter(new DateFilter<>(JobAdvert_.endDate, "general.label.endDate"))
                .addFilter(new TagFilter("skills", "general.label.Skills", "Recruit"))
                .addFilter(new StringFilter<>(JobAdvert_.owner, "general.label.Owner"))
                .addFilter(new StringFilter<>(JobAdvert_.status, "general.label.Status"));

        queryDefinition
                .addColumn(new TextColumn<>(JobAdvert_.serial, "general.label.Serial"), true)
                .addColumn(new TextColumn<>(JobAdvert_.topic, "general.label.Topic"), true)
                .addColumn(new TextColumn<>(JobAdvert_.info, "general.label.Info"), true)
                .addColumn(new DateColumn<>(JobAdvert_.startDate, "general.label.startDate"), true)
                .addColumn(new DateColumn<>(JobAdvert_.endDate, "general.label.endDate"), true)
                .addColumn(new TextColumn<>(JobAdvert_.status, "general.label.Status"), true);
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
