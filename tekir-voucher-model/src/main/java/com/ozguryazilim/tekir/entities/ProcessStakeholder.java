package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
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
 * Bir process içerisinde ilgili kişiler.
 * 
 * Örneğin satış sürecinde, karar vericiler, teknik kişiler, muhasebe görevlisi v.b.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TVO_PROCESS_STAKEHOLDER")
public class ProcessStakeholder extends EntityBase{

    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "PROCESS_ID", foreignKey = @ForeignKey(name = "FK_STAKE_PROCESS"))
    private Process process;
    
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID", foreignKey = @ForeignKey(name = "FK_STAKE_CONTACT"))
    private Contact contact;
    
    @Column(name = "INFO")
    private String info;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
    
}
