package com.ozguryazilim.tekir.contact.corporationType;

import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.ParamRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.CorporationType;
import com.ozguryazilim.tekir.entities.CorporationType_;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Repository Class
 * 
 * @author
 */
@Repository
@Dependent
public abstract class CorporationTypeRepository
		extends
			ParamRepositoryBase<CorporationType, CorporationTypeViewModel>
		implements
			CriteriaSupport<CorporationType> {

	@Override
	protected Class<CorporationTypeViewModel> getViewModelClass() {
		return CorporationTypeViewModel.class;
	}

	@Override
	protected SingularAttribute<? super CorporationType, Long> getIdAttribute() {
		return CorporationType_.id;
	}
}