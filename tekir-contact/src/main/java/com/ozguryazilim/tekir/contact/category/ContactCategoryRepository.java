package com.ozguryazilim.tekir.contact.category;

import com.ozguryazilim.tekir.entities.ContactCategory;
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
public abstract class ContactCategoryRepository
		extends
			TreeRepositoryBase<ContactCategory>
		implements
			CriteriaSupport<ContactCategory> {
}