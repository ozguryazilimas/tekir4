/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.columns;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.telve.messages.Messages;
import com.ozguryazilim.telve.query.columns.Column;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.metamodel.Attribute;

import org.apache.commons.beanutils.BeanUtils;

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
    
    @Override
    public void export(E e, Writer doc)
    		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
    	// TODO Auto-generated method stub
    	   doc.write("\"");
           String val = BeanUtils.getProperty(e, getName());
           if( !Strings.isNullOrEmpty(val)){
               doc.write( Messages.getMessage( "voucherState.name." + val ));
           }
           doc.write("\"");
    }
}
