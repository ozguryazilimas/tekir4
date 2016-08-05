package com.ozguryazilim.tekir.core.unitset;

import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.ParamRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.UnitSetDefinition;
import com.ozguryazilim.tekir.entities.UnitSetDefinition_;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Repository Class
 * 
 * @author
 */
@Repository
@Dependent
public abstract class UnitSetDefinitionRepository
		extends
			ParamRepositoryBase<UnitSetDefinition, UnitSetDefinitionViewModel>
		implements
			CriteriaSupport<UnitSetDefinition> {

	@Override
	protected Class<UnitSetDefinitionViewModel> getViewModelClass() {
		return UnitSetDefinitionViewModel.class;
	}

	@Override
	protected SingularAttribute<? super UnitSetDefinition, Long> getIdAttribute() {
		return UnitSetDefinition_.id;
	}
}