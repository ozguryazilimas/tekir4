package com.ozguryazilim.tekir.payment;

import com.ozguryazilim.tekir.entities.VoucherMatchable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author oyas
 */
public class PaymentMatcherViewModel {
    
    private VoucherMatchable matchable;
    private BigDecimal amount;
    private BigDecimal oldAmount;

    public PaymentMatcherViewModel(VoucherMatchable matchable, BigDecimal amount) {
        this.matchable = matchable;
        this.amount = amount;
        this.oldAmount = BigDecimal.ZERO;
    }
    
    public PaymentMatcherViewModel(VoucherMatchable matchable, BigDecimal amount, BigDecimal oldAmount) {
        this.matchable = matchable;
        this.amount = amount;
        this.oldAmount = oldAmount;
    }

    public VoucherMatchable getMatchable() {
        return matchable;
    }

    public void setMatchable(VoucherMatchable matchable) {
        this.matchable = matchable;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.matchable);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaymentMatcherViewModel other = (PaymentMatcherViewModel) obj;
        if (!Objects.equals(this.matchable, other.matchable)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
