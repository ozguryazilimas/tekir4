package com.ozguryazilim.tekir.core.view;

import com.ozguryazilim.tekir.core.query.filter.TagSuggestionService;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.primefaces.event.SelectEvent;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@FacesComponent("inputTag")
public class InputTagComponent extends UINamingContainer{

    private TagSuggestionService suggestionService;

    public List<TagResult> getTags() {
        List<String> valueList = getTagsAsString();

        if (valueList == null) {
            return new ArrayList<>();
        }

        return valueList.stream().map(TagResult::new).collect(Collectors.toList());

    }

    public List<String> getTagsAsString() {
        ValueExpression ve = getValueExpression("value");
        List<String> valueList = (List<String   >) ve.getValue(FacesContext.getCurrentInstance().getELContext());

        if (valueList == null) {
            return new ArrayList<>();
        }

        return valueList;
    }


    public void setTags(List<Object> tags) {
        List<String> valueList;
        if (tags == null || tags.isEmpty()) {
            valueList = new ArrayList<>();
        } else if (tags.get(0) instanceof TagResult) {
            //çok saçma ama autocomplete bileşeni hatası. Aldığını vermek yerine gösterdiğini veriyor bazen
            valueList = tags.stream().map(t -> ((TagResult) t).getTag()).collect(Collectors.toList());
        } else {
            valueList = tags.stream().map(t -> t.toString()).collect(Collectors.toList());
        }
        ValueExpression ve = getValueExpression("value");
        ve.setValue(FacesContext.getCurrentInstance().getELContext(), valueList);

    }

    public List<TagResult> completeTag(String query) {
        List<String> suggestions = getSuggestionService().getSuggestions(getKey());
        List<String> tagsAsString = getTagsAsString();
        suggestions.removeAll(tagsAsString);
        List<TagResult> resultList = suggestions.stream()
                .filter(s -> s.toLowerCase().contains(query.toLowerCase()))
                .map(TagResult::new)
                .collect(Collectors.toList());
        tagsAsString.replaceAll(String::trim);
        if (!suggestions.contains(query) && !tagsAsString.contains(query.trim())) {
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
}
