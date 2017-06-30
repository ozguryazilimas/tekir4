/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.credit;

import com.ozguryazilim.tekir.entities.EmployeeCreditNote;
import com.ozguryazilim.tekir.entities.EmployeeCreditNote_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.EmployeeCreditNoteType;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.hr.employee.EmployeeRepository;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateFilter;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateTypeFilter;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.MessageColumn;
import com.ozguryazilim.telve.query.columns.MoneyColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.columns.UserColumn;
import com.ozguryazilim.telve.query.filters.BigDecimalFilter;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.EnumFilter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;
import com.ozguryazilim.telve.query.filters.UserFilter;
import javax.inject.Inject;

/**
 *
 * @author Erdem Uslu
 */
@Browse( feature=EmployeeCreditNoteFeature.class )
public class EmployeeCreditNoteBrowse extends VoucherBrowseBase<EmployeeCreditNote, EmployeeCreditNoteViewModel>{

    @Inject
    private EmployeeCreditNoteRepository repository;
    
    @Inject
    private EmployeeRepository employeeRepository;
    
    @Inject 
    private EmployeeCreditNoteHome home;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<EmployeeCreditNote, EmployeeCreditNoteViewModel> queryDefinition) {
        
        EnumFilter typeFilter = new EnumFilter(EmployeeCreditNote_.type, EmployeeCreditNoteType.SALARY, "employeeCreditNote.label.Type", "employeeCreditNote.type.label.");
        typeFilter.setOperand(FilterOperand.All);
        
        queryDefinition
                .addColumn(new DateColumn<>(VoucherBase_.date, "general.label.Date"), true)
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new SubTextColumn<>(EmployeeCreditNote_.employee, Contact_.name, "hr.label.Employee"), true)
                .addColumn(new TextColumn<>(VoucherBase_.info, "general.label.Info"), true)
                .addColumn(new TextColumn<>(VoucherBase_.code, "general.label.Code"), false)
                .addColumn(new TextColumn<>(VoucherBase_.referenceNo, "voucher.label.ReferenceNo"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false)
                .addColumn(new UserColumn<>(VoucherBase_.owner, "voucher.label.Owner"), true)
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), false)
                .addColumn(new DateColumn<>(EmployeeCreditNote_.paymentDate, "hr.label.PaymentDate"), false)
                .addColumn(new MoneyColumn<>(EmployeeCreditNote_.amount, EmployeeCreditNote_.currency, "general.label.Amount"), true)
                .addColumn(new SubTextColumn<>(EmployeeCreditNote_.financeAccount, FinanceAccount_.name, "general.label.FinanceAccount"), false)
                .addColumn(new MessageColumn<>(EmployeeCreditNote_.type, "employeeCreditNote.label.Type", "employeeCreditNote.type.label."), false);
        
        queryDefinition
                .addFilter(new StringFilter<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"))
                .addFilter(new StringFilter<>(VoucherBase_.code, "voucher.label.Code"))
                .addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
                .addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))
                .addFilter(new VoucherStateFilter<>(VoucherBase_.state, getHome().getStateConfig().getStates(), "general.label.State"))
                .addFilter(new VoucherStateTypeFilter<>(VoucherBase_.state, "voucher.label.StateType"))
                .addFilter(new StringFilter<>(VoucherBase_.stateReason, "voucher.label.StateReason"))
                .addFilter(new UserFilter<>(VoucherBase_.owner, "voucher.label.Owner"))
                .addFilter(new BigDecimalFilter<>(EmployeeCreditNote_.amount, "general.label.Amount"))
                .addFilter(typeFilter)
                .addFilter(new SubStringFilter<>(EmployeeCreditNote_.employee, Contact_.name, "hr.label.Employee"))
                .addFilter(new SubStringFilter<>(EmployeeCreditNote_.financeAccount, FinanceAccount_.name, "general.label.FinanceAccount"))
                .addFilter(new DateFilter<>(EmployeeCreditNote_.paymentDate, "hr.label.PaymentDate", FilterOperand.All, DateValueType.LastTenDays))
                .addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date", FilterOperand.All, DateValueType.LastTenDays));
                
    }

    @Override
    public VoucherRepositoryBase<EmployeeCreditNote, EmployeeCreditNoteViewModel> getVoucherRepository() {
        return repository;
    }
    
     @Override
    public VoucherFormBase<EmployeeCreditNote> getHome() {
    	// TODO Auto-generated method stub
    	return home;
    }
    
    public Employee getEmployee() {
        if (getSelectedItem() != null) {
            return getSelectedItem().getEmployee();
        } else {
            return null;
        }
    } 
    
}
