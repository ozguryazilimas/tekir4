/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.columns;

import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.telve.query.columns.Column;
import javax.persistence.metamodel.Attribute;

/**
 *
 * @author oyas
 * @param <E>
 */
public class VoucherStateColumn<E> extends Column<E> {

    public VoucherStateColumn(Attribute<? super E, VoucherState> attribute, String labelKey) {
        super(attribute, labelKey);
    }

    @Override
    public String getTemplate() {
        return "voucherStateColumn";
    }
    
}
