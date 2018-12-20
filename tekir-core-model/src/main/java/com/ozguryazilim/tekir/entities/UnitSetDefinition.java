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
 * Sistemde kullanılacak olan Birim Kümeleri
 * 
 * 
 * Her birim kümesinin bir adı ve taban birimi olacak.
 * 
 * Detay olarak da bu taban birim üzerinden ek birim tanımları gerçekleşecek.
 * 
 * Default data olarak bazı tanımlar sistem içerisinde bulunacak örneğin ağırlık, uzunluk, hacim gibi olanlar.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCO_UNIT_SET" )
public class UnitSetDefinition extends ParamEntityBase{
    
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;

    @Column( name = "BASE_UNIT")
    private String baseUnit;
    
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UnitSetItem> items = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public List<UnitSetItem> getItems() {
        return items;
    }

    public void setItems(List<UnitSetItem> items) {
        this.items = items;
    }
    
    
}
