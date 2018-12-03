package com.ozguryazilim.tekir.recruit.jobapplication;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yusuf
 */
public class JobApplicationViewModel implements ViewModel, Serializable {

    private Long id;
    private Date date;
    private String owner;
    private String state;
    private String info;
    private JobAdvert advert;
    private Applicant applicant;
    private List<String> tags;

    public JobApplicationViewModel(
            Long id,
            Date date,
            String owner,
            String state,
            String info,
            JobAdvert advert,
            Applicant applicant,
            List<String> tags) {
        this.id = id;
        this.date = date;
        this.owner = owner;
        this.state = state;
        this.info = info;
        this.advert = advert;
        this.applicant = applicant;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public JobAdvert getAdvert() {
        return advert;
    }

    public void setAdvert(JobAdvert advert) {
        this.advert = advert;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

}
