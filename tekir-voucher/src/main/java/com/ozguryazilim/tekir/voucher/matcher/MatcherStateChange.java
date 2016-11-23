/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.matcher;

import com.ozguryazilim.tekir.entities.VoucherMatchable;

/**
 *
 * @author oyas
 */
public class MatcherStateChange {
    
    private VoucherMatchable matchable;

    public MatcherStateChange(VoucherMatchable matchable) {
        this.matchable = matchable;
    }

    public VoucherMatchable getMatchable() {
        return matchable;
    }

    public void setMatchable(VoucherMatchable matchable) {
        this.matchable = matchable;
    }
    
    
}
