/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherState;

/**
 * Voucher State'leri değişirken fırlatılacak CDI event sınıfı.
 * 
 * @author Hakan Uygun
 */
public class VoucherStateChange {
    
    private VoucherState from;
    private VoucherStateAction action;
    private VoucherState to;
    private VoucherBase payload;

    public VoucherStateChange(VoucherState from, VoucherStateAction action, VoucherState to, VoucherBase payload) {
        this.from = from;
        this.action = action;
        this.to = to;
        this.payload = payload;
    }

    public VoucherState getFrom() {
        return from;
    }

    public VoucherStateAction getAction() {
        return action;
    }

    public VoucherState getTo() {
        return to;
    }

    public VoucherBase getPayload() {
        return payload;
    }
            
    
}
