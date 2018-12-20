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
 * Territory için detay satır
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCO_TERRITORY_ITEM" )
public class TerritoryItem extends EntityBase{

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", foreignKey = @ForeignKey(name = "FK_TERIT_PARENT"))
    private Territory parent;
    
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID", foreignKey = @ForeignKey(name = "FK_TERIT_LOC"))
    private Location location;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Territory getParent() {
        return parent;
    }

    public void setParent(Territory parent) {
        this.parent = parent;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
}
