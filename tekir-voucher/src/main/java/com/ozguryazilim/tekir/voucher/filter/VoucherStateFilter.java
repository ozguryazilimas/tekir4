package com.ozguryazilim.tekir.voucher.filter;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.telve.query.Operands;
import com.ozguryazilim.telve.query.filters.Filter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.deltaspike.data.api.criteria.Criteria;

/**
 * Voucher State Filter
 * 
 * @author Hakan Uygun
 */
public class VoucherStateFilter<E> extends Filter<E, VoucherState, VoucherState> {

    private List<VoucherState> valueList;
    
    public VoucherStateFilter(SingularAttribute<? super E, VoucherState> attribute, List<VoucherState> valueList, String label) {
        super(attribute, label);
        
        this.valueList = valueList;

        setOperands(Operands.getEnumOperands());
        setOperand(FilterOperand.All);
        setValue(valueList.get(0));
    }

    @Override
    public void decorateCriteria(Criteria<E, ?> criteria) {
        if (getValue() != null) {
            switch (getOperand()) {
                case Equal:
                    criteria.eq(getAttribute(), getValue());
                    break;
                case NotEqual:
                    criteria.notEq(getAttribute(), getValue());
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
                    predicates.add(builder.equal(from.get(getAttribute()), getValue()));
                    break;
                case NotEqual:
                    predicates.add(builder.notEqual(from.get(getAttribute()), getValue()));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String getTemplate() {
        return "voucherStateFilter";
    }

    public List<VoucherState> getValueList() {
        return valueList;
    }

    public void setValueList(List<VoucherState> valueList) {
        this.valueList = valueList;
    }

    public String getStringValue(){
        return getValue().toString();
    }
    
    public void setStringValue(String val){
        setValue(VoucherState.valueOf(val));
    }
    
    
    
    @Override
    public String serialize() {
        return Joiner.on("::").join(getOperand(), getValue() == null ? "null" : getValue().toString());
    }

    @Override
    public void deserialize(String s) {
        List<String> ls = Splitter.on("::").trimResults().splitToList(s);
        setOperand(FilterOperand.valueOf(ls.get(0)));
        if (!"null".equals(ls.get(1))) {
            setValue(VoucherState.valueOf(ls.get(1)));
        } else {
            setValue(null);
        }
    }
    
}
