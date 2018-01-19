package com.ozguryazilim.tekir.contact.relation;

import com.ozguryazilim.tekir.contact.ContactRoleRegistery;
import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.ContactRelation;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.util.List;
import javax.inject.Inject;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.ContactRelation", serial = "REL", size=3 )
public class ContactRelationHome extends ParamBase<ContactRelation, Long> {

    @Inject
    private ContactRelationRepository repository;

    @Inject
    private AutoCodeService codeService;

    @Override
    protected ContactRelation getNewEntity() {

        ContactRelation result = new ContactRelation();
        result.setCode(codeService.getNewSerialNumber(ContactRelationHome.class.getSimpleName()));

        return result;
    }

    public List<String> getContactRoles() {
        return ContactRoleRegistery.getContactRoles();
    }

    public List<String> completeContactRoles(String query) {
        return ContactRoleRegistery.getContactRoles();
    }

    public ContactRelationRepository getRepository() {
        return this.repository;
    }

    public void setRepository(final ContactRelationRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean onBeforeSave() {

        boolean result = true;

        if (getEntity().getSourceRoles().isEmpty()) {
            //FIXME: i18n
            FacesMessages.error("Kaynak Rolü Seçilmemiş", "En az bir adet kaynak rolü seçilmelidir.");
            result = false;

        }

        if (getEntity().getSourceRoles().isEmpty()) {
            //FIXME: i18n
            FacesMessages.error("Hedef Rolü Seçilmemiş", "En az bir hedef kaynak rolü seçilmelidir.");
            result = false;
        }

        //Vector ve Revers bileşiminden parametre ismi oluşturuyoruz.
        getEntity().setName(getEntity().getVectorName() + "/" + getEntity().getReversName());

        return result;
    }

}
