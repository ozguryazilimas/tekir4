package com.ozguryazilim.tekir.core.filters;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ozguryazilim.telve.query.Operands;
import com.ozguryazilim.telve.query.filters.Filter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.deltaspike.data.api.criteria.Criteria;

/**
 * String filtresi fakat kullanıcıdan veri almak yerine verilen listeden seçtirir.
 *
 * EnumFilter gibi davranır.
 *
 * @author Soner Cirit
 */
public class ListAttributeStringListFilter<E> extends Filter<E, String, String> {

    private String keyPrefix;
    private List<String> valueList;
    private String attributeName;


    public ListAttributeStringListFilter(String attributeName, List<String> valueList, String label,
        String itemLabel) {
        super(label);

        keyPrefix = itemLabel;
        this.valueList = valueList;
        this.attributeName = attributeName;

        setOperands(Operands.getStringOperands());
        setOperand(FilterOperand.Contains);
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
    public void decorateCriteriaQuery(List<Predicate> predicates, CriteriaBuilder builder,
        Root<E> from) {
        if (getValue() != null) {
            switch (getOperand()) {
                case Contains:
                    predicates
                        .add(builder.like(from.get(attributeName).as(String.class),
                            "%" + getValue() + "%"));
                    break;
                case NotContains:
                    predicates
                        .add(builder.like(from.get(attributeName).as(String.class),
                            "%" + getValue() + "%"));
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public String getTemplate() {
        return "listAttributeStringListFilter";
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    @Override
    public String serialize() {
        return Joiner.on("::").join(getOperand(), getValue() == null ? "null" : getValue());
    }

    @Override
    public void deserialize(String s) {
        List<String> ls = Splitter.on("::").trimResults().splitToList(s);
        setOperand(FilterOperand.valueOf(ls.get(0)));
        if (!"null".equals(ls.get(1))) {
            setValue(ls.get(1));
        } else {
            setValue(null
            );
        }
    }
}
