/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherState;

/**
 * Voucher Owner'lari değişirken fırlatılacak CDI event sınıfı.
 * 
 * @author Ceyhun Onur
 */
public class VoucherOwnerChange {
    
    private String from;
    private String to;
    private VoucherBase payload;

    public VoucherOwnerChange(String from, String to, VoucherBase payload) {
        this.from = from;
        this.to = to;
        this.payload = payload;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public VoucherBase getPayload() {
        return payload;
    }            
    
}
