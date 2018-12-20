package com.ozguryazilim.finance.account;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.telve.lookup.LookupTableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 *
 * @author oyas
 */

@Lookup(dialogPage = FinancePages.FinanceAccountLookup.class)
public class FinanceAccountLookup extends LookupTableControllerBase<FinanceAccount, FinanceAccount> {

	@Inject
	private FinanceAccountRepository repository;

	private List<String> requiredRoles;
	private List<String> optinalRoles;
	private List<String> selectedOptinalRoles;

	@Override
	public void buildModel(LookupTableModel<FinanceAccount> model) {
		model.addColumn("code", "general.label.Code");
		model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<FinanceAccount, FinanceAccount> getRepository() {
		return repository;
	}

	@Override
	public void populateData() {
		getModel().setData(getData(getModel().getSearchText()));
	}
	
	@Override
    protected List<FinanceAccount> populateSuggestData(String text) {   	
    	List<FinanceAccount> resultList = getData(getModel().getSearchText());   	
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
		return FinanceAccount_.name.getName();
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
	
	private List<FinanceAccount> getData(String searchTest){
		String type = getModel().getProfileProperties().get("T");

		List<String> rls = new ArrayList<>();
		rls.addAll(requiredRoles);
		rls.addAll(selectedOptinalRoles);

		// Şimdide Repository'den sorgumuz yapıp datayı dolduruyoruz
		List<FinanceAccount> models = repository.lookupQuery(getModel().getSearchText(), type, rls);
		
		return models;
	}

}
