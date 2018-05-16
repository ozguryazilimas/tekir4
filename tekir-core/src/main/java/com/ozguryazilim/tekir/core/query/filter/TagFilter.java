package com.ozguryazilim.tekir.core.query.filter;

import com.ozguryazilim.tekir.core.view.InputTagController;
import com.ozguryazilim.telve.entities.SuggestionItem;
import com.ozguryazilim.telve.query.filters.Filter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.suggestion.SuggestionRepository;
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
import java.util.stream.Collectors;

/**
 * Etiketler için filre tanım modeli
 *
 * @param <E> Entity sınıfı
 * @author Hüseyin ATEŞ
 */
public class TagFilter<E> extends Filter<E, List<String>, List<String>>{

    private List<String> suggestions;

    public TagFilter(String attributeName, String label, String key) {
        super(null, label);
        setAttribute(new MockSingularAttribute<>(attributeName));

        this.setOperands(Arrays.asList(new FilterOperand[]{
                FilterOperand.All,
                FilterOperand.Contains,
                FilterOperand.NotContains
        }));
        this.setOperand(FilterOperand.Equal);
        this.initSuggestions(key);
    }

    private void initSuggestions(String key) {
        SuggestionRepository repository = BeanProvider.getContextualReference(SuggestionRepository.class);
        List<SuggestionItem> suggestionItems = repository.findByGroupAndKey(InputTagController.SUGGESSTION_GROUP, key);
        suggestions = suggestionItems.stream().map(SuggestionItem::getData).collect(Collectors.toList());
    }

    @Override
    public void decorateCriteria(Criteria<E, ?> criteria) {
    }

    @Override
    public void decorateCriteriaQuery(List<Predicate> predicates, CriteriaBuilder cb, Root<E> root) {
        List<String> tags = getValue();
        if (tags == null || tags.isEmpty()) return;
        Expression<String> tagsExp = root.get(this.getAttribute().getName()).as(String.class);

        // tüm seçenekler seçilmiş
        if (this.getOperand() == FilterOperand.Equal && tags.size() == suggestions.size()) {
            return;
        }

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
        return null;
    }

    @Override
    public void deserialize(String s) {

    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
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
