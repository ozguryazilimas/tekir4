package com.ozguryazilim.tekir.core.filters;

import com.google.common.base.Splitter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.NumberFilter;

import java.util.List;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author oktay
 */
public class IntegerFilter<E> extends NumberFilter<E, Integer> {

	public IntegerFilter(SingularAttribute<? super E, Integer> attribute, String label) {
		super(attribute, label, 0);
	}

	@Override
	public void deserialize(String s) {
			 
		List<String> ls = Splitter.on(".").trimResults().splitToList(s);
        setOperand(FilterOperand.valueOf(ls.get(0)));
        setValue(new Integer(ls.get(1)));
		

		
	}
}
