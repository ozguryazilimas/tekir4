/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.filters;

import com.google.common.base.Splitter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.NumberFilter;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author oktay
 */
public class IntegerFilter<E> extends NumberFilter<E, Integer> {

	public IntegerFilter(SingularAttribute<? super E, Integer> attribute, String label) {
		super(attribute, label, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deserialize(String s) {
			 
		List<String> ls = Splitter.on(".").trimResults().splitToList(s);
        setOperand(FilterOperand.valueOf(ls.get(0)));
        setValue(new Integer(ls.get(1)));
		

		
	}
}
