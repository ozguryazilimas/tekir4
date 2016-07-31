/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Alış Siparişi
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TPO_PURCHASE_ORDER")
public class PurchaseOrder extends Order{
    
}
