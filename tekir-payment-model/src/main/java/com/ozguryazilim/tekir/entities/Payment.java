/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
