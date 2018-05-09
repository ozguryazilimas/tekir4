package com.ozguryazilim.tekir.core.tag;

import com.ozguryazilim.tekir.entities.Tag;
import com.ozguryazilim.telve.data.RepositoryBase;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.context.Dependent;
import java.util.List;

@Repository
@Dependent
public abstract class TagRepository extends RepositoryBase<Tag, Tag>{

    public abstract List<Tag> findByType(String type);
}
