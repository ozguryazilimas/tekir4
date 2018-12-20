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
