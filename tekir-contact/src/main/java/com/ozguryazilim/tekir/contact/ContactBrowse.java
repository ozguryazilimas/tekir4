package com.ozguryazilim.tekir.contact;

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
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.StringFilter;
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
                    .addFilter(new StringFilter<>(Contact_.name, "general.label.Name"));
                
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