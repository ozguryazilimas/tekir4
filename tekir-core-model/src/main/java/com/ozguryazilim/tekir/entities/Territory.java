package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.ParamEntityBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Bölge tanımları.
 * 
 * Contactların hangi bölgeye dahil olduğu.
 * 
 * Bu bölgede detay olarak Kapsam : List<Location> şeklinde olabilir.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCO_TERRITORY" )
public class Territory extends ParamEntityBase{

    
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TerritoryItem> items = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TerritoryItem> getItems() {
        return items;
    }

    public void setItems(List<TerritoryItem> items) {
        this.items = items;
    }
    
    
    
}
