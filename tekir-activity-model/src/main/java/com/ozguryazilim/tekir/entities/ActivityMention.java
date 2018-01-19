/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table( name = "TCA_ACT_MENTION" )
public class ActivityMention extends EntityBase{
  
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;

    @Embedded
    private FeaturePointer featurePointer = new FeaturePointer();

    @ManyToOne
    @JoinColumn(name = "ACTIVITY_ID", foreignKey = @ForeignKey(name = "FK_ACTIVITY_FEED"))
    private Activity activity;
    
    /**
     * Mention'ın taşıdığı özel bir davranış hali.
     * 
     * Örneğin : PRIMARY | NORMAL 
     * 
     * Primary değerler master'da olacak.
     */
    @Column(name="TYPE")
    private String type = "NORMAL";

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeaturePointer getFeaturePointer() {
        return featurePointer;
    }

    public void setFeaturePointer(FeaturePointer featurePointer) {
        this.featurePointer = featurePointer;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    
}
