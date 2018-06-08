package com.ozguryazilim.tekir.core.filters;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ozguryazilim.telve.query.Operands;
import com.ozguryazilim.telve.query.filters.Filter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import java.lang.reflect.Member;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;
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
    private List<String> values;


    public ListAttributeStringListFilter(String attributeName, List<String> valueList, String label,
        String itemLabel) {
        super(label);
        setAttribute(new MockSingularAttribute<>(attributeName));

        keyPrefix = itemLabel;
        this.valueList = valueList;
        this.attributeName = attributeName;

        setOperands(Operands.getEnumOperands());
        setOperand(FilterOperand.All);
    }

    @Override
    public void decorateCriteria(Criteria<E, ?> criteria) {
        if (getValues() != null) {
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
        if (getValues() != null) {
            switch (getOperand()) {
                case Equal:
                    for (String v : values) {
                        predicates
                            .add(builder
                                .like(from.get(attributeName).as(String.class), "%" + v + "%"));
                    }
                    break;
                case NotEqual:
                    for (String v : values) {
                        predicates
                            .add(builder
                                .notLike(from.get(attributeName).as(String.class), "%" + v + "%"));
                    }
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

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String serialize() {
        if (getValues() != null) {
            StringBuilder sb = new StringBuilder();
            ListIterator<String> iterator = getValues().listIterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                if (iterator.hasNext()) {
                    sb.append(",");
                }
            }
            return Joiner.on("::").join(getOperand(), sb.toString());
        } else {
            return Joiner.on("::").join(getOperand(), "null");
        }
    }

    @Override
    public void deserialize(String s) {
        List<String> ls = Splitter.on("::").trimResults().splitToList(s);
        setOperand(FilterOperand.valueOf(ls.get(0)));
        if (!"null".equals(ls.get(1))) {
            List<String> values = Splitter.on(",").trimResults().splitToList(ls.get(1));
            setValues(values);
        } else {
            setValues(null
            );
        }
    }

    class MockSingularAttribute<E> implements SingularAttribute<E, String> {

        private String name;

        public MockSingularAttribute(String name) {
            this.name = name;
        }

        @Override
        public boolean isId() {
            return false;
        }

        @Override
        public boolean isVersion() {
            return false;
        }

        @Override
        public boolean isOptional() {
            return false;
        }

        @Override
        public Type<String> getType() {
            return null;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public PersistentAttributeType getPersistentAttributeType() {
            return null;
        }

        @Override
        public ManagedType<E> getDeclaringType() {
            return null;
        }

        @Override
        public Class<String> getJavaType() {
            return null;
        }

        @Override
        public Member getJavaMember() {
            return null;
        }

        @Override
        public boolean isAssociation() {
            return false;
        }

        @Override
        public boolean isCollection() {
            return false;
        }

        @Override
        public BindableType getBindableType() {
            return null;
        }

        @Override
        public Class<String> getBindableJavaType() {
            return null;
        }
    }
}
