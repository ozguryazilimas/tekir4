package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.forms.SubView;
import com.ozguryazilim.telve.forms.SubViewPageBase;

/**
 *
 * @author oyas
 */
@SubView(containerPage = ContactPages.ContactView.class, viewPage = ContactPages.DocumentSubView.class, permission = "document", order = 42)
public class ContactDocumentSubView extends SubViewPageBase{

    @Override
    public void reload() {
        //Şimdilik ne yapılacak bilmiyorum
    }
    
}
