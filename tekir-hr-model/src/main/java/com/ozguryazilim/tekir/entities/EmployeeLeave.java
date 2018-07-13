/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.util.Date;
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
 * Çalışan İzin Fişi modeli
 * @author Hakan Uygun
 */
@Entity
@Table(name = "THR_LEAVE")
public class EmployeeLeave extends VoucherBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(name = "FK_LEAVE_EMP"))
    private Employee employee;
    
    /**
     * Ücretli izin mi?
     */
    @Column(name = "PAID")
    private Boolean paid = Boolean.TRUE;
    
    /**
     * Yıllık izinden düşülecek mi?
     */
    @Column(name = "ANNUAL")
    private Boolean annual = Boolean.TRUE;
    
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    /**
     * Kaç gün izinli? Eğer araya hafta sonu giriyor ise başlangıç bitiş tarihi anlamsız oluyor.
     * Yarım gün izinleri ne yapsak? 1 gün yerine 2 yarım gün şeklinde girsek mi?
     * Yoksa float mı tutsak değeri?
     */
    @Column(name = "LEAVE_DAY")
    private Integer leaveDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getAnnual() {
        return annual;
    }

    public void setAnnual(Boolean annual) {
        this.annual = annual;
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

    public Integer getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }
    
    
    
}
