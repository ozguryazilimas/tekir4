/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee;

import java.util.Date;

import com.ozguryazilim.tekir.contact.ContactViewModel;
import com.ozguryazilim.tekir.entities.Contact;

/**
 * Employee View Model.
 * 
 * TODO: Empoyee'ye özgü alanlar eklenecek.
 * 
 * @author Hakan Uygun
 */
public class EmployeeViewModel extends ContactViewModel{
	
	private String employeeNo;
	private String sgkNo;
	private Date recruitmentDate;
	private Date terminationDate;
    
    public EmployeeViewModel(Long id, String code, String name,String employeeNo,String sgkNo,Date recruitmentDate,
    		Date terminationDate,String info, Boolean active, Class<? extends Contact> type, Long pmMobileId, 
    		String pmMobile, Long pmPhoneId, String pmPhone, Long pmEmailId, String pmEmail) {
        super(id, code, name, info, active, type, pmMobileId, pmMobile, pmPhoneId, pmPhone, pmEmailId, pmEmail);
        
        this.employeeNo=employeeNo;
        this.sgkNo=sgkNo;
        this.recruitmentDate=recruitmentDate;
        this.terminationDate=terminationDate;
        
    }

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getSgkNo() {
		return sgkNo;
	}

	public void setSgkNo(String sgkNo) {
		this.sgkNo = sgkNo;
	}

	public Date getRecruitmentDate() {
		return recruitmentDate;
	}

	public void setRecruitmentDate(Date recruitmentDate) {
		this.recruitmentDate = recruitmentDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}
   
   
}
