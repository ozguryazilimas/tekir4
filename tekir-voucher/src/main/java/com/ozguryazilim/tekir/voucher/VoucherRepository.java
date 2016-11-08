/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
