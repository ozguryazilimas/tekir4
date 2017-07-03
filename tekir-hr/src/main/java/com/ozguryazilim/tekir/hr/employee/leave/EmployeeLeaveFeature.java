/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee.leave;

import javax.enterprise.inject.Default;

import com.ozguryazilim.tekir.entities.EmployeeLeave;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.telve.feature.search.Search;

/**
*
* @author oktay
*/
@Feature(permission = "employeeLeave", forEntity = EmployeeLeave.class )
@Page( type = PageType.BROWSE, page = EmployeePages.EmployeeLeaveBrowse.class )
@Page( type = PageType.VIEW, page = EmployeePages.EmployeeLeaveView.class )
@Page( type = PageType.MASTER_VIEW, page = EmployeePages.EmployeeLeaveMasterView.class )
@Page( type = PageType.EDIT, page = EmployeePages.EmployeeLeave.class )
@Search(	handler	=	EmployeeLeaveHandler.class)
@Voucher @Default
public class EmployeeLeaveFeature extends AbstractFeatureHandler{
    
}
