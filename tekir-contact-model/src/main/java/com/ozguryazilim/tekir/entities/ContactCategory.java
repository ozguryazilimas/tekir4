package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.TreeNodeEntityBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contact'ları gruplamak için ağaç yapısında bir kategorizasyon
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCC_CONTACT_CAT" )
public class ContactCategory extends TreeNodeEntityBase<ContactCategory>{

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
