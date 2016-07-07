/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.entities.ContactInformation;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 * Contact Information'lar ile ilgili temel UI controllerini sağlar.
 *
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class ContactInformationController implements Serializable {

    /**
     * Verilen info için hangi Icon'ın kullanılabileceğini belirler.
     *
     * TODO: Bunun için aslında ilgili editor'u bulup ondan istemek lazım.
     * Böylece daha sonradan farklı bilgi türleri eklenebilir. Hem model hem de
     * genel mimari buna uygun
     *
     * @param info
     * @return
     */
    public String getIcon(ContactInformation info) {

        String result = "fa fa-link";

        AbstractContactInformationEditor cie = getEditor(info);

        if (cie != null) {
            result = cie.getIcon(info);
        }

        return result;
    }

    public void edit(ContactInformation info) {
        AbstractContactInformationEditor cie = getEditor(info);

        if (cie != null) {
            cie.edit(info);
        }
    }

    @Transactional
    public void delete(ContactInformation info) {
        AbstractContactInformationEditor cie = getEditor(info);

        if (cie != null) {
            cie.delete(info);
        }

    }

    protected AbstractContactInformationEditor getEditor(ContactInformation info) {
        return ContactInformationEditorRegistery.getEditor(info.getClass());
    }
    
    public boolean isPrimary( ContactInformation info ){
        return info.getRoles().contains(ContactInformationConsts.Roles.PRIMARY);
    }
}
