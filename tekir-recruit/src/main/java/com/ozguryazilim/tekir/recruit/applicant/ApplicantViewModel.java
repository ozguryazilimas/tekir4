package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yusuf
 */
public class ApplicantViewModel implements ViewModel,Serializable{

    private Long id;
    private List<String> skills;
    private List<String> classifications;
    private Boolean militaryDuty;
    private Integer rating;
    private Boolean married;

    public ApplicantViewModel(Long id,List<String> skills, List<String> classifications, Boolean militaryDuty, Integer rating, Boolean married) {
        this.id=id;
        this.skills = skills;
        this.classifications = classifications;
        this.militaryDuty = militaryDuty;
        this.rating = rating;
        this.married = married;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
