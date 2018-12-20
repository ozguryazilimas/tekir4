package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Alınan ödeme ( Tahsilat )
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("CREDIT")
public class PaymentReceived extends PaymentBase{
    
}
