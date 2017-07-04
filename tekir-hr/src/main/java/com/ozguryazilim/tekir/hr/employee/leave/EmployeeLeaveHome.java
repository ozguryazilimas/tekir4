/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee.leave;

import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.EmployeeLeave;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.reports.JasperReportHandler;

/**
*
* EmployeeLeave View Controller
* @author oktay
*/
@FormEdit(feature = EmployeeLeaveFeature.class)
public class EmployeeLeaveHome extends VoucherFormBase<EmployeeLeave>{

	private static Logger LOG = LoggerFactory.getLogger(EmployeeLeaveHome.class);
	
    @Inject
    private Identity identity;
    
    @Inject
    private ViewNavigationHandler viewNavigationHandler;

    @Inject
    private EmployeeLeaveRepository repository;
    
    @Inject
    private JasperReportHandler reportHandler;

	@Override
	protected RepositoryBase<EmployeeLeave, ?> getRepository() {
		return repository;
	}
	
    @Override
    public boolean onBeforeSave() {
        
        if( getEntity().getState().equals(VoucherState.DRAFT) && !getEntity().isPersisted()){
            getEntity().setState(VoucherState.OPEN);
        }
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();        
        
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check" ), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("approved", "fa fa-check" ), VoucherState.WON);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true ), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true ), VoucherState.DRAFT);
        
        config.addStateTypeAction(VoucherStateType.OPEN, new VoucherPrintOutAction(this));
        config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));
        
        return config;
    }
    
	public FeaturePointer getAllFeaturePointer(EntityBase contact){
	   		return FeatureUtils.getFeaturePointer(contact);
	}

}
