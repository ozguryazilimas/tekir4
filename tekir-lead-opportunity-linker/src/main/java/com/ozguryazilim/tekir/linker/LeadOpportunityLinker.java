package com.ozguryazilim.tekir.linker;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.audit.AuditLogCommand;
import com.ozguryazilim.telve.audit.AuditLogger;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.AfterLiteral;
import com.ozguryazilim.telve.qualifiers.BeforeLiteral;
import com.ozguryazilim.telve.qualifiers.EntityQualifierLiteral;
import com.ozguryazilim.telve.utils.EntityUtils;

import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.auth.Identity;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.core.territory.TerritoryRepository;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.tekir.entities.TerritoryItem;
import com.ozguryazilim.tekir.lead.LeadFeature;
import com.ozguryazilim.tekir.lead.LeadHome;
import com.ozguryazilim.tekir.opportunity.OpportunityHome;
import com.ozguryazilim.tekir.voucher.VoucherRedirectHandler;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.messages.FormatedMessage;
import com.ozguryazilim.telve.messages.Messages;

@Dependent
@FeatureQualifier(feauture = LeadFeature.class)
public class LeadOpportunityLinker implements VoucherRedirectHandler {

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private Event<EntityChangeEvent> entityChangeEvent;

    @Inject
    private AuditLogger auditLogger;

    @Inject
    private Identity identity;

    @Inject
    private LeadHome leadHome;

    @Inject
    private OpportunityHome opportunityHome;

    @Inject
    private LeadFeature leadFeature;

    @Inject
    private FormatedMessage formatedMessage;

    @Inject
    private TerritoryRepository territoryRepository;

    @Inject
    private AutoCodeService codeService;

    @Override
    public Class<? extends ViewConfig> redirect(VoucherStateChange event) {

        if ("WON".equals(event.getTo().getName())) {

            // Territory ve location beraber null ise null bırakılıyor, bir şey yapılmıyor.
            Territory territory = leadHome.getEntity().getTerritory();
            if (territory == null) {
                // Territory nullken ve location null değilken yeni territory oluşturulup location buna ekleniyor.
                if (leadHome.getEntity().getLocation() != null) {
                    territory = new Territory();
                    TerritoryItem ti = new TerritoryItem();
                    ti.setParent(territory);
                    ti.setLocation(leadHome.getEntity().getLocation());
                    territory.getItems().add(ti);
                    territoryRepository.save(territory);
                }
            } // Territory ve location null değilken de location direkt territorye ekleniyor.
            else if (leadHome.getEntity().getLocation() != null) {
                TerritoryItem ti = new TerritoryItem();
                ti.setParent(territory);
                ti.setLocation(leadHome.getEntity().getLocation());
                territory.getItems().add(ti);
            }

            //Contact Person
            Person person = new Person();
            person.getContactRoles().add("CONTACT");
            person.getContactRoles().add("PERSON");
            person.setFirstName(leadHome.getEntity().getRelatedPersonName());
            person.setLastName(leadHome.getEntity().getRelatedPersonSurname());
            person.setName( leadHome.getEntity().getRelatedPersonName() + " " + leadHome.getEntity().getRelatedPersonSurname());
            person.setOwner(identity.getLoginName());

            ContactAddress address = new ContactAddress();
            address.setAddress(leadHome.getEntity().getRelatedAddress());
            person.setPrimaryAddress(address);
            address.setContact(person);

            ContactEMail email = new ContactEMail();
            email.setAddress(leadHome.getEntity().getRelatedEmail());
            person.setPrimaryEmail(email);
            email.setContact(person);

            ContactPhone phone = new ContactPhone();
            phone.setAddress(leadHome.getEntity().getRelatedPhone());
            person.setPrimaryPhone(phone);
            phone.setContact(person);

            FeaturePointer fp = new FeaturePointer();
            fp.setBusinessKey(leadHome.getEntity().getVoucherNo());
            fp.setPrimaryKey(leadHome.getEntity().getId());
            fp.setFeature(leadFeature.getName());
            person.setCode((codeService.getNewSerialNumber(Person.class.getSimpleName())));
            person.setTerritory(territory);
            person.setIndustry(leadHome.getEntity().getIndustry());
            person.setSourcePointer(fp);

            entityChangeEvent
                    .select(new EntityQualifierLiteral(Contact.class))
                    .select(new BeforeLiteral())
                    .fire(new EntityChangeEvent(person, EntityChangeAction.INSERT));

            contactRepository.save(person);

            entityChangeEvent
                    .select(new EntityQualifierLiteral(Contact.class))
                    .select(new AfterLiteral())
                    .fire(new EntityChangeEvent(person, EntityChangeAction.INSERT));

            auditLogger.actionLog(person.getClass().getSimpleName(), person.getId(),
                    EntityUtils.getBizKeyValue(person), AuditLogCommand.CAT_ENTITY,
                    AuditLogCommand.ACT_INSERT, identity.getLoginName(), "");

            //Contact Corporation
            Corporation corporation = new Corporation();
            corporation.getContactRoles().add("CONTACT");
            corporation.getContactRoles().add("CORPORATION");
            corporation.setOrganizastionName(leadHome.getEntity().getRelatedCompanyName());
            corporation.setName(leadHome.getEntity().getRelatedCompanyName());
            corporation.setOwner(identity.getLoginName());
            corporation.setCode((codeService.getNewSerialNumber(Corporation.class.getSimpleName())));
            corporation.setPrimaryContact(person);
            corporation.setTerritory(territory);
            corporation.setIndustry(leadHome.getEntity().getIndustry());
            corporation.setSourcePointer(fp);

            entityChangeEvent
                    .select(new EntityQualifierLiteral(Contact.class))
                    .select(new BeforeLiteral())
                    .fire(new EntityChangeEvent(corporation, EntityChangeAction.INSERT));

            contactRepository.save(corporation);

            entityChangeEvent
                    .select(new EntityQualifierLiteral(Contact.class))
                    .select(new AfterLiteral())
                    .fire(new EntityChangeEvent(corporation, EntityChangeAction.INSERT));

            auditLogger.actionLog(corporation.getClass().getSimpleName(), corporation.getId(),
                    EntityUtils.getBizKeyValue(corporation), AuditLogCommand.CAT_ENTITY,
                    AuditLogCommand.ACT_INSERT, identity.getLoginName(), "");

            //Opportunity
            Class<? extends ViewConfig> result = opportunityHome.create();

            opportunityHome.getEntity().setStarter(fp);

            opportunityHome.getEntity().setPrimaryContact(person);
            opportunityHome.getEntity().setAccount(corporation);
            opportunityHome.getEntity().setTopic(leadHome.getEntity().getTopic());
            opportunityHome.getEntity().setGroup(leadHome.getEntity().getGroup());

            //FIXME: Summary ve Detail mesajları farklılaşmalı.
            FacesMessages.info(Messages.getMessage("lead-opportunity-linker.messages.WON", leadHome.getEntity().getVoucherNo()));

            return result;
        }

        return null;
    }

}
