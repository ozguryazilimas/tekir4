/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.tekir.entities.AccountType;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

/**
 *
 * @author oyas
 */
@FormEdit( feature = FinanceAccountFeature.class )
public class FinanceAccountHome extends FormBase<FinanceAccount, Long> {

    @Inject
    private FinanceAccountRepository repository;

    @Inject
    private Identity identity;
    
    @Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    private List<String> selectedRoles = new ArrayList<>();
    
    @Override
    public FinanceAccountRepository getRepository() {
        return this.repository;
    }
    
    
    public Class<? extends ViewConfig> newCashAccount() {
        FinanceAccount p = new FinanceAccount();
        p.getAccountRoles().add("CASH");
        p.setType(AccountType.CASH);
        p.setOwner(identity.getLoginName());
        setEntity(p);
        selectedRoles.clear();
        return FinancePages.FinanceAccount.class;
    }
    
    public Class<? extends ViewConfig> newBankAccount() {
        FinanceAccount p = new FinanceAccount();
        p.getAccountRoles().add("BANK");
        p.setType(AccountType.BANK);
        p.setOwner(identity.getLoginName());
        setEntity(p);
        selectedRoles.clear();
        return FinancePages.FinanceAccount.class;
    }
    
    public Class<? extends ViewConfig> newCreditCardAccount() {
        FinanceAccount p = new FinanceAccount();
        p.getAccountRoles().add("CREDIT_CARD");
        p.setType(AccountType.CREDIT_CARD);
        p.setOwner(identity.getLoginName());
        setEntity(p);
        selectedRoles.clear();
        return FinancePages.FinanceAccount.class;
    }
    
    @Override
    public boolean onBeforeSave() {
        //Eğer person ise name alanını düzeltmek lazım
        if (getEntity().getType() == AccountType.BANK ) {
            getEntity().setName( getEntity().getBank() + " " + getEntity().getBranch() + " " + getEntity().getAccountNo() );
        }

        //Önce kullanıcı seçimli olmayan rolleri bir toparlayalım
        List<String> ls = getEntity().getAccountRoles().stream()
                .filter(p -> !getAccountRoles().contains(p))
                .collect(Collectors.toList());
        
        //Şimdi kullanıcın seçtiklerini ekleyelim
        ls.addAll(selectedRoles);
        
        //Şimdi de yeni durumu yerleştirelim.
        getEntity().getAccountRoles().clear(); 
        getEntity().getAccountRoles().addAll(ls);
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onAfterLoad() {
        
        //FIXME: Burayı generic bir hale getirmek lazım                
        if( !identity.isPermitted("financeAccount:select:" + getEntity().getOwner())){
            FacesMessages.error("Kayda erişim için yetkiniz yok!");
            createNew();
            viewNavigationHandler.navigateTo(FinancePages.FinanceAccountBrowse.class);
            return false;
        }
        
        selectedRoles = getEntity().getAccountRoles().stream()
                            .filter(p -> getAccountRoles().contains(p))
                            .collect(Collectors.toList());
        
        return super.onAfterLoad();
    }
    
    public List<String> getAccountRoles() {
        return AccountRoleRegitery.getSelectableAccountRoles();
    }

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    @Override
    public Class<? extends FeatureHandler> getFeatureClass(){
        return FinanceAccountFeature.class;
    }
    
    public FeaturePointer getFeaturePointer(){
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getName());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }
}
