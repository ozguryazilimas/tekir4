package com.ozguryazilim.tekir.core.view;

import com.ozguryazilim.tekir.core.query.filter.TagSuggestionService;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.primefaces.event.SelectEvent;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@FacesComponent("inputTag")
public class InputTagComponent extends UINamingContainer{

    private TagSuggestionService suggestionService;

    public List<TagResult> completeTag(String query) {
        List<String> suggestions = getSuggestionService().getSuggestions(getKey());
        List<TagResult> resultList = suggestions.stream()
                .filter(s -> s.contains(query))
                .map(TagResult::new)
                .collect(Collectors.toList());

        if (!suggestions.contains(query)) {
            resultList.add(0, new TagResult(query, true));
        }

        return resultList;
    }

    public void onItemSelect(SelectEvent event) {
        String tag = (String) event.getObject();
        TagSuggestionService service = getSuggestionService();
        String key = getKey();
        List<String> suggestions = service.getSuggestions(key);
        if (!suggestions.contains(tag)) {
            service.saveSuggestion(key, tag);
        }
    }

    private String getKey() {
        return (String) getAttributes().get("key");
    }

    private TagSuggestionService getSuggestionService() {
        if (suggestionService == null) {
            suggestionService = BeanProvider.getContextualReference(TagSuggestionService.class);
        }
        return suggestionService;
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
