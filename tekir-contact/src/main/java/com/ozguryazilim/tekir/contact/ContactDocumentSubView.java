/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
