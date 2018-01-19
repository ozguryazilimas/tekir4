package com.ozguryazilim.tekir.lead.source;

import javax.enterprise.context.Dependent;

import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

import com.ozguryazilim.tekir.entities.LeadSource;
import com.ozguryazilim.telve.data.TreeRepositoryBase;

@Repository
@Dependent
public abstract class LeadSourceRepository extends TreeRepositoryBase<LeadSource>
		implements CriteriaSupport<LeadSource> {

}
