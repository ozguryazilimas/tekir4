/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.google.common.base.Strings;
import com.ozguryazilim.opportunity.dashlets.TopOpportunityModel;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.Opportunity_;
import com.ozguryazilim.tekir.entities.Person_;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.entities.VoucherProcessBase_;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.Criteria;

/**
 * Opportunity'ler için repository
 *
 * @author Hakan Uygun
 */
@Dependent
@Repository
public abstract class OpportunityRepository extends VoucherRepositoryBase<Opportunity, OpportunityViewModel> {

	@Override
	public List<OpportunityViewModel> browseQuery(QueryDefinition queryDefinition) {
		List<Filter<Opportunity, ?, ?>> filters = queryDefinition.getFilters();

		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		//Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
		CriteriaQuery<OpportunityViewModel> criteriaQuery = criteriaBuilder.createQuery(OpportunityViewModel.class);


		//From 
		Root<Opportunity> from = criteriaQuery.from(Opportunity.class);
		Join<Opportunity, VoucherGroup> joinGroup = from.join(Opportunity_.group, JoinType.LEFT);

		//Sonuç filtremiz
		buildVieModelSelect(criteriaQuery, from);

		//Filtreleri ekleyelim.
		List<Predicate> predicates = new ArrayList<>();

		//predicates.add(criteriaBuilder.equal( from.get(Opportunity_.account).get(Contact_.name), getValue()));

		decorateFilters(filters, predicates, criteriaBuilder, from);

		buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

		//RowLevel yetki kontrol filtresi
		buildOwnerFilter(predicates, from);

		//Oluşan filtreleri sorgumuza ekliyoruz
		criteriaQuery.where(predicates.toArray(new Predicate[]{}));

		// Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı sıralama var ise onu setleyelim
		if (queryDefinition.getSorters().isEmpty()) {
			criteriaQuery.orderBy(criteriaBuilder.desc(from.get(VoucherBase_.date)));
		} else {
			criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
		}

		//Haydi bakalım sonuçları alalım
		TypedQuery<OpportunityViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
		typedQuery.setMaxResults(queryDefinition.getResultLimit());
		List<OpportunityViewModel> resultList = typedQuery.getResultList();

		return resultList;
	}

	private void buildVieModelSelect(CriteriaQuery<OpportunityViewModel> criteriaQuery, Root<? extends Opportunity> from) {
		criteriaQuery.multiselect(
				from.get(Opportunity_.id),
				from.get(VoucherProcessBase_.process).get(Process_.id),
				from.get(VoucherProcessBase_.process).get(Process_.processNo),
				from.get(VoucherProcessBase_.account).get(Contact_.id),
				from.get(VoucherProcessBase_.account).get(Contact_.name),
				from.get(VoucherProcessBase_.account).type(),
				from.get("tags"),
				from.get(VoucherBase_.voucherNo),
				from.get(VoucherBase_.info),
				from.get(VoucherBase_.referenceNo),
				from.get(VoucherBase_.date),
				from.get(VoucherBase_.owner),
				from.get(VoucherBase_.state),
				from.get(VoucherBase_.stateReason),
				from.get(VoucherBase_.stateInfo),
				from.get(VoucherBase_.group).get(VoucherGroup_.id),
				from.get(VoucherBase_.group).get(VoucherGroup_.groupNo),
				from.get(VoucherBase_.topic),
				from.get(Opportunity_.budget),
				from.get(Opportunity_.currency)
				);
	}

