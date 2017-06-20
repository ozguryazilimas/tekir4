package com.ozguryazilim.tekir.linker;

import javax.enterprise.context.Dependent;
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
import com.ozguryazilim.tekir.lead.LeadFeature;
import com.ozguryazilim.tekir.lead.LeadHome;
import com.ozguryazilim.tekir.opportunity.OpportunityHome;
import com.ozguryazilim.tekir.voucher.VoucherRedirectHandler;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.messages.FormatedMessage;
import com.ozguryazilim.telve.messages.MessagesUtils;

@Dependent
@FeatureQualifier(feauture = LeadFeature.class)
public class LeadOpportunityLinker implements VoucherRedirectHandler {

	@Inject
	private ContactRepository contactRepository;

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

	@Override
	public Class<? extends ViewConfig> redirect(VoucherStateChange event) {

		if ("WON".equals(event.getTo().getName())) {

			// Territory ve location beraber null ise null bırakılıyor, bir şey
			// yapılmıyor.
			Territory territory = leadHome.getEntity().getTerritory();
			if (territory == null) {
				// Territory nullken ve location null değilken yeni territory
				// oluşturulup location buna ekleniyor.
				if (leadHome.getEntity().getLocation() != null) {
					territory = new Territory();
					territory.getLocations().add(leadHome.getEntity().getLocation());
					territoryRepository.save(territory);
				}
			}
			// Territory ve location null değilken de location
			// direkt territorye ekleniyor.
			else if (leadHome.getEntity().getLocation() != null) {
				territory.getLocations().add(leadHome.getEntity().getLocation());
			}

			Person person = new Person();
			person.getContactRoles().add("CONTACT");
			person.getContactRoles().add("PERSON");
			person.setFirstName(leadHome.getEntity().getRelatedPersonName());
			person.setLastName(leadHome.getEntity().getRelatedPersonSurname());
			person.setName(
					leadHome.getEntity().getRelatedPersonName() + " " + leadHome.getEntity().getRelatedPersonSurname());

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
                        
			person.setCode(leadHome.getEntity().getVoucherNo() + person.getName());
			person.setTerritory(territory);
			person.setIndustry(leadHome.getEntity().getIndustry());
                        person.setSourcePointer(fp);
			contactRepository.save(person);

			Corporation corporation = new Corporation();
			corporation.getContactRoles().add("CONTACT");
			corporation.getContactRoles().add("CORPORATION");
			corporation.setOrganizastionName(leadHome.getEntity().getRelatedCompanyName());
			corporation.setName(leadHome.getEntity().getRelatedCompanyName());
			corporation.setCode(leadHome.getEntity().getVoucherNo() + corporation.getName());
			corporation.setPrimaryContact(person);
			corporation.setTerritory(territory);
			corporation.setIndustry(leadHome.getEntity().getIndustry());
                        corporation.setSourcePointer(fp);
			contactRepository.save(corporation);

			FeaturePointer fp = new FeaturePointer();
			fp.setBusinessKey(leadHome.getEntity().getVoucherNo());
			fp.setPrimaryKey(leadHome.getEntity().getId());
			fp.setFeature(leadFeature.getName());

			Class<? extends ViewConfig> result = opportunityHome.create();

			opportunityHome.getEntity().setStarter(fp);

			opportunityHome.getEntity().setPrimaryContact(person);
			opportunityHome.getEntity().setAccount(corporation);
			opportunityHome.getEntity().setTopic(leadHome.getEntity().getTopic());
			opportunityHome.getEntity().setGroup(leadHome.getEntity().getGroup());

			FacesMessages
					.info(formatedMessage.getMessage(MessagesUtils.getMessage("lead-opportunity-linker.messages.WON"),
							new Object[] { leadHome.getEntity().getVoucherNo() }));

			return result;
		}

		return null;
	}

}