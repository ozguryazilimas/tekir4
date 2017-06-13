package com.ozguryazilim.tekir.lead.category;

import javax.enterprise.context.Dependent;

import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

import com.ozguryazilim.tekir.entities.LeadCategory;
import com.ozguryazilim.telve.data.TreeRepositoryBase;

@Repository
@Dependent
public abstract class LeadCategoryRepository extends TreeRepositoryBase<LeadCategory>
		implements CriteriaSupport<LeadCategory> {

}
