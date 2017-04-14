package com.ozguryazilim.tekir.contact;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupModel;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = ContactPages.ContactLookup.class)
public class ContactLookup
		extends
			LookupTableControllerBase<Contact, ContactViewModel> {

	@Inject
	private ContactRepository repository;

        
        private List<String> requiredRoles;
        private List<String> optinalRoles;
        private List<String> selectedOptinalRoles;
        private Long callerContactId;
        
	@Override
	public void buildModel(LookupTableModel<ContactViewModel> model) {
		model.addColumn("code", "general.label.Code");
                model.addColumn("name", "general.label.Name");
	}

	@Override
	protected RepositoryBase<Contact, ContactViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return Contact_.name.getName();
	}
        
        
        @Override
    public void populateData() {
        getModel().setData(getData(getModel().getSearchText()));
    }
        
    @Override
    protected List<Contact> populateSuggestData(String text) {   	
    	List<ContactViewModel> vmList = getData(getModel().getSearchText());
    	List<Contact> resultList = new ArrayList<>();
    	
    	for(ContactViewModel cvm : vmList){
    		resultList.add(getEntity(cvm));
    	}
    	
    	return resultList;
    }

    @Override
    public void initProfile() {
        super.initProfile(); 
        
        //Gereken Roller
        String roles = getModel().getProfileProperties().get("R");
        if( !Strings.isNullOrEmpty(roles)){
            requiredRoles = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(roles);
        } else {
            requiredRoles = Collections.emptyList();
        }
        //Seçimlik rolles;
        String optroles = getModel().getProfileProperties().get("O");
        if( !Strings.isNullOrEmpty(optroles)){
            optinalRoles = new ArrayList<>(Splitter.on(',').omitEmptyStrings().trimResults().splitToList(optroles));
        } else {
            optinalRoles = new ArrayList<>();
        }
        
        String soptroles = getModel().getProfileProperties().get("S");
        if( !Strings.isNullOrEmpty(soptroles)){
            selectedOptinalRoles = new ArrayList<>(Splitter.on(',').omitEmptyStrings().trimResults().splitToList(soptroles));
            optinalRoles.addAll(selectedOptinalRoles);
        } else {
            selectedOptinalRoles = new ArrayList<>();
        }
        
        String cContact = getModel().getProfileProperties().get("C");
        
        if( !Strings.isNullOrEmpty(cContact)){
        	callerContactId = Long.parseLong(cContact);            
        } else {
        	callerContactId = -1l;
        }
        
    }
    
    
    
    /**
     * Lookup Dialog Başlığı
     * 
     * Profile'a göre farklılık gösterecek
     * @return 
     */
    public String getTitle(){
        String result = "contactLookup.Title";
        
        
        
        String type = getModel().getProfileProperties().get("T");
        if(!Strings.isNullOrEmpty(type)) switch( type ){
            case "Person" : result = "personLookup.Title"; break;
            case "Corporation" : result = "corporationLookup.Title"; break;
        }
        
        
        String roles = getModel().getProfileProperties().get("R");
        LookupModel<ContactViewModel, ?> a = getModel();
        if( !Strings.isNullOrEmpty(roles)){
            List<String> rls = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(roles);
        
            if( rls.contains("CUSTOMER") ){
                result = "customerLookup.Title";
            } else if ( rls.contains("VENDOR") ){
                result = "vendorLookup.Title";
            } else if ( rls.contains("ACCOUNT") ){
                result = "accountLookup.Title";
            }
        }
        
        return result;
    }

    public List<String> getRequiredRoles() {
        return requiredRoles;
    }

    public List<String> getOptinalRoles() {
        return optinalRoles;
    }
    
    public void toggleRole( String role ){
        if( selectedOptinalRoles.contains(role) ){
            selectedOptinalRoles.remove(role);
        } else {
            selectedOptinalRoles.add(role);
        }
        search();
    }
    
    public Boolean isRoleSelected( String role ){
        return selectedOptinalRoles.contains(role);
    }

	public Long getCallerContactId() {
		return callerContactId;
	}
	
    private List<ContactViewModel> getData( String searchText ){
    	  String type = getModel().getProfileProperties().get("T");
          
          List<String> rls = new ArrayList<>();
          rls.addAll(requiredRoles);
          rls.addAll(selectedOptinalRoles);
                  
          //Şimdide Repository'den sorgumuz yapıp datayı dolduruyoruz
          List<ContactViewModel> models = repository.lookupQuery(getModel().getSearchText(), type, rls);
                 
          models.removeIf(c -> c.getId().equals(callerContactId));
          return models;
    }
    
}