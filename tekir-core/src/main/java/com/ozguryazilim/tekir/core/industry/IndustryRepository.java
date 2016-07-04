package com.ozguryazilim.tekir.core.industry;

import com.ozguryazilim.tekir.entities.Industry;
import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.TreeRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * Repository Class
 * 
 * @author
 */
@Repository
@Dependent
public abstract class IndustryRepository extends TreeRepositoryBase<Industry>
		implements
			CriteriaSupport<Industry> {
}