package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.AuditBase;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Askerlik bilgileri
 */
@Entity
@Table( name = "TRC_MILITARY_SERVICE" )
public class ApplicantMilitaryService extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    /**
     * Askerlik bilgilerinin ait olduğu aday
     */
    @OneToOne
    @JoinColumn(name = "APPLICANT_ID", foreignKey = @ForeignKey(name = "FK_MS_APPLICANT"))
    private Applicant applicant;

    /**
     * Askerlik durumu
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ApplicantMilitaryServiceStatus status = ApplicantMilitaryServiceStatus.EXEMPT;

    /**
     * Ek açıklama
     */
    @Column(name = "INFO")
    private String info;

    /**
     * Ne zamana kadar tecilli
     */
    @Column(name = "POSTPONED_TO")
    @Temporal(TemporalType.DATE)
    private Date postponedTo;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public ApplicantMilitaryServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicantMilitaryServiceStatus status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getPostponedTo() {
        return postponedTo;
    }

    public void setPostponedTo(Date postponedTo) {
        this.postponedTo = postponedTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicantMilitaryService)) return false;
        if (!super.equals(o)) return false;
        ApplicantMilitaryService that = (ApplicantMilitaryService) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getApplicant(), that.getApplicant()) &&
                getStatus() == that.getStatus() &&
                Objects.equals(getInfo(), that.getInfo()) &&
                Objects.equals(getPostponedTo(), that.getPostponedTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getApplicant(), getStatus(), getInfo(), getPostponedTo());
    }

}
