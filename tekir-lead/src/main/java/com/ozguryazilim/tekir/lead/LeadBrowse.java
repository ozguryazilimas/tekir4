package com.ozguryazilim.tekir.lead;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;

import com.ozguryazilim.tekir.entites.VoucherBase_;
import com.ozguryazilim.tekir.entites.Lead_;
import com.ozguryazilim.tekir.entites.LeadSource_;
import com.ozguryazilim.tekir.entites.LeadCategory_;

@Browse(feature = LeadFeature.class)
public class LeadBrowse extends BrowseBase<Lead, LeadViewModel> {

	@Inject
	private LeadRepository repository;

	@Override
	protected void buildQueryDefinition(QueryDefinition<Lead, LeadViewModel> queryDefinition) {

		queryDefinition
				.addFilter(new StringFilter<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"))
				.addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date"))
				.addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))
				.addFilter(new StringFilter<>(VoucherBase_.owner, "voucher.label.Owner"))
				.addFilter(new StringFilter<>(Lead_.relatedCompanyName, "lead.label.RelatedCompanyName"))
				.addFilter(new StringFilter<>(Lead_.relatedPersonName, "lead.label.RelatedPersonName"))
				.addFilter(new StringFilter<>(Lead_.relatedPersonSurname, "lead.label.RelatedPersonSurname"))
				.addFilter(new StringFilter<>(Lead_.relatedPhone, "lead.label.RelatedPhone"))
				.addFilter(new StringFilter<>(Lead_.relatedAddress, "lead.label.RelatedAddress"))
				.addFilter(new StringFilter<>(Lead_.relatedEmail, "lead.label.RelatedEmail"))
				.addFilter(new StringFilter<>(LeadSource_.name, "lead.label.LeadSourceName"))
				.addFilter(new StringFilter<>(LeadCategory_.name, "lead.label.LeadCategoryName"));

		queryDefinition
				.addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
				.addColumn(new DateColumn<>(VoucherBase_.date, "voucher.label.Date"), true)
				.addColumn(new TextColumn<>(VoucherBase_.topic, "voucher.label.Topic"), true)
				.addColumn(new TextColumn<>(VoucherBase_.owner, "voucher.label.Owner"), true)
				.addColumn(new TextColumn<>(Lead_.relatedCompanyName, "lead.label.RelatedCompanyName"), true)
				.addColumn(new TextColumn<>(Lead_.relatedPersonName, "lead.label.RelatedPersonName"), false)
				.addColumn(new TextColumn<>(Lead_.relatedPersonSurname, "lead.label.RelatedPersonSurname"), false)
				.addColumn(new TextColumn<>(Lead_.relatedPhone, "lead.label.RelatedPhone"), false)
				.addColumn(new TextColumn<>(Lead_.relatedAddress, "lead.label.RelatedAddress"), false)
				.addColumn(new TextColumn<>(Lead_.relatedEmail, "lead.label.RelatedEmail"), false)
				.addColumn(new TextColumn<>(LeadSource_.name, "lead.label.LeadSourceName"), false)
				.addColumn(new TextColumn<>(LeadCategory_.name, "lead.label.LeadCategoryName"), false);

	}

	@Override
	protected RepositoryBase<Lead, LeadViewModel> getRepository() {
		return null;
	}

}
