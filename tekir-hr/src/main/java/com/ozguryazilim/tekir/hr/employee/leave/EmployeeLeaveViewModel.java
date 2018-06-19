/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee.leave;

import java.util.Currency;
import java.util.Date;

import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.hr.employee.EmployeeViewModel;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.VoucherViewModel;
import java.util.List;

/**
 * Employee Leave View Model.
 * 
* @author oktay
*/
public class EmployeeLeaveViewModel extends VoucherViewModel{
    private Employee employee;
    private Boolean paid;
    private Boolean annual;
    private Date startDate;
    private Date endDate;
    private Integer leaveDay;  
    
    
	public EmployeeLeaveViewModel(Long id, List<String> tags, String voucherNo, String info, String referenceNo, Date date,
			String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic,
			Employee employee, Boolean paid, Boolean annual, Date startDate, Date endDate,
			Integer leaveDay) {
		super(id, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
		this.employee = employee;
		this.paid = paid;
		this.annual = annual;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveDay = leaveDay;
	}


	public EmployeeLeaveViewModel(Long id, List<String> tags, String voucherNo, String info, String referenceNo, Date date,
			String owner, VoucherState state, String stateReason, String stateInfo, Long groupId,String groupNo, String topic,
			Long employeeId,String employeeName, Boolean paid, Boolean annual, Date startDate, Date endDate, Integer leaveDay) {
		super(id, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId,groupNo, topic);

		this.employee = new Employee();
		this.employee.setId(employeeId);
		this.employee.setName(employeeName);
		
		this.paid = paid;
		this.annual = annual;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveDay = leaveDay;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Boolean getPaid() {
		return paid;
	}


	public void setPaid(Boolean paid) {
		this.paid = paid;
	}


	public Boolean getAnnual() {
		return annual;
	}


	public void setAnnual(Boolean annual) {
		this.annual = annual;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getLeaveDay() {
		return leaveDay;
	}


	public void setLeaveDay(Integer leaveDay) {
		this.leaveDay = leaveDay;
	}   
}
