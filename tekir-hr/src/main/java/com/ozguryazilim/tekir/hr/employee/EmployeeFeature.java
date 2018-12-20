package com.ozguryazilim.tekir.hr.employee;

import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(permission = "employee", forEntity = Employee.class )
@Page( type = PageType.BROWSE, page = EmployeePages.EmployeeBrowse.class )
@Page( type = PageType.VIEW, page = EmployeePages.EmployeeView.class )
@Page( type = PageType.MASTER_VIEW, page = EmployeePages.EmployeeMasterView.class )
@Page( type = PageType.EDIT, page = EmployeePages.Employee.class )
public class EmployeeFeature extends AbstractFeatureHandler{
    
}
