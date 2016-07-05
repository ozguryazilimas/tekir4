package com.ozguryazilim.tekir.contact.relation;

import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.ParamRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.ContactRelation;
import com.ozguryazilim.tekir.entities.ContactRelation_;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Repository Class
 * 
 * @author
 */
@Repository
@Dependent
public abstract class ContactRelationRepository
		extends
			ParamRepositoryBase<ContactRelation, ContactRelationViewModel>
		implements
			CriteriaSupport<ContactRelation> {

	@Override
	protected Class<ContactRelationViewModel> getViewModelClass() {
		return ContactRelationViewModel.class;
	}

	@Override
	protected SingularAttribute<? super ContactRelation, Long> getIdAttribute() {
		return ContactRelation_.id;
	}
}