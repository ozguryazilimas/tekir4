/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.Employee_;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.telve.lookup.LookupTableModel;

/**
 * Lookup View Control Class
 * 
 * @author oktay
 *
 */
@Lookup(dialogPage = EmployeePages.EmployeeLookup.class)
public class EmployeeLookup extends LookupTableControllerBase<Employee, EmployeeViewModel> {

	@Inject
	private EmployeeRepository repository;

	private List<String> requiredRoles;
	private List<String> optinalRoles;
	private List<String> selectedOptinalRoles;

	@Override
	protected void buildModel(LookupTableModel<EmployeeViewModel> model) {
		model.addColumn("code", "general.label.Code");
		model.addColumn("name", "hr.label.Employee");
	}

	@Override
	protected RepositoryBase<Employee, EmployeeViewModel> getRepository() {
		return repository;
	}
	@Override
    protected List<Employee> populateSuggestData(String text) {  
		List<EmployeeViewModel> vmList = getData(getModel().getSearchText());
    	List<Employee> resultList = new ArrayList<>(); 	
    	
    	for(EmployeeViewModel evm : vmList){
    		resultList.add(getEntity(evm));
    	}
    	return resultList;
    }
	
	
	@Override
	public void initProfile() {
		super.initProfile();

		// Gereken Roller
		String roles = getModel().getProfileProperties().get("R");
		if (!Strings.isNullOrEmpty(roles)) {
			requiredRoles = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(roles);
		} else {
			requiredRoles = Collections.emptyList();
		}
		// Seçimlik rolles;
		String optroles = getModel().getProfileProperties().get("O");
		if (!Strings.isNullOrEmpty(optroles)) {
			optinalRoles = new ArrayList<>(Splitter.on(',').omitEmptyStrings().trimResults().splitToList(optroles));
		} else {
			optinalRoles = new ArrayList<>();
		}

		String soptroles = getModel().getProfileProperties().get("S");
		if (!Strings.isNullOrEmpty(soptroles)) {
			selectedOptinalRoles = new ArrayList<>(
					Splitter.on(',').omitEmptyStrings().trimResults().splitToList(soptroles));
			optinalRoles.addAll(selectedOptinalRoles);
		} else {
			selectedOptinalRoles = new ArrayList<>();
		}	
	}
	
	@Override
	public String getCaptionFieldName() {
		return Employee_.name.getName();
	}

	public List<String> getRequiredRoles() {
		return requiredRoles;
	}

	public List<String> getOptinalRoles() {
		return optinalRoles;
	}

	public void toggleRole(String role) {
		if (selectedOptinalRoles.contains(role)) {
			selectedOptinalRoles.remove(role);
		} else {
			selectedOptinalRoles.add(role);
		}
		search();
	}

	public Boolean isRoleSelected(String role) {
		return selectedOptinalRoles.contains(role);
	}

	private List<EmployeeViewModel> getData(String searchTest) {
		String type = getModel().getProfileProperties().get("T");

		List<String> rls = new ArrayList<>();
		rls.addAll(requiredRoles);
		rls.addAll(selectedOptinalRoles);

		// Şimdide Repository'den sorgumuz yapıp datayı dolduruyoruz
		List<EmployeeViewModel> models = repository.lookupQuery(getModel().getSearchText(), type, rls);

		return models;
	}

    /**
     * Lookup Dialog Başlığı
     * 
     * Profile'a göre farklılık gösterecek
     * @return 
     */
    public String getTitle(){
        String result = "employeeLookup.Title";
        return result;
    }    
        
	
}
