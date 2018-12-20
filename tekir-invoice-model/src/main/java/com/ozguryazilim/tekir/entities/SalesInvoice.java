package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author oyas
 */
@Entity
@DiscriminatorValue("SALES")
public class SalesInvoice extends Invoice{
    
}
