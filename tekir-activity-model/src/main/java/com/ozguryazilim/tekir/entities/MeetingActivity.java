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
     * Bunları liste yaptıptığımız gibi bizde kaydı varsa eşleştirmemiz lazım.
     * Ya da kaldıralım sadece mention kullanalım
     */
    @Column(name = "ATTENDEES")
    private String attendees;
    
    /**
     * Toplantı notları
     */
    @Column(name = "MEETING_MINUTES")
    private String meetingMinutes;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public String getMeetingMinutes() {
        return meetingMinutes;
    }

    public void setMeetingMinutes(String meetingMinutes) {
        this.meetingMinutes = meetingMinutes;
    }
    
    
}
