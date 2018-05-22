package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.category.ContactCategoryLookup;
import com.ozguryazilim.tekir.core.filters.ListAttributeStringListFilter;
import com.ozguryazilim.tekir.core.industry.IndustryLookup;
import com.ozguryazilim.tekir.core.territory.TerritoryLookup;
import com.ozguryazilim.tekir.entities.ContactCategory_;
import com.ozguryazilim.tekir.entities.Industry_;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactEMail_;
import com.ozguryazilim.tekir.entities.ContactPhone_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.EntityOverlayFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;
import com.ozguryazilim.telve.query.filters.TreeEntityFilter;
import com.ozguryazilim.telve.query.filters.UserFilter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Brwose Control Class
 * 
 * @author
 */
@Browse( feature = ContactFeature.class)
public class ContactBrowse extends BrowseBase<Contact, ContactViewModel> {

	@Inject
	private ContactRepository repository;
        
        @Inject
        private Identity identity;

	@Override
	protected void buildQueryDefinition(QueryDefinition<Contact, ContactViewModel> queryDefinition) {
                queryDefinition
                    .addFilter(new StringFilter<>(Contact_.code, "general.label.Code"))
                    .addFilter(new StringFilter<>(Contact_.name, "general.label.Name"))
                    .addFilter(new SubStringFilter<>(Contact_.primaryMobile, ContactPhone_.address, "contact.label.PrimaryMobile"))
                    .addFilter(new SubStringFilter<>(Contact_.primaryPhone, ContactPhone_.address, "contact.label.PrimaryPhone"))
                    .addFilter(new SubStringFilter<>(Contact_.primaryEmail, ContactEMail_.address, "contact.label.PrimaryEmail"))
                    .addFilter(new StringFilter<>(Contact_.info, "general.label.Info"))
                    .addFilter(new TreeEntityFilter<>(Contact_.category, ContactCategory_.path,
                        ContactCategoryLookup.class, "general.label.Category"))
                    .addFilter(new TreeEntityFilter<>(Contact_.industry, Industry_.path,
                        IndustryLookup.class, "general.label.Industry"))
                    .addFilter(new EntityOverlayFilter<>(Contact_.territory, TerritoryLookup.class,
                        "general.label.Territory"))
                    .addFilter(new ListAttributeStringListFilter<>("contactRoles",
                        ContactRoleRegistery.getFilterableContactRoles(), "general.label.Tag",
                        "contact.role."))
                    .addFilter(new UserFilter<>(Contact_.owner, "general.label.Owner"));

                queryDefinition
                    .addColumn(new LinkColumn<>(Contact_.code, "general.label.Code"), true)
                    .addColumn(new LinkColumn<>(Contact_.name, "general.label.Name"), true)
                    .addColumn(new SubTextColumn<>(Contact_.primaryMobile, ContactPhone_.address, "contact.label.PrimaryMobile"), true)
                    .addColumn(new SubTextColumn<>(Contact_.primaryPhone, ContactPhone_.address, "contact.label.PrimaryPhone"), true)
                    .addColumn(new SubTextColumn<>(Contact_.primaryEmail, ContactEMail_.address, "contact.label.PrimaryEmail"), true)
                    .addColumn(new TextColumn<>(Contact_.info, "general.label.Info"), true);
	}
	
	



	@Override
	protected RepositoryBase<Contact, ContactViewModel> getRepository() {
            //TODO: Kullanıcı yetki kontrolü yapılacak
            //owner,group,all durumuna bakılacak
            //Bu davranışı nasıl generic hale getirebilirim?
            
            
            if( identity.isPermitted("contact:select:*") ){
                //Her tülü yetkili zaten dolayısı ile group felan ile uğraşmayalım
            } else 
                if( identity.isPermitted("contact:select:$group") ){
                List<String> ls = identity.getGroupsMembers();
                if( ls.isEmpty() ){
                    ls.add("NONE");
                }
                repository.setOwnerFilter(identity.getGroupsMembers());
            } else if( identity.isPermitted("contact:select:$owner") ){
                List<String> ls = new ArrayList<>();
                ls.add(identity.getLoginName());
                repository.setOwnerFilter(ls);
            }
            
            return repository;
	}
        
        public Contact getContact(){
            if( getSelectedItem() != null ){
                return repository.findBy(getSelectedItem().getId());
            } else {
                return null;
            }
        }
        
        public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
        }
}