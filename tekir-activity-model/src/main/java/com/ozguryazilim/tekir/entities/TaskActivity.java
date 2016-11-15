/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Görev activitesi.
 * 
 * Aslında bu activite türü, gerçek bir iletişim şeysi değil. Nasıl yapsak?
 * 
 * TODO: Kavram olarak daha sonra implemente edelim.
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("TASK")
public class TaskActivity extends Activity{
    
}
