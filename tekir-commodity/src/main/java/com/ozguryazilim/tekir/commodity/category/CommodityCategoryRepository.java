package com.ozguryazilim.tekir.commodity.category;

import com.ozguryazilim.tekir.entities.CommodityCategory;
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
public abstract class CommodityCategoryRepository
		extends
			TreeRepositoryBase<CommodityCategory>
		implements
			CriteriaSupport<CommodityCategory> {
}