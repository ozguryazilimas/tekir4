/**
 * 
 */
package com.ozguryazilim.tekir.voucher.group;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.entities.VoucherGroupTxn;
import com.ozguryazilim.tekir.entities.VoucherGroupTxn_;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.entities.FeaturePointer_;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder.Trimspec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.Criteria;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import org.apache.deltaspike.data.api.criteria.QuerySelection;

import com.ozguryazilim.telve.data.RepositoryBase;

/**
 * VoucherGroup Txn için repository sınıfı.
 * 
 * @author oktay
 *
 */
@Repository
@Dependent
public abstract class VoucherGroupTxnRepository extends RepositoryBase<VoucherGroupTxn, VoucherGroupTxn>
		implements CriteriaSupport<VoucherGroupTxn> {

	private VoucherGroup group;

	public VoucherGroup getGroup() {
		return group;
	}

	public void setGroup(VoucherGroup group) {
		this.group = group;
	}

	public abstract VoucherGroupTxn findOptionalByFeature(FeaturePointer feature);

	public abstract VoucherGroupTxn findOptionalByFeatureAndGroup(FeaturePointer feature, VoucherGroup group);

	//public abstract List<VoucherGroupTxn> findByGroupId(VoucherGroup voucherGroup);
	
	public List<VoucherGroupTxn> findByGroupId(VoucherGroup voucherGroup){
	    if (voucherGroup != null){
            Criteria<VoucherGroupTxn, VoucherGroupTxn> cr = criteria();
            cr.eq(VoucherGroupTxn_.group,voucherGroup);
            return cr.createQuery().getResultList();
        }
        return Collections.emptyList();
	}

	@Override
	public List<VoucherGroupTxn> browseQuery(QueryDefinition queryDefinition) {
		List<Filter<VoucherGroupTxn, ?, ?>> filters = queryDefinition.getFilters();

		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();

		CriteriaQuery<VoucherGroupTxn> criteriaQuery = criteriaBuilder.createQuery(VoucherGroupTxn.class);

		Root<VoucherGroupTxn> from = criteriaQuery.from(VoucherGroupTxn.class);

		// Filtreleri ekleyelim.
		List<Predicate> predicates = new ArrayList<>();

		decorateFilters(filters, predicates, criteriaBuilder, from);

		// Hem source hem de target üzerinde olanlar gelsin.
		if (group != null) {
			predicates
					.add(criteriaBuilder.equal(from.get(VoucherGroupTxn_.group).get(VoucherGroup_.id), group.getId()));
		}

		// filtremize ekledik.
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		// Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı
		// sıralama var ise onu setleyelim
		if (queryDefinition.getSorters().isEmpty()) {
			criteriaQuery.orderBy(criteriaBuilder.desc(from.get(VoucherGroupTxn_.date)));
		} else {
			criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
		}

		// Haydi bakalım sonuçları alalım
		TypedQuery<VoucherGroupTxn> typedQuery = entityManager().createQuery(criteriaQuery);
		List<VoucherGroupTxn> resultList = typedQuery.getResultList();

		return resultList;
	}

    public abstract void deleteByFeature_feature(String feature);
}
