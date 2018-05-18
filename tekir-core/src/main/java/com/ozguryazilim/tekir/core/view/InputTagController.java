package com.ozguryazilim.tekir.core.view;

import com.ozguryazilim.telve.entities.SuggestionItem;
import com.ozguryazilim.telve.suggestion.SuggestionRepository;
import org.apache.deltaspike.core.api.scope.WindowScoped;
import org.primefaces.event.SelectEvent;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * t:inputTag bileşeni ardında çalışan controller
 *
 * @author Hüseyin ATEŞ
 */
@Named
@WindowScoped
public class InputTagController implements Serializable{

    public static final String SUGGESSTION_GROUP = "INPUT_TAG";

    @Inject
    private SuggestionRepository repository;

    private Map<String, Set<String>> potentialNewTagsMap = new HashMap<>();
    private Map<String, Set<String>> submittedNewTagsMap = new HashMap<>();

    public List<TagResult> completeTag(String query) {
        String key = getKey();
        List<String> suggestions = getSuggestionList();
        List<TagResult> resultList = suggestions.stream()
                .filter(s -> s.contains(query))
                .map(TagResult::new)
                .collect(Collectors.toList());

        if (!suggestions.contains(query) && !submittedNewTags(key).contains(query)) {
            potentialNewTags(key).add(query);
            resultList.add(0, new TagResult(query, true));
        }

        return resultList;
    }

    public void onItemSelect(SelectEvent event) {
        String tag = (String) event.getObject();
        String key = getKey();
        if (!submittedNewTags(key).contains(tag) && potentialNewTags(key).contains(tag)) {
            SuggestionItem si = new SuggestionItem();
            si.setGroup(SUGGESSTION_GROUP);
            si.setKey(getKey());
            si.setData(tag);
            si.setActive(Boolean.TRUE);
            si.setInfo("AUTO");
            repository.saveAndFlush(si);
            submittedNewTags(key).add(tag);
        }
    }

    private List<String> getSuggestionList() {
        // Burada bir performans problemi olabilir
        List<SuggestionItem> suggestions = repository.findByGroupAndKey(SUGGESSTION_GROUP, getKey());
        return suggestions.stream().map(SuggestionItem::getData).collect(Collectors.toList());
    }

    private String getKey() {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc);
        ValueExpression keyExp = (ValueExpression) component.getPassThroughAttributes().get("key");
        return (String) keyExp.getValue(fc.getELContext());
    }

    private Set<String> submittedNewTags(String key) {
        if (!submittedNewTagsMap.containsKey(key)) {
            Set<String> set = new HashSet<>();
            submittedNewTagsMap.put(key, new HashSet<>());
            return set;
        }
        return submittedNewTagsMap.get(key);
    }

    private Set<String> potentialNewTags(String key) {
        if (!potentialNewTagsMap.containsKey(key)) {
            Set<String> set = new HashSet<>();
            potentialNewTagsMap.put(key, new HashSet<>());
            return set;
        }
        return potentialNewTagsMap.get(key);
    }

    public class TagResult implements Serializable{
        private String tag;
        private boolean absent;

        public TagResult(String tag) {
            this.tag = tag;
        }

        public TagResult(String tag, boolean absent) {
            this.tag = tag;
            this.absent = absent;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public boolean isAbsent() {
            return absent;
        }

        public void setAbsent(boolean absent) {
            this.absent = absent;
        }
    }
}
