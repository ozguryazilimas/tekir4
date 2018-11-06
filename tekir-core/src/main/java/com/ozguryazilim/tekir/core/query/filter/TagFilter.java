package com.ozguryazilim.tekir.core.query.filter;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ozguryazilim.telve.query.filters.Filter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;
import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;

/**
 * Etiketler için filre tanım modeli
 *
 * @param <E> Entity sınıfı
 * @author Hüseyin ATEŞ
 */
public class TagFilter<E> extends Filter<E, List<String>, List<String>>{

    private String key;
    private TagSuggestionService suggestionProvider;

    /**
     * @param attributeName Entity etiket alanı adı
     * @param label         Filtre için gösterilecek etiket
     * @param key           Etiket gruplarını ayıran anahtar. "*" olması durumunda tüm etiketler filtre seçeneklerine getirilir
     */
    public TagFilter(String attributeName, String label, String key) {
        super(null, label);
        this.key = key;
        setAttribute(new MockSingularAttribute<>(attributeName));
        this.setOperands(Arrays.asList(FilterOperand.All, FilterOperand.Equal, FilterOperand.NotEqual));
        this.setOperand(FilterOperand.Equal);
        this.suggestionProvider = BeanProvider.getContextualReference(TagSuggestionService.class);
    }


    public List<String> getSuggestions() {
        return this.suggestionProvider.getSuggestions(this.key);
    }

    @Override
    public void decorateCriteria(Criteria<E, ?> criteria) {
    }

    @Override
    public void decorateCriteriaQuery(List<Predicate> predicates, CriteriaBuilder cb, Root<E> root) {
        List<String> tags = getValue();
        if (tags == null || tags.isEmpty()) return;
        Expression<String> tagsExp = root.get(this.getAttribute().getName()).as(String.class);
        switch (this.getOperand()) {
            case Contains:
                tags.forEach(tag -> predicates.add(cb.like(tagsExp, "%|" + tag + "|%")));
                break;
            case NotContains:
                tags.forEach(tag -> predicates.add(cb.notLike(tagsExp, "%|" + tag + "|%")));
                break;
        }
    }

    @Override
    public String getTemplate() {
        return "tagFilter";
    }

    @Override
    public String serialize() {
        Joiner joiner = Joiner.on("::");
        String tags = joiner.join(this.getValue());
        return joiner.join(this.getOperand(), tags);
    }

    @Override
    public void deserialize(String s) {
        List<String> ls = Splitter.on("::").trimResults().splitToList(s);
        String operand = ls.remove(0);
        this.setOperand(FilterOperand.valueOf(operand));
        this.setValue(ls);
    }


    class MockSingularAttribute<E> implements SingularAttribute<E, List<String>>{

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
        public Type getType() {
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
        public ManagedType getDeclaringType() {
            return null;
        }

        @Override
        public Class getJavaType() {
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
        public Class getBindableJavaType() {
            return null;
        }
    }

}
