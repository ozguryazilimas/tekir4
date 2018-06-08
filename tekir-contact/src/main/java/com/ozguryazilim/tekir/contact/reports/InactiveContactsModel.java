package com.ozguryazilim.tekir.contact.reports;

import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;

public class InactiveContactsModel implements Serializable {

    private Contact contact;
    private String daysSinceLastMovement;

    public InactiveContactsModel(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDaysSinceLastMovement() {
        return daysSinceLastMovement;
    }

    public void setDaysSinceLastMovement(String daysSinceLastMovement) {
        this.daysSinceLastMovement = daysSinceLastMovement;
    }
}
