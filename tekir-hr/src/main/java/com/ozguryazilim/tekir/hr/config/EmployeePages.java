/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.config;

import com.ozguryazilim.tekir.hr.employee.EmployeeFeature;
import com.ozguryazilim.tekir.hr.employee.leave.EmployeeLeaveFeature;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./employee")
public interface EmployeePages extends Pages {
    
    @View
	@SecuredPage("employee")
	@PageTitle("module.caption.EmployeeBrowse")
	@Navigation(label = "module.caption.EmployeeBrowse", feature = EmployeeFeature.class, section = HRNavigationSection.class)
	class EmployeeBrowse implements EmployeePages {
	}

	@View
	@SecuredPage("employee")
	@PageTitle("module.caption.Employee")
	class Employee implements EmployeePages {
	}

	@View
	@SecuredPage("employee")
	@PageTitle("module.caption.Employee")
	class EmployeeView
			implements
				EmployeePages {
	}

	@View
	@SecuredPage("employee")
	@PageTitle("module.caption.EmployeeMasterView")
	class EmployeeMasterView
			implements
				EmployeePages {
	}

	@SecuredPage() @View
	class EmployeeLookup
			implements
				EmployeePages {
	}
	
    @View
	@SecuredPage("employeeLeaveBrowse")
	@PageTitle("module.caption.EmployeeLeaveBrowse")
	@Navigation(label = "module.caption.EmployeeLeaveBrowse", feature = EmployeeLeaveFeature.class, section = HRNavigationSection.class)
	class EmployeeLeaveBrowse implements EmployeePages {
	}
    
	@View
	@SecuredPage("employeeLeave")
	@PageTitle("module.caption.EmployeeLeave")
	class EmployeeLeave implements EmployeePages {
	}
	
	@View
	@SecuredPage("employeeLeave")
	@PageTitle("module.caption.EmployeeLeave")
	class EmployeeLeaveView
			implements
				EmployeePages {
	}
    
	@View
	@SecuredPage("employeeLeave")
	@PageTitle("module.caption.EmployeeLeaveMasterView")
	class EmployeeLeaveMasterView
			implements
				EmployeePages {
	}

}
