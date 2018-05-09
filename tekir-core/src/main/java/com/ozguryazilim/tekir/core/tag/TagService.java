package com.ozguryazilim.tekir.core.tag;

import com.ozguryazilim.tekir.entities.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TagService{

    @Inject
    private TagRepository repository;

    public void populateWithNames(Collection<Tag> tags, List<String> tagNames, String type) {
        Map<String, Tag> globalTagMap = getTagMap(type);
        for (String text : tagNames) {
            if (globalTagMap.containsKey(text)) {
                tags.add(globalTagMap.get(text));
            } else {
                Tag t = new Tag();
                t.setText(text);
                t.setType(type);
                tags.add(t);
            }
        }

    }

    private Map<String, Tag> getTagMap(String type) {
        //TODO burada haldır haldır select çalışabilir. Buraya bir el atmak lazım
        List<Tag> existingTags = repository.findByType(type);
        Map<String, Tag> tagMap = new HashMap<>(existingTags.size());
        for (Tag t : existingTags) {
            tagMap.put(t.getText(), t);
        }
        return tagMap;
    }

}
