package com.ozguryazilim.tekir.core.view;

import com.ozguryazilim.tekir.core.tag.TagRepository;
import com.ozguryazilim.tekir.entities.Tag;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FacesComponent("inputTag")
public class InputTag extends UINamingContainer{

    public List<String> getChips() {
        Collection<Tag> tags = getTags();
        List<String> chips = new ArrayList<>(tags.size());
        for (Tag t : tags) {
            chips.add(t.getText());
        }
        return chips;
    }

    public void setChips(List<String> chips) {
        String type = (String) getAttributes().get("type");
        Map<String, Tag> globalTagMap = getTagMap(type);
        Collection<Tag> tags = getTags();
        tags.clear();
        for (String text : chips) {
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

    private Collection<Tag> getTags() {
        Object valueObject = getAttributes().get("value");
        if (!(valueObject instanceof Collection)) {
            throw new RuntimeException("InputTag value should be a collection of VoucherTag.");
        }

        return ((Collection<Tag>) valueObject);
    }

    private Map<String, Tag> getTagMap(String type) {
        //TODO burada haldır haldır select çalışacak. Araya TagService yazarak bu işi çöz
        List<Tag> existingTags = getRepository().findByType(type);
        Map<String, Tag> tagMap = new HashMap<>(existingTags.size());
        for (Tag t : existingTags) {
            tagMap.put(t.getText(), t);
        }
        return tagMap;
    }

    private TagRepository getRepository() {
        BeanManager bm = CDI.current().getBeanManager();
        Bean<TagRepository> bean = (Bean<TagRepository>) bm.getBeans(TagRepository.class).iterator().next();
        CreationalContext<TagRepository> ctx = bm.createCreationalContext(bean);
        return (TagRepository) bm.getReference(bean, TagRepository.class, ctx);
    }

}
