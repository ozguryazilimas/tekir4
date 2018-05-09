package com.ozguryazilim.tekir.core.view;

import com.ozguryazilim.tekir.core.tag.TagRepository;
import com.ozguryazilim.tekir.core.tag.TagService;
import com.ozguryazilim.tekir.entities.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.persistence.EntityManager;
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
        Collection<Tag> tags = getTags();
        tags.clear();
        getTagService().populateWithNames(tags, chips, type);
    }

    private Collection<Tag> getTags() {
        Object valueObject = getAttributes().get("value");
        if (!(valueObject instanceof Collection)) {
            throw new RuntimeException("InputTag value should be a collection of VoucherTag.");
        }

        return ((Collection<Tag>) valueObject);
    }

    private TagService getTagService() {
        BeanManager bm = CDI.current().getBeanManager();
        Bean<TagService> bean = (Bean<TagService>) bm.getBeans(TagService.class).iterator().next();
        CreationalContext<TagService> ctx = bm.createCreationalContext(bean);
        return (TagService) bm.getReference(bean, TagService.class, ctx);
    }

}
