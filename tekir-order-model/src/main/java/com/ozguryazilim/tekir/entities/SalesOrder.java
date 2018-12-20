package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Satış Siparişi
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("SALES")
public class SalesOrder extends Order{
    
}
