/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
