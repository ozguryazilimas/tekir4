/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee.leave;

import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

import com.ozguryazilim.tekir.entities.EmployeeLeave;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.tekir.hr.employee.EmployeeRepository;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;

/**
*
* @author oktay
*/
@FormEdit(feature = EmployeeLeaveFeature.class)
public class EmployeeLeaveHome extends FormBase<EmployeeLeave, Long>{

	
    @Inject
    private Identity identity;

    @Inject
    private EmployeeLeaveRepository repository;

	@Override
	protected RepositoryBase<EmployeeLeave, EmployeeLeaveViewModel> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}
    public Class<? extends ViewConfig> newEmployeeLeave() {
        return EmployeePages.EmployeeLeave.class;
    }
}
