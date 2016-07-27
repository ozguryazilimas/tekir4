/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Toplantı aktivitesi
 * 
 * TODO: Bu aktivite aslında Apoinment ( Randevu ) kavramı ile ilişkili ve gene Takvim sistemi ile ( iCAL ) entegrasyon düşünmek lazım olabilir.
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("MEETING")
public class MeetingActivity extends Activity{
    
    /**
     * Toplantı nerede?
     */
    @Column(name = "LOCATION")
    private String location;
    
    /**
     * Toplantı kimlerle
     * TODO: Bunu bir liste olarak mı saklasak? Katılacak kişilerin isimleri, e-posta adresler v.b şeklinde?
     */
    @Column(name = "ATTENDEES")
    private String attendees;
    
    /**
     * Toplantı notları
     */
    @Column(name = "MEETING_MINUTES")
    private String meetingMinutes;
}
