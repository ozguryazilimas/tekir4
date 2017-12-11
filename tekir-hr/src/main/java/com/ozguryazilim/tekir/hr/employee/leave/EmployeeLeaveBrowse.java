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
import com.ozguryazilim.tekir.entities.Employee_;
import com.ozguryazilim.tekir.entities.EmployeeLeave_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.core.filters.IntegerFilter;
import com.ozguryazilim.tekir.entities.Contact_;

import com.ozguryazilim.tekir.hr.employee.EmployeeFeature;
import com.ozguryazilim.tekir.hr.employee.EmployeeRepository;
import com.ozguryazilim.tekir.hr.employee.EmployeeViewModel;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateFilter;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateTypeFilter;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.BooleanColumn;
import com.ozguryazilim.telve.query.columns.DateColumn;
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
import com.ozguryazilim.telve.query.filters.UserFilter;

/**
 *
 * @author oktay
 */
@Browse(feature = EmployeeLeaveFeature.class)
public class EmployeeLeaveBrowse extends VoucherBrowseBase<EmployeeLeave, EmployeeLeaveViewModel> {

	@Inject
	private EmployeeLeaveRepository repository;

	@Inject
	private Identity identity;
	
    @Inject 
    private EmployeeLeaveHome home;

	@Override
	protected void buildQueryDefinition(QueryDefinition<EmployeeLeave, EmployeeLeaveViewModel> queryDefinition) {
		
		BooleanFilter p = new BooleanFilter<>(EmployeeLeave_.paid, "hr.label.Paid", "general.boolean.yesno.");
		p.setOperand(FilterOperand.All);		
		BooleanFilter an = new BooleanFilter<>(EmployeeLeave_.annual, "hr.label.Annual", "general.boolean.yesno.");
		an.setOperand(FilterOperand.All);		
		
        queryDefinition
        		.addFilter(new StringFilter<>(EmployeeLeave_.voucherNo, "voucher.label.VoucherNo"))
        		.addFilter(new SubStringFilter<>(EmployeeLeave_.employee, Contact_.name,  "hr.label.Employee"))
        		.addFilter(p)
        		.addFilter(an)
        		.addFilter(new DateFilter<>(EmployeeLeave_.startDate, "hr.label.StartDate",FilterOperand.All,DateValueType.LastMonth))
        		.addFilter(new DateFilter<>(EmployeeLeave_.endDate, "hr.label.EndDate",FilterOperand.All,DateValueType.NextMonth))	
        		.addFilter(new IntegerFilter<>(EmployeeLeave_.leaveDay,"hr.label.LeaveDay"))
        		.addFilter(new StringFilter<>(VoucherBase_.code, "voucher.label.Code"))
        		.addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
        		.addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))
                .addFilter(new VoucherStateFilter<>(VoucherBase_.state, getHome().getStateConfig().getStates(), "general.label.State"))
                .addFilter(new VoucherStateTypeFilter<>(VoucherBase_.state, "voucher.label.StateType"))
                .addFilter(new StringFilter<>(VoucherBase_.stateReason, "voucher.label.StateReason"))
                .addFilter(new UserFilter<>(VoucherBase_.owner, "voucher.label.Owner"))
        		.addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date",FilterOperand.All,DateValueType.NextMonth));
        		
		queryDefinition
				.addColumn(new LinkColumn<>(EmployeeLeave_.voucherNo, "voucher.label.VoucherNo"), true)
				.addColumn(new SubTextColumn<>(EmployeeLeave_.employee, Contact_.name, "general.label.Name"), true)
				.addColumn(new BooleanColumn<>(EmployeeLeave_.paid, "hr.label.Paid", "general.boolean.yesno."),false)
				.addColumn(new BooleanColumn<>(EmployeeLeave_.annual, "hr.label.Annual", "general.boolean.yesno."),false)
				.addColumn(new DateColumn<>(EmployeeLeave_.startDate, "hr.label.StartDate"), true)
				.addColumn(new DateColumn<>(EmployeeLeave_.endDate, "hr.label.EndDate"), true)
				.addColumn(new TextColumn<>(EmployeeLeave_.leaveDay, "hr.label.LeaveDay"), true)
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), false)
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

	public EmployeeLeave getEmployeeLeave() {
		if (getSelectedItem() != null) {
			return repository.findBy(getSelectedItem().getId());
		} else {
			return null;
		}
	}

	@Override
	public VoucherRepositoryBase<EmployeeLeave, EmployeeLeaveViewModel> getVoucherRepository() {
		return repository;
	}
    
    @Override
    public VoucherFormBase<EmployeeLeave> getHome() {
    	return home;
    }
	
	public FeaturePointer getAllFeaturePointer(EntityBase contact){
	   		return FeatureUtils.getFeaturePointer(contact);
	}

}
