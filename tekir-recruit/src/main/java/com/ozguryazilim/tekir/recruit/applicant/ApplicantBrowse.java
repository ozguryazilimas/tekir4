package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.Applicant_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.BooleanColumn;
import com.ozguryazilim.telve.query.columns.EnumColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.BooleanFilter;
import com.ozguryazilim.telve.query.filters.RatingFilter;
import javax.inject.Inject;

/**
 *
 * @author yusuf
 */
@Browse(feature=ApplicantFeature.class)
public class ApplicantBrowse extends BrowseBase<Applicant, ApplicantViewModel>{

    @Inject
    private ApplicantRepository applicantRepository;
      
    @Override
    protected void buildQueryDefinition(QueryDefinition<Applicant, ApplicantViewModel> queryDefinition) {
        queryDefinition
                .addFilter(new TagFilter<>("skills", "Applicant.label.Skills", "Applicant"))
                .addFilter(new TagFilter<>("classifications", "Applicant.label.Classifications", "Applicant"))
                .addFilter(new BooleanFilter<>(Applicant_.married, "Applicant.label.Married", "Married."))
                .addFilter(new BooleanFilter<>(Applicant_.militaryDuty, "Applicant.label.militaryDuty", "militaryDuty."))             
                .addFilter(new RatingFilter<>(Applicant_.rating,"Applicant.label.Rating",0,5));                       
                
        queryDefinition
                .addColumn(new TextColumn<>(Applicant_.firstName, "Applicant.label.FirstName"),true)
                .addColumn(new TextColumn<>(Applicant_.lastName, "Applicant.label.LastName"),true)
                .addColumn(new BooleanColumn<>(Applicant_.married,"Applicant.label.Married","Married."),true)
                .addColumn(new BooleanColumn<>(Applicant_.militaryDuty,"Applicant.label.militaryDuty","militaryDuty."),true)
                .addColumn(new EnumColumn(Applicant_.gender,"Applicant.label.Gender","gender."),true)
                ;
    }

    @Override
    protected RepositoryBase<Applicant, ApplicantViewModel> getRepository() {
        return applicantRepository;
    }
    
     public Applicant getApplicant() {
        if (getSelectedItem() != null) {
            return applicantRepository.findBy(getSelectedItem().getId());
        } else {
            return null;
        }
    }     
}
