package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.EntityBase;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Bir süreç içindeki fişleri bir birine bağlamak için kullanılır.
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TVO_PROCESS")
public class Process extends EntityBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name="PROCESS_NO", length=30, nullable=false, unique=true)
    @NotNull @Size(max = 30)
    @BizKey
    private String processNo;
    
    @Column(name = "TOPIC")
    private String topic;
    
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_PROCESS_ACC"))
    private Contact account;
    
    @Enumerated(EnumType.STRING)
    @Column( name="TYPE")
    private ProcessType type;
    
    @Enumerated(EnumType.STRING)
    @Column( name="STATUS")
    private ProcessStatus status;
    
    /**
     * Bu process ile ilişkili açık belgeler bir artırır kapandıkların da bir eksiltir.
     */
    @Column( name="COUNTER")
    private Integer counter = 0;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }

    public ProcessType getType() {
        return type;
    }

    public void setType(ProcessType type) {
        this.type = type;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    
    
}
