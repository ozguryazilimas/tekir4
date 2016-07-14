/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(caption = "feature.caption.Contact", icon = "fa fa-user", permission = "contact", forEntity = Contact.class )
@Page( type = PageType.BROWSE, page = ContactPages.ContactBrowse.class )
@Page( type = PageType.VIEW, page = ContactPages.ContactView.class )
@Page( type = PageType.EDIT, page = ContactPages.Contact.class )
public class ContactFeature extends AbstractFeatureHandler{
    
}
