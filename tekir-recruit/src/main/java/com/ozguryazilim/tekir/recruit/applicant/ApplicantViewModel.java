package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.contact.ContactViewModel;
import com.ozguryazilim.tekir.entities.Contact;
import java.util.List;

/**
 *
 * @author yusuf
 */
public class ApplicantViewModel extends ContactViewModel {

    private List<String> skills;
    private List<String> classifications;
    private Boolean militaryDuty;
    private Integer rating;
    private Boolean married;

    public ApplicantViewModel(Long id, String code, String name, List<String> skills, List<String> classifications,
            Boolean militaryDuty, Integer rating, Boolean married, String info, Boolean active, Class<? extends Contact> type, Long pmMobileId, 
    		String primaryMobileaddress, Long pmPhoneId, String primaryPhoneaddress, Long pmEmailId, String primaryEmailaddress) {
        super(id, code, name, info, active, type, pmMobileId, primaryMobileaddress, pmPhoneId, primaryPhoneaddress, pmEmailId, primaryEmailaddress);
        this.skills = skills;
        this.classifications = classifications;
        this.militaryDuty = militaryDuty;
        this.rating = rating;
        this.married = married;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<String> classifications) {
        this.classifications = classifications;
    }

    public Boolean getMilitaryDuty() {
        return militaryDuty;
    }

    public void setMilitaryDuty(Boolean militaryDuty) {
        this.militaryDuty = militaryDuty;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

}
