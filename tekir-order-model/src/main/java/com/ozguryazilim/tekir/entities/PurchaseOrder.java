package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Alış Siparişi
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("PURCHASE")
public class PurchaseOrder extends Order{
    
}
