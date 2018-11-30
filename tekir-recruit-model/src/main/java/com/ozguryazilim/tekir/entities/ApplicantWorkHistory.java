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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author oyas
 */
@Entity
@Table( name = "TRC_WORK_HISTORY" )
public class ApplicantWorkHistory extends AuditBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    /**
     * Bu eğitim bilgisinin kime ait olduğu
     */
    @ManyToOne
    @JoinColumn(name = "APPLICANT_ID", foreignKey = @ForeignKey(name = "FK_EDU_APPLICANT"))
    private Applicant applicant;

    
    /**
     * İş yeri adı
     */
    @Column(name = "NAME")
    private String name;
    
    /**
     * Bu iş yerinde yapılan görev/iş
     */
    @Column(name = "JOB")
    private String job;
    
    /**
     * Bu iş yerinde sahip olunan ünvan
     */
    @Column(name = "TITLE")
    private String title;
    
    /**
     * Ek açıklama
     */
    @Column(name = "INFO")
    private String info;

    
    @Column( name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column( name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    /**
     * Buradaki bilgiler suggestion'dan alınarak getirilebilir.
     * STAJ, İş, Gönüllü v.b.
     */
    @Enumerated(EnumType.STRING)
    @Column( name = "WORK_TYPE")
    private ApplicantWorkType type;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ApplicantWorkType getType() {
        return type;
    }

    public void setType(ApplicantWorkType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.applicant);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.job);
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.info);
        hash = 97 * hash + Objects.hashCode(this.startDate);
        hash = 97 * hash + Objects.hashCode(this.endDate);
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApplicantWorkHistory other = (ApplicantWorkHistory) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.info, other.info)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.applicant, other.applicant)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        return true;
    }
    
}
