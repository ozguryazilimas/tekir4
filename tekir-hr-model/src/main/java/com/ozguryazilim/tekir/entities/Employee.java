package com.ozguryazilim.tekir.entities;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Çalışan veri modeli.
 * 
 * Contact -> AbstractPerson sınıfını miras alır diye düşündük ama Hibernate Bug'ı nedeniyle olmuyor. Bir birini miras alan Concrate sınıfları bulamıyor.
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends AbstractPerson{
    
    /**
     * Sicil No
     */
    @Column( name = "EMPLOYEE_NO")
    private String employeeNo;
    
    /**
     * Sosyal Güvenlik Numarası / SGK numarası
     */
    @Column( name = "SGK_NO")
    private String sgkNo;
    
    @Column( name = "SALARY_AMOUNT")
    private BigDecimal salaryAmount;
    
    @Column( name = "SALARY_CCY")
    private Currency salaryCurrency;
    
    /**
     * İşe Giriş Tarihi
     */
    @Column( name = "RECRUITMENT_DT")
    @Temporal(TemporalType.DATE)
    private Date recruitmentDate;
    
    /**
     * İşten Çıkış Tarihi
     */
    @Column( name = "TERMINATION_DT")
    @Temporal(TemporalType.DATE)
    private Date terminationDate;
    
    /**
     * Yıllık izin günü
     */
    @Column( name = "LEAVE_DAY")
    private Integer anualLeaveDay;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getSgkNo() {
        return sgkNo;
    }

    public void setSgkNo(String sgkNo) {
        this.sgkNo = sgkNo;
    }

    

    public BigDecimal getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(BigDecimal salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public Currency getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(Currency salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    

    public Date getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(Date recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public Integer getAnualLeaveDay() {
        return anualLeaveDay;
    }

    public void setAnualLeaveDay(Integer anualLeaveDay) {
        this.anualLeaveDay = anualLeaveDay;
    }
    
}
