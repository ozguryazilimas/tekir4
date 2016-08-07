package com.ozguryazilim.tekir.core.tax;

import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.ParamRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.TaxDefinition;
import com.ozguryazilim.tekir.entities.TaxDefinition_;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Repository Class
 * 
 * @author
 */
@Repository
@Dependent
public abstract class TaxDefinitionRepository
		extends
			ParamRepositoryBase<TaxDefinition, TaxDefinitionViewModel>
		implements
			CriteriaSupport<TaxDefinition> {

	@Override
	protected Class<TaxDefinitionViewModel> getViewModelClass() {
		return TaxDefinitionViewModel.class;
	}

	@Override
	protected SingularAttribute<? super TaxDefinition, Long> getIdAttribute() {
		return TaxDefinition_.id;
	}
}