package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(permission = "contact", forEntity = AbstractPerson.class )
@Page( type = PageType.BROWSE, page = ContactPages.ContactBrowse.class )
@Page( type = PageType.VIEW, page = ContactPages.ContactView.class )
@Page( type = PageType.EDIT, page = ContactPages.Contact.class )
public class AbstractPersonFeature extends AbstractFeatureHandler{
    
}
