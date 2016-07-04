package com.ozguryazilim.tekir.core.territory;

import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.ParamRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.tekir.entities.Territory_;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Repository Class
 * 
 * @author
 */
@Repository
@Dependent
public abstract class TerritoryRepository
		extends
			ParamRepositoryBase<Territory, TerritoryViewModel>
		implements
			CriteriaSupport<Territory> {

	@Override
	protected Class<TerritoryViewModel> getViewModelClass() {
		return TerritoryViewModel.class;
	}

	@Override
	protected SingularAttribute<? super Territory, Long> getIdAttribute() {
		return Territory_.id;
	}
}