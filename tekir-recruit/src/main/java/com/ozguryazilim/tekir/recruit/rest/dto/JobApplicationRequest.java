package com.ozguryazilim.tekir.recruit.rest.dto;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantCertificate;
import com.ozguryazilim.tekir.entities.ApplicantEducation;
import com.ozguryazilim.tekir.entities.ApplicantMilitaryService;
import com.ozguryazilim.tekir.entities.ApplicantWorkHistory;

import java.io.Serializable;
import java.util.List;

public class JobApplicationRequest implements Serializable{

    private Long advertId;

    private Applicant applicant;
    private ApplicantMilitaryService militaryObligation;
    private List<ApplicantEducation> education;
    private List<ApplicantWorkHistory> workHistory;
    private List<ApplicantCertificate> certificates;

    public Long getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Long advertId) {
        this.advertId = advertId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public ApplicantMilitaryService getMilitaryObligation() {
        return militaryObligation;
    }

    public void setMilitaryObligation(ApplicantMilitaryService militaryObligation) {
        this.militaryObligation = militaryObligation;
    }

    public List<ApplicantEducation> getEducation() {
        return education;
    }

    public void setEducation(List<ApplicantEducation> education) {
        this.education = education;
    }

    public List<ApplicantWorkHistory> getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(List<ApplicantWorkHistory> workHistory) {
        this.workHistory = workHistory;
    }

    public List<ApplicantCertificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<ApplicantCertificate> certificates) {
        this.certificates = certificates;
    }
}
