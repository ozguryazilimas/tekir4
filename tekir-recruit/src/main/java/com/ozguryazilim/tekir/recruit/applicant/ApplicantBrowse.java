package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.Applicant_;
import com.ozguryazilim.tekir.entities.ContactEMail_;
import com.ozguryazilim.tekir.entities.ContactPhone_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.BooleanColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.filters.BooleanFilter;
import com.ozguryazilim.telve.query.filters.RatingFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;
import javax.inject.Inject;

/**
 *
 * @author yusuf
 */
@Browse(feature=ApplicantFeature.class)
public class ApplicantBrowse extends BrowseBase<Applicant, ApplicantViewModel>{

    @Inject
    private ApplicantRepository applicantRepository;
    
    private Integer ratingLength=5;
      
    @Override
    protected void buildQueryDefinition(QueryDefinition<Applicant, ApplicantViewModel> queryDefinition) {
        queryDefinition
                .addFilter(new StringFilter<>(Contact_.code, "general.label.Code"))
                .addFilter(new StringFilter<>(Contact_.name, "general.label.Name"))
                .addFilter(new TagFilter<>("skills", "Applicant.label.Skills", "Applicant"))
                .addFilter(new TagFilter<>("classifications", "Applicant.label.Classifications", "Applicant"))
                .addFilter(new BooleanFilter<>(Applicant_.married, "Applicant.label.Married", "Applicant.Married."))
                .addFilter(new RatingFilter<>(Applicant_.rating, "Applicant.label.Rating", 0, ratingLength));

        queryDefinition
                .addColumn(new LinkColumn<>(Contact_.code, "general.label.Code"), true)
                .addColumn(new LinkColumn<>(Contact_.name, "general.label.Name"), true)
                .addColumn(new BooleanColumn<>(Applicant_.married, "Applicant.label.Married", "Applicant.Married."), true)
                .addColumn(new SubTextColumn<>(Contact_.primaryMobile, ContactPhone_.address, "contact.label.PrimaryMobile"), true)
                .addColumn(new SubTextColumn<>(Contact_.primaryPhone, ContactPhone_.address, "contact.label.PrimaryPhone"), true)
                .addColumn(new SubTextColumn<>(Contact_.primaryEmail, ContactEMail_.address, "contact.label.PrimaryEmail"), true);
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

    public Integer getRatingLength() {
        return ratingLength;
    }

    public void setRatingLength(Integer ratingLength) {
        this.ratingLength = ratingLength;
    }

     
}
