/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.salarynote;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.entities.SalaryNote;
import com.ozguryazilim.tekir.entities.SalaryNote_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.MoneyColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;

/**
 * @author oktay
 *
 */
@Browse( feature=SalaryNoteFeature.class )
public class SalaryNoteBrowse extends VoucherBrowseBase<SalaryNote , SalaryNoteViewModel>{

	@Inject
	private Identity identity;

	@Inject
	private SalaryNoteRepository repository;
	
	@Inject
	private SalaryNoteHome home;
	

	@Override
	protected void buildQueryDefinition(QueryDefinition<SalaryNote, SalaryNoteViewModel> queryDefinition) {
		queryDefinition
		.addFilter(new StringFilter<>(SalaryNote_.voucherNo, "voucher.label.VoucherNo"))
		.addFilter(new DateFilter<>(SalaryNote_.paymentDate, "hr.label.PaymentDate",FilterOperand.All,DateValueType.LastMonth))
		.addFilter(new StringFilter<>(VoucherBase_.code, "voucher.label.Code"))
		.addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
		.addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))

		;
		
		queryDefinition
		.addColumn(new LinkColumn<>(SalaryNote_.voucherNo, "voucher.label.VoucherNo"), true)
		.addColumn(new SubTextColumn<>(SalaryNote_.financeAccount, FinanceAccount_.name, "general.label.Name"), true)
		.addColumn(new LinkColumn<>(SalaryNote_.paymentDate, "hr.label.PaymentDate"), true)
		.addColumn(new MoneyColumn<>(SalaryNote_.total, SalaryNote_.currency, "general.label.Total"), true)
        .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), true)
        .addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
        .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false)
		.addColumn(new TextColumn<>(VoucherBase_.owner, "voucher.label.Owner"), false)
		.addColumn(new TextColumn<>(VoucherBase_.referenceNo, "voucher.label.ReferenceNo"), false)
        .addColumn(new TextColumn<>(VoucherBase_.code, "voucher.label.Code"), false)
        .addColumn(new TextColumn<>(VoucherBase_.info, "voucher.label.Info"), false)
        .addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
        .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false);

		
	}

	@Override
	public VoucherRepositoryBase<SalaryNote, SalaryNoteViewModel> getVoucherRepository() {
		return repository;
	}
	
	@Override
	public VoucherFormBase<SalaryNote> getHome() {
		// TODO Auto-generated method stub
		return home;
	}
}
