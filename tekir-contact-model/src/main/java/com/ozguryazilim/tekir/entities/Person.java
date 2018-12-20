package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author oyas
 */
@Entity
@DiscriminatorValue("PERSON")
public class Person extends AbstractPerson{
    
}
