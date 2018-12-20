package com.ozguryazilim.tekir.hr.employee;

import com.ozguryazilim.tekir.entities.ContactEMail_;
import com.ozguryazilim.tekir.entities.ContactPhone_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.Employee_;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.StringFilter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature = EmployeeFeature.class)
public class EmployeeBrowse extends BrowseBase<Employee, EmployeeViewModel>{

        @Inject
	private EmployeeRepository repository;
        
        @Inject
        private Identity identity;

	@Override
	protected void buildQueryDefinition(QueryDefinition<Employee, EmployeeViewModel> queryDefinition) {
                queryDefinition
                    .addFilter(new StringFilter<>(Contact_.code, "general.label.Code"))
                    .addFilter(new StringFilter<>(Contact_.name, "general.label.Name"))
                    .addFilter(new StringFilter<>(Employee_.employeeNo, "hr.label.EmployeeNo"))
            		.addFilter(new StringFilter<>(Employee_.sgkNo, "hr.label.SgkNo"));
                
                queryDefinition
                    .addColumn(new LinkColumn<>(Contact_.code, "general.label.Code"), true)
                    .addColumn(new LinkColumn<>(Contact_.name, "general.label.Name"), true)
                    .addColumn(new TextColumn<>(Employee_.employeeNo, "hr.label.EmployeeNo"), true)
                    .addColumn(new TextColumn<>(Employee_.sgkNo, "hr.label.SgkNo"), true)
                    .addColumn(new DateColumn<>(Employee_.recruitmentDate, "hr.label.RecruitmentDate"), false)
                    .addColumn(new DateColumn<>(Employee_.terminationDate, "hr.label.TerminationDate"), false)
                    .addColumn(new SubTextColumn<>(Contact_.primaryMobile, ContactPhone_.address, "contact.label.PrimaryMobile"), true)
                    .addColumn(new SubTextColumn<>(Contact_.primaryPhone, ContactPhone_.address, "contact.label.PrimaryPhone"), true)
                    .addColumn(new SubTextColumn<>(Contact_.primaryEmail, ContactEMail_.address, "contact.label.PrimaryEmail"), true)
                    .addColumn(new TextColumn<>(Contact_.info, "general.label.Info"), true);
	}

	@Override
	protected RepositoryBase<Employee, EmployeeViewModel> getRepository() {
            //TODO: Kullanıcı yetki kontrolü yapılacak
            //owner,group,all durumuna bakılacak
            //Bu davranışı nasıl generic hale getirebilirim?
            
            
            if( identity.isPermitted("employee:select:*") ){
                //Her tülü yetkili zaten dolayısı ile group felan ile uğraşmayalım
            } else 
                if( identity.isPermitted("employee:select:$group") ){
                List<String> ls = identity.getGroupsMembers();
                if( ls.isEmpty() ){
                    ls.add("NONE");
                }
                repository.setOwnerFilter(identity.getGroupsMembers());
            } else if( identity.isPermitted("employee:select:$owner") ){
                List<String> ls = new ArrayList<>();
                ls.add(identity.getLoginName());
                repository.setOwnerFilter(ls);
            }
            
            return repository;
	}
        
        public Employee getEmployee(){
            if( getSelectedItem() != null ){
                return repository.findBy(getSelectedItem().getId());
            } else {
                return null;
            }
        }    
}
