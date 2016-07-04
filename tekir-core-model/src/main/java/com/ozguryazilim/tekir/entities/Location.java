/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.TreeNodeEntityBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Adres koordinat sistemi için yer bilgisi.
 *
 *
 * Aşağıdaki biçinde veri tutulur :  
 * Türkiye ( Ülke ) 
 *      İstanbul ( Şehir ) 
 *         Beşiktaş ( İlçe ) 
 *              Levent ( Semt ) 
 *              Çarşı
 *          Kadıköy 
 *      Ankara 
 * USA ( Ülke ) 
 *      NewYork ( Eyalet ) 
 *          NewYork City ( Şehir )
 *
 * 
 * Root'nodelar alt kırılımın hangi tipler ile olabileceğini belirler. Pattern olarak alır.
 * 
 * NodeType'a göre icon gösterilir.
 * NodeType'lar : Ülke, Bölge, Eyalet, Şehir, İlçe, Semt
 * 
 * Perfomans için NamePath'de node üzerinde saklanabilir ( tersten ) : Levent - Beşiktaş - İstanbul - Türkiye
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCO_LOCATION" )
public class Location extends TreeNodeEntityBase<Location> {

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @Enumerated
    @Column( name = "TYPE")
    private LocationType type;
    
    //Lat-Lon biçminde? Bu sayede harita gösterilebilir?
    @Column( name = "LAT")
    private Double latitude;
    
    @Column( name = "LON")
    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    

}
