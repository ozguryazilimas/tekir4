package com.ozguryazilim.tekir.core.query.filter;

import com.ozguryazilim.telve.entities.SuggestionItem;
import com.ozguryazilim.telve.suggestion.SuggestionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Etiket önerileri yapan araç
 *
 * @author Hüseyin ATEŞ
 */
@ApplicationScoped
public class TagSuggestionService{

    public static final String SUGGESSTION_GROUP = "INPUT_TAG";

    @Inject
    private SuggestionRepository repository;

    private Map<String, Boolean> dirtyTable = new HashMap<>();
    //Tüm etiketler burada tutulacak fakat 10 binlerce etiket tanımlanmayacağından problem olmayacaktır
    private Map<String, List<String>> tagTable = new HashMap<>();

    public List<String> getSuggestions(String key) {
        if (dirtyTable.get(key) != Boolean.FALSE) {
            List<SuggestionItem> suggestionItems;
            List<String> tags;
            if ("*".equals(key)) {
                suggestionItems = repository.findByGroup(SUGGESSTION_GROUP);
                tags = suggestionItems.stream().map(SuggestionItem::getData)
                        .distinct().collect(Collectors.toList());
            } else {
                suggestionItems = repository.findByGroupAndKey(SUGGESSTION_GROUP, key);
                tags = suggestionItems.stream().map(SuggestionItem::getData)
                        .collect(Collectors.toList());
            }
            tagTable.put(key, tags);
            dirtyTable.put(key, Boolean.FALSE);
        }
        return tagTable.get(key);
    }

    public void saveSuggestion(String key, String tag) {
        if (!getSuggestions(key).contains(tag)) {
            SuggestionItem si = new SuggestionItem();
            si.setGroup(SUGGESSTION_GROUP);
            si.setKey(key);
            si.setData(tag);
            si.setActive(Boolean.TRUE);
            si.setInfo("AUTO");
            repository.saveAndFlush(si);
            dirtyTable.put(key, Boolean.TRUE);
        }
    }
}
