package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.telve.entities.ViewModel;
import java.util.List;

/**
 *
 * @author oyas
 */
public interface VoucherRepository<E extends VoucherBase, R extends ViewModel> {
    
    List<String> getOwnerFilter();

    void setOwnerFilter(List<String> ownerFilter);
}
