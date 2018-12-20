package com.ozguryazilim.tekir.voucher.filter;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.telve.query.Operands;
import com.ozguryazilim.telve.query.filters.Filter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import java.util.Arrays;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.deltaspike.data.api.criteria.Criteria;

/**
 * Voucher State Type üzerinden filtreleme yapar.
 *
 * @author Hakan Uygun
 */
public class VoucherStateTypeFilter<E> extends Filter<E, VoucherState, VoucherStateType> {

    public VoucherStateTypeFilter(SingularAttribute<? super E, VoucherState> attribute, String label) {
        super(attribute, label);

        setOperands(Operands.getEnumOperands());
        setOperand(FilterOperand.All);
        setValue(VoucherStateType.OPEN);
    }

    @Override
    public void decorateCriteria(Criteria<E, ?> criteria) {
        if (getValue() != null) {
            switch (getOperand()) {
                case Equal:
                    //criteria.eq(getAttribute(), getValue());
                    break;
                case NotEqual:
                    //criteria.notEq(getAttribute(), getValue());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void decorateCriteriaQuery(List<Predicate> predicates, CriteriaBuilder builder, Root<E> from) {
        if (getValue() != null) {
            switch (getOperand()) {
                case Equal:
                    predicates.add(builder.like(from.get(getAttribute()).as(String.class), getValue().toString() + "-%"));
                    break;
                case NotEqual:
                    predicates.add(builder.notLike(from.get(getAttribute()).as(String.class), getValue().toString() + "-%"));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String getTemplate() {
        return "voucherStateTypeFilter";
    }

    @Override
    public String serialize() {
        return Joiner.on("::").join(getOperand(), getValue().toString());
    }

    @Override
    public void deserialize(String s) {
        List<String> ls = Splitter.on("::").trimResults().splitToList(s);
        setOperand(FilterOperand.valueOf(ls.get(0)));
        setValue(VoucherStateType.valueOf(ls.get(1)));
    }
    
    public List<VoucherStateType> getTypes(){
        return Arrays.asList(VoucherStateType.values());
    }

}
