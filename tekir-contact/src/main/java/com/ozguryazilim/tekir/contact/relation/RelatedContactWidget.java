package com.ozguryazilim.tekir.contact.relation;

import com.ozguryazilim.tekir.contact.relation.ContactRelationRepository;
import com.ozguryazilim.tekir.contact.relation.ContactRelationService;
import com.ozguryazilim.tekir.contact.relation.ContactRelationViewModel;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureController;
import com.ozguryazilim.telve.feature.FeatureLink;
import com.ozguryazilim.telve.feature.FeatureUtils;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

/**
 *
 * @author oyas
 */
@Named
@GroupedConversationScoped
public class RelatedContactWidget implements Serializable{
    
    @Inject
    private ContactRelationRepository relationRepository;
    
    @Inject
    private ContactRelationService relationService;
    
    @Inject
    private FeatureController featureController;
    
    private Contact contact;
    
    private ContactRelationViewModel selectedRelation;
    
    private RelatedContactViewModel relatedContact;
    
    public void init( Contact contact ){
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    
    
    public List<ContactRelationViewModel> getRelations(){
        return relationRepository.findByContactRole(getContact().getContactRoles());
    }
    
    
    public List<RelatedContactViewModel> getRelatedContacts(){
        return relationService.getRelatedContacts(contact);
    }

    public ContactRelationViewModel getSelectedRelation() {
        return selectedRelation;
    }

    public void setSelectedRelation(ContactRelationViewModel selectedRelation) {
        this.selectedRelation = selectedRelation;
    }

    public RelatedContactViewModel getRelatedContact() {
        return relatedContact;
    }

    public void setRelatedContact(RelatedContactViewModel relatedContact) {
        this.relatedContact = relatedContact;
    }
    
    public FeaturePointer getFeaturePointer( Contact contact ){
        return FeatureUtils.getFeaturePointer(contact);
    }
    
    public FeatureLink getFeatureLink( Contact contact ){
        return  featureController.getFeatureLink(getFeaturePointer(contact));
    }

    public void createRelation( ContactRelationViewModel relation ){
        relatedContact = new RelatedContactViewModel(contact, relation);
    }
    
    
    public void save(){
        relationService.saveRelatedContact(relatedContact);
        relatedContact = null;
    }
    
    public void edit( RelatedContactViewModel relatedContactViewModel ){
        relatedContact = relatedContactViewModel;
    }
    
    @Transactional
    public void delete( RelatedContactViewModel relatedContactViewModel ){
        relationService.deleteRelatedContact(relatedContactViewModel);
        relatedContact = null;
    }
}
