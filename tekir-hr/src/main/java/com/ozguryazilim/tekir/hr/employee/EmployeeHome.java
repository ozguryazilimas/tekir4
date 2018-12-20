package com.ozguryazilim.tekir.hr.employee;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.contact.ContactRoleRegistery;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author oyas
 */
@FormEdit( feature = EmployeeFeature.class )
public class EmployeeHome extends FormBase<Employee, Long> {
    
    @Inject
    private Identity identity;
    
    @Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    @Inject
    private NavigationParameterContext navigationParameterContext;

    @Inject
    private EmployeeRepository repository;

    @Inject
    private ContactInformationRepository informationRepository;

    private List<String> selectedRoles = new ArrayList<>();

    public Class<? extends ViewConfig> newEmployee() {
        Employee p = new Employee();
        p.getContactRoles().add("EMPLOYEE");
        p.getContactRoles().add("ACCOUNT");
        p.setOwner(identity.getLoginName());
        setEntity(p);
        selectedRoles.clear();
        navigationParameterContext.addPageParameter("eid", 0);

        return EmployeePages.Employee.class;
    }

    @Override
    public boolean onBeforeSave() {
        //Eğer person ise name alanını düzeltmek lazım
        getEntity().setName(getEntity().getFirstName() + " " + getEntity().getLastName());
        

        //Önce kullanıcı seçimli olmayan rolleri bir toparlayalım
        List<String> ls = getEntity().getContactRoles().stream()
                .filter(p -> !getContactRoles().contains(p))
                .collect(Collectors.toList());
        
        //Şimdi kullanıcın seçtiklerini ekleyelim
        ls.addAll(selectedRoles);
        
        //Şimdi de yeni durumu yerleştirelim.
        getEntity().getContactRoles().clear(); 
        getEntity().getContactRoles().addAll(ls);
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onAfterLoad() {
        
        //FIXME: Burayı generic bir hale getirmek lazım                
        if( !identity.isPermitted("employee:select:" + getEntity().getOwner())){
            FacesMessages.error("facesMessages.error.NoPermission");
            createNew();
            viewNavigationHandler.navigateTo(EmployeePages.EmployeeBrowse.class);
            return false;
        }
        
        selectedRoles = getEntity().getContactRoles().stream()
                            .filter(p -> getContactRoles().contains(p))
                            .collect(Collectors.toList());
        
        return super.onAfterLoad();
    }

    
    
    @Override
    protected RepositoryBase<Employee, EmployeeViewModel> getRepository() {
        return repository;
    }

    /**
     * Geriye ilgili contact'a ait iletişim bilgilerini döndürür.
     *
     * @return
     */
    public List<ContactInformation> getContactInformations() {
        return informationRepository.findByContact(getEntity());
    }

    
    public List<String> getContactRoles() {
        return ContactRoleRegistery.getSelectableContactRoles();
    }

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }
    
    public Class<? extends FeatureHandler> getFeatureClass(){
        return FeatureRegistery.getFeatureClass(getEntity().getClass());
    }
    
    public FeaturePointer getFeaturePointer(){
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getName());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }
    
    /**
     * Belge sahipliğini değiştirme yetkisi var mı?
     * @return 
     */
    public Boolean hasChangeOwnerPermission() {
        return identity.isPermitted(getPermissionDomain() + ":changeOwner:" + getEntity().getOwner());
    }
    
    /**
     * Belge Sahibini değiştirir.
     * @param event 
     */
    public void onOwnerChange(SelectEvent event) {
        String userName = (String) event.getObject();
        if( Strings.isNullOrEmpty(userName)) return;
        getEntity().setOwner(userName);
        save();
    }
    
    //Kullanıcının PrimaryAddress girişleri üzerindeki yetkisi kontrol edilir
    public boolean hasContactInfoPermission(ContactInformation contactInfo, String action) {
        if (contactInfo instanceof ContactAddress) {
            boolean valid = identity.isPermitted("employeeAddresses" + ":" + action + ":" + getEntity().getOwner());
            //Fatura adresi olarak kullanılan adres üzerinde islem yapabilmek
            //için fatura adresi düzenleme yetkisine sahip olunması gerekir.
            if (contactInfo.getSubTypes().contains("INVOICE") && (action.equals("update") || action.equals("delete"))) {
                return valid && identity.isPermitted("employeeAddresses" + ":employeeInvoiceAddress:" + getEntity().getOwner());
            }
            return valid;
        }
        return false;
    }
}
