/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee.leave;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.EmployeeLeave;
import com.ozguryazilim.tekir.entities.EmployeeLeave_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.core.filters.IntegerFilter;
import com.ozguryazilim.tekir.entities.Contact_;

import com.ozguryazilim.tekir.hr.employee.EmployeeFeature;
import com.ozguryazilim.tekir.hr.employee.EmployeeRepository;
import com.ozguryazilim.tekir.hr.employee.EmployeeViewModel;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.BigDecimalFilter;
import com.ozguryazilim.telve.query.filters.BooleanFilter;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.NumberFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;

/**
 *
 * @author oktay
 */
@Browse(feature = EmployeeLeaveFeature.class)
public class EmployeeLeaveBrowse extends BrowseBase<EmployeeLeave, EmployeeLeaveViewModel> {

	@Inject
	private EmployeeLeaveRepository repository;

	@Inject
	private Identity identity;

	@Override
	protected void buildQueryDefinition(QueryDefinition<EmployeeLeave, EmployeeLeaveViewModel> queryDefinition) {
		
		BooleanFilter bf = new BooleanFilter<>(EmployeeLeave_.annual, "hr.label.Annual", "general.boolean.yes.");
		bf.setOperand(FilterOperand.All);		
		
        queryDefinition
        		.addFilter(new SubStringFilter<>(EmployeeLeave_.employee, Contact_.name,  "hr.label.Employee"))
        		.addFilter(new DateFilter<>(EmployeeLeave_.startDate, "hr.label.StartDate",FilterOperand.All,DateValueType.LastMonth))
        		.addFilter(new DateFilter<>(EmployeeLeave_.endDate, "hr.label.EndDate",FilterOperand.All,DateValueType.NextMonth))	
        		.addFilter(bf)
        		.addFilter(new IntegerFilter<>(EmployeeLeave_.leaveDay,"hr.label.LeaveDay"));
        		
		queryDefinition
				.addColumn(new LinkColumn<>(EmployeeLeave_.employee, "hr.label.Employee"), true)
				.addColumn(new LinkColumn<>(EmployeeLeave_.paid, "hr.label.Paid"), true)
				.addColumn(new LinkColumn<>(EmployeeLeave_.annual, "hr.label.Annual"), true)
				.addColumn(new LinkColumn<>(EmployeeLeave_.startDate, "hr.label.StartDate"), true)
				.addColumn(new LinkColumn<>(EmployeeLeave_.endDate, "hr.label.EndDate"), true)
				.addColumn(new LinkColumn<>(EmployeeLeave_.leaveDay, "hr.label.LeaveDay"), true);

	}

	@Override
	protected RepositoryBase<EmployeeLeave, EmployeeLeaveViewModel> getRepository() {
		// TODO: Kullanıcı yetki kontrolü yapılacak
		// owner,group,all durumuna bakılacak
		// Bu davranışı nasıl generic hale getirebilirim?

		if (identity.isPermitted("employee:select:*")) {
			// Her tülü yetkili zaten dolayısı ile group felan ile uğraşmayalım
		} else if (identity.isPermitted("employee:select:$group")) {
			List<String> ls = identity.getGroupsMembers();
			if (ls.isEmpty()) {
				ls.add("NONE");
			}
			repository.setOwnerFilter(identity.getGroupsMembers());
		} else if (identity.isPermitted("employee:select:$owner")) {
			List<String> ls = new ArrayList<>();
			ls.add(identity.getLoginName());
			repository.setOwnerFilter(ls);
		}

		return repository;
	}

	public EmployeeLeave getEmployee() {
		if (getSelectedItem() != null) {
			return repository.findBy(getSelectedItem().getId());
		} else {
			return null;
		}
	}
}
