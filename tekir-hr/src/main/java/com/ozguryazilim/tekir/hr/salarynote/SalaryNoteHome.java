/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.salarynote;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.SalaryNote;
import com.ozguryazilim.tekir.entities.SalaryNoteItem;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateEffect;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.hr.employee.EmployeeRepository;
import com.ozguryazilim.tekir.hr.employee.EmployeeViewModel;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.reports.JasperReportHandler;

/**
 * @author oktay
 *
 */
@FormEdit( feature = SalaryNoteFeature.class )
public class SalaryNoteHome extends VoucherFormBase<SalaryNote> implements SalaryNoteItemEditorListener<SalaryNoteItem> {

	private static Logger LOG = LoggerFactory.getLogger(SalaryNoteHome.class);
	
	@Inject
	private Identity identity;

	@Inject
	private SalaryNoteRepository repository;
	
	@Inject
	private EmployeeRepository employeeRepository;
	
	@Inject
	private SalaryNoteItemEditor salaryNoteItemEditor;
	
	@Inject
	private CurrencyService currencyService;

	@Inject
	private JasperReportHandler reportHandler;

	
	@Override
	protected RepositoryBase<SalaryNote, ?> getRepository() {
		return repository;
	}
	
	@Override
    public void createNew() {
		super.createNew();
		List<Employee> emps =getEmployees();
    	for(Employee emp : emps){
    		SalaryNoteItem item = new SalaryNoteItem();
    		item.setEmployee(emp);
    		item.setAmount(getEntity().getTotal());
    		saveItem(item);
    	}
    }

	@Override
	public void saveItem(SalaryNoteItem item) {
        if ( item.getId() == null && item.getMaster() == null ) {
            item.setMaster(getEntity());
            getEntity().getItems().add(item);
        }
        calculateSummaries();
	}
	
	@Override
	public void addItem() {
		SalaryNoteItem item = new SalaryNoteItem();
		salaryNoteItemEditor.openDialog(item, getEntity().getCurrency(), this);
	}

	@Override
	public void editItem(SalaryNoteItem item) {
		salaryNoteItemEditor.openDialog(item, getEntity().getCurrency(), this);
	}

	@Override
	public void removeItem(SalaryNoteItem item) {
		getEntity().getItems().remove(item);
		calculateSummaries();
	}
	
	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
		
	}
	
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();      
        
        VoucherState paid = new VoucherState("PAID", VoucherStateType.CLOSE, VoucherStateEffect.POSITIVE);
        VoucherState partialPaid = new VoucherState("PARTIAL", VoucherStateType.OPEN, VoucherStateEffect.NEUTRAL);
        VoucherState unpaid = new VoucherState("UNPAID", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE);
        VoucherState partialUnpaid = new VoucherState("PARTUNPAID", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE);
        
        VoucherStateAction paidAction = new VoucherStateAction("paid");
        VoucherStateAction partialPaidAction = new VoucherStateAction("partial");
        
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("approved", "fa fa-check" ), VoucherState.WON);
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true), unpaid);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, partialPaidAction, partialPaid);
        config.addTranstion(VoucherState.OPEN, paidAction, paid);
        config.addTranstion( partialPaid, paidAction, paid);
        config.addTranstion( partialPaid, new VoucherStateAction("loss", "fa fa-close", true), partialUnpaid);
        config.addTranstion( partialPaid, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.REVISE);
        config.addTranstion(VoucherState.REVISE, new VoucherStateAction("publish", "fa fa-check", true), VoucherState.OPEN);
        
        
        config.addStateAction(VoucherState.REVISE, new VoucherPrintOutAction(this));        
        config.addStateTypeAction(VoucherStateType.OPEN, new VoucherPrintOutAction(this));
        config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));
        
        return config;
    }

	public FeaturePointer getAllFeaturePointer(EntityBase contact){
   		return FeatureUtils.getFeaturePointer(contact);
	}

	public void calculateSummaries() {
		List<SalaryNoteItem> items = getEntity().getItems();	
    	BigDecimal t = BigDecimal.ZERO;
    	for(SalaryNoteItem item : items){
    		t=t.add(item.getEmployee().getSalaryAmount());
    	}
    	getEntity().setTotal(t);
	}
	
}
