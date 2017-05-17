package com.ozguryazilim.tekir.lead;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
import com.ozguryazilim.tekir.entities.Lead_;
import com.ozguryazilim.tekir.entities.LeadSource_;
import com.ozguryazilim.tekir.entities.LeadCategory_;

import com.ozguryazilim.telve.entities.TreeNodeEntityBase_;

@Browse(feature = LeadFeature.class)
public class LeadBrowse extends VoucherBrowseBase<Lead, LeadViewModel> {

	@Inject
	private LeadRepository repository;
	
	@Inject
	private LeadHome home;

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
				.addFilter(new StringFilter<>(Lead_.relatedAddress, "lead.label.RelatedAddress"))
				.addFilter(new SubStringFilter<>(Lead_.leadSource, TreeNodeEntityBase_.name, "lead.label.LeadSourceName"))
				.addFilter(new SubStringFilter<>(Lead_.leadCategory, TreeNodeEntityBase_.name, "lead.label.LeadCategoryName"));

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
				.addColumn(new SubTextColumn<>(Lead_.leadSource, TreeNodeEntityBase_.name, "lead.label.LeadSourceName"), false)
				.addColumn(new SubTextColumn<>(Lead_.leadCategory, TreeNodeEntityBase_.name, "lead.label.LeadCategoryName"), false)
				.addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false)
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), false);

	}

	@Override
	public VoucherFormBase<Lead> getHome() {
		return home;
	}

	@Override
	public VoucherRepositoryBase<Lead, LeadViewModel> getVoucherRepository() {
		return repository;
	}

}
