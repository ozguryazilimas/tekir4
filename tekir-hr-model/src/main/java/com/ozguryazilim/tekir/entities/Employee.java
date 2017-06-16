/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Contact -> Person sınıfını miras alır diye düşündük ama Hibernate Bug'ı nedeniyle olmuyor. Bir birini miras alan Concrate sınıfları bulamıyor.
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Contact{
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "SECOND_NAME")
    private String secondName;
    
    @Column(name = "LAST_NAME")
    private String lastName;
    
    /**
     * Kullandığı isim. Bazı kişilerin birden fazla adı olabiliyor ve bunlardan birini tercih edebiliyor
     * Burada acaba hitap şekli ile birlikte bir yöntem mi izlesek? Mr. Ms. v.b.?
     */
    @Column(name = "USE_NAME")
    private String useName;
    
    
    /**
     * Bireysel contactlar için
     * TC Kimlik Numarası / Sosyal Güvenlik Numarası / Pasaport Numarası v.b.
     */
    @Column(name = "SSN")
    private String ssn;
            
    /**
     * Bireyler için iş ünvanı / çalıştığı yerdeki pozisyonu
     *
     */
    @Column(name = "JOB_TITLE")
    private String jobTitle;
            
    
    @Column(name = "GENDER")
    private Gender gender = Gender.UNKNOWN;
    
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    
}
