package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(permission = "contact", forEntity = Contact.class )
@Page( type = PageType.BROWSE, page = ContactPages.ContactBrowse.class )
@Page( type = PageType.VIEW, page = ContactPages.ContactView.class )
@Page( type = PageType.MASTER_VIEW, page = ContactPages.ContactMasterView.class )
@Page( type = PageType.EDIT, page = ContactPages.Contact.class )
@AutoCode(cosumer = "Contact", caption = "feature.caption.ContactFeature", serial = "CONT" )
public class ContactFeature extends AbstractFeatureHandler{
    
}