	private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Opportunity> from) {
		if (!Strings.isNullOrEmpty(searchText)) {
			predicates.add(
					criteriaBuilder.or(
							criteriaBuilder.like(from.get(VoucherBase_.topic), "%" + searchText + "%"),
							criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
							criteriaBuilder.like(from.get(VoucherProcessBase_.account).get(Contact_.name), "%" + searchText + "%")
							)
					);
		}
	}

	/**
	 *
	 * @param feature hangi evrak türü. boş olması hepsi demek
	 * @param username hangi kullanıcı için boş olması hesap demek
	 * @param startDate hangi tarihten başlayacağız
	 * @param limit geriye kaç sonuç dönecek
	 * @return 
	 */
	public List<TopOpportunityModel> findTopOpportunities(String username, Date startDate, Integer limit ){
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		//Geriye AccidentAnalysisViewModel dönecek cq'yu ona göre oluşturuyoruz.
		CriteriaQuery<TopOpportunityModel> criteriaQuery = criteriaBuilder.createQuery(TopOpportunityModel.class);

		//From Tabii ki PersonWorkHistory
		Root<Opportunity> from = criteriaQuery.from(Opportunity.class);

		criteriaQuery.multiselect(
				from.get(Opportunity_.id),  
				from.get(Opportunity_.topic),
				from.get(Opportunity_.primaryContact).get(Person_.id),
				from.get(Opportunity_.account).get(Person_.name),               
				from.get(Opportunity_.date),
				from.get(Opportunity_.budget),          
				from.get(Opportunity_.currency),
				from.get(Opportunity_.localBudget),  
				from.get(Opportunity_.probability)             
				);       

		//Filtreleri ekleyelim.
		List<Predicate> predicates = new ArrayList<>();

		if( !Strings.isNullOrEmpty(username)){
			predicates.add(criteriaBuilder.equal(from.get(Opportunity_.owner), username));
		}

		predicates.add(criteriaBuilder.greaterThanOrEqualTo(from.get(Opportunity_.date), startDate));

		criteriaQuery.where(predicates.toArray(new Predicate[]{}));

		criteriaQuery.orderBy(criteriaBuilder.desc(from.get(Opportunity_.localBudget)));
		TypedQuery<TopOpportunityModel> typedQuery = entityManager().createQuery(criteriaQuery);
		typedQuery.setMaxResults(limit);      
		List<TopOpportunityModel> resultList = typedQuery.getResultList();

		return resultList;
	}

	public List<Opportunity> findByStateAndOwner(VoucherState state, String owner){
		Criteria<Opportunity, Opportunity> cr = criteria();
		
		if(state != null){
			cr.eq(Opportunity_.state, state);
		}
		
		if(!Strings.isNullOrEmpty(owner)){
			cr.eq(Opportunity_.owner, owner);
		}
		
		return cr.createQuery().getResultList();
		
	}
        
        /**
         * Belirtilen süreden sonra kapanmamış fırsatları veritabanında arar.
         * 
         * @param date Fırsatların oluşturulma tarihinden itibaren geçecek süre
         * @return Geçen süreden sonra kapanmamış olan fırsatlar
         * @see Opportunity
         * @see Opportunity_
         * @see OpportunityViewModel
         */
        public List<OpportunityViewModel> findUnclosedOpportunities(Date date) {
            CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
            //Geriye ViewModel dönecek cq'yu ona göre oluşturuyoruz.
            CriteriaQuery<OpportunityViewModel> criteriaQuery = criteriaBuilder.createQuery(OpportunityViewModel.class);

            //From 
            Root<Opportunity> from = criteriaQuery.from(Opportunity.class);
            
            //Join
            from.join(Opportunity_.group, JoinType.LEFT);

            //Sonuç filtremiz
            buildVieModelSelect(criteriaQuery, from);

            //Filtreleri ekleyelim.
            List<Predicate> predicates = new ArrayList<>();

            //Tarih verilen parametreden küçük ve eşit olanlar.
            predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get(Opportunity_.createDate), date));

            //Olumlu ya da Olumsuz kapanmamış olanlar.
            predicates.add(criteriaBuilder.notLike(from.get(VoucherBase_.state).as(String.class), "CLOSE-%"));

            //Oluşan filtreleri sorgumuza ekliyoruz
            criteriaQuery.where(predicates.toArray(new Predicate[]{}));

            //Haydi bakalım sonuçları alalım
            TypedQuery<OpportunityViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
            List<OpportunityViewModel> resultList = typedQuery.getResultList();

            return resultList;
        }

    public abstract List<Opportunity> findByDateBetween(Date beginDate, Date endDate);
}
