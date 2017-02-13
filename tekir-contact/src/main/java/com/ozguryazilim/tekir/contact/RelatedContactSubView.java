/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactRelation_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.RelatedContact;
import com.ozguryazilim.tekir.entities.RelatedContact_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.SubView;
import com.ozguryazilim.telve.forms.SubViewQueryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@SubView(containerPage = ContactPages.ContactView.class, viewPage = ContactPages.RelatedContactSubView.class, permission = "relatedContact", order = 42)
public class RelatedContactSubView extends SubViewQueryBase<RelatedContact, RelatedContactViewModel>{
    
    @Inject
    private ContactHome contactHome;
    
    @Inject
    private RelatedContactRepository repository;

    @Override
    protected void buildQueryDefinition(QueryDefinition<RelatedContact, RelatedContactViewModel> queryDefinition) {
        queryDefinition
                //.addColumn(new SubTextColumn<>(RelatedContact_.sourceContact, Contact_.name, "contact.label.Source"), true)
                .addColumn(new SubTextColumn<>(RelatedContact_.targetContact, Contact_.name, "contact.label.Contact"), true)
                .addColumn(new SubTextColumn<>(RelatedContact_.relation, ContactRelation_.name, "contact.label.Relation"), true)
                .addColumn(new TextColumn<>(RelatedContact_.info, "general.label.Info"), true);
    }

    @Override
    protected RepositoryBase<RelatedContact, RelatedContactViewModel> getRepository() {
        repository.setContact(getSourceContact());
        return repository;
    }
    
    @Override
    public boolean onBeforeSave(){
        getEntity().setSourceContact(getSourceContact());
        
        return true;
    }
    
    public Contact getSourceContact(){
    	return contactHome.getEntity();
    }
    
}
