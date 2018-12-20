package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author oyas
 */
@Entity
@Table(name = "THR_SALARY_NOTE_ITEM")
public class SalaryNoteItem extends EntityBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "MASTER_ID", foreignKey = @ForeignKey(name = "FK_SALIT_SAL"))
    private SalaryNote master;
    
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(name = "FK_SALARY_EMP"))
    private Employee employee;
    
    @Column(name = "INFO")
    private String info;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalaryNote getMaster() {
        return master;
    }

    public void setMaster(SalaryNote master) {
        this.master = master;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
}
