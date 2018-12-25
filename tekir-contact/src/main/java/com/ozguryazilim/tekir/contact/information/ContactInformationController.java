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

    /**
     * Kullanilan regex icin: https://gist.github.com/dperini/729294
     * Version: 2018/09/12
     * trim() metodu bastaki ve sondaki bosluk karakterlerini silmek icin kullaniliyor.
     */
    public boolean isLink(String address) {
        String urlRegex = "^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?"
            + ":169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1"
            + "\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|2"
            + "5[0-4]))|(?:(?:[a-z0-9\\u00a1-\\uffff][a-z0-9\\u00a1-\\uffff_-]{0,62})?[a-z0-9\\u00a1-\\uffff]\\.)+(?:[a"
            + "-z\\u00a1-\\uffff]{2,}\\.?))(?::\\d{2,5})?(?:[/?#]\\S*)?$";
        return address.trim().matches(urlRegex);
    }
}
