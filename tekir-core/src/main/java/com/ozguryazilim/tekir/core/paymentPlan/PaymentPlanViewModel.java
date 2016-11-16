/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.paymentPlan;

import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;

/**
 *
 * @author oyas
 */
public class PaymentPlanViewModel implements ViewModel, Serializable{
    
    private Long id;
    private String code;
    private String name;

    public PaymentPlanViewModel(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
}
