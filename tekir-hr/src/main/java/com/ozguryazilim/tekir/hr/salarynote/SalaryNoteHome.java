/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.salarynote;

import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.slf4j.LoggerFactory;

import com.ozguryazilim.tekir.entities.SalaryNote;
import com.ozguryazilim.tekir.hr.employee.EmployeeFeature;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import com.sun.istack.logging.Logger;

/**
 * @author oktay
 *
 */
@FormEdit( feature = SalaryNoteFeature.class )
public class SalaryNoteHome extends VoucherFormBase<SalaryNote> {

	@Inject
	private Identity identity;

	@Inject
	private ViewNavigationHandler viewNavigationHandler;

	@Inject
	private SalaryNoteRepository repository;

	@Override
	protected RepositoryBase<SalaryNote, ?> getRepository() {
		return repository;
	}

	@Override
	protected VoucherStateConfig buildStateConfig() {
		return null;
	}

}
