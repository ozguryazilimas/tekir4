package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Yapılan Ödemeler ( Tediye )
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("DEBIT")
public class Payment extends PaymentBase{
    
}
