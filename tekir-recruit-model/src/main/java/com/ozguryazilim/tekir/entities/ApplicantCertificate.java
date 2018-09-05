package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.AuditBase;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table( name = "TRC_CERTIFICATE" )
public class ApplicantCertificate extends AuditBase{
    
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
     * Sertifika adı. RH Hede sertifikası. Burada Suggestion kullanılabilir belki.
     */
    @Column(name = "NAME")
    private String name;
    
    /**
     * Ek açıklama
     */
    @Column(name = "INFO")
    private String info;

    /**
     * Buradaki bilgiler suggestion'dan alınarak getirilebilir.
     * Katılım, Sınav sonucu v.b.
     */
    @Column( name = "CERT_TYPE")
    private String type;
    
    /**
     * Sertifikanın alındığı tarih
     */
    @Column( name = "ISSUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.applicant);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.info);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + Objects.hashCode(this.issueDate);
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
        final ApplicantCertificate other = (ApplicantCertificate) obj;
        if (!Objects.equals(this.name, other.name)) {
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
        if (!Objects.equals(this.issueDate, other.issueDate)) {
            return false;
        }
        return true;
    }
    
}
