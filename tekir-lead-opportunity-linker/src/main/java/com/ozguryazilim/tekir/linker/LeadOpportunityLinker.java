package com.ozguryazilim.tekir.linker;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.lead.LeadFeature;
import com.ozguryazilim.tekir.lead.LeadHome;
import com.ozguryazilim.tekir.opportunity.OpportunityHome;
import com.ozguryazilim.tekir.voucher.VoucherRedirectHandler;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;

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

	@Override
	public Class<? extends ViewConfig> redirect(VoucherStateChange event) {

		if ("WON".equals(event.getTo().getName())) {

			Person person = new Person();
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

			person.setCode(leadHome.getEntity().getVoucherNo() + person.getName());
			contactRepository.save(person);

			Corporation corporation = new Corporation();
			corporation.setOrganizastionName(leadHome.getEntity().getRelatedCompanyName());
			corporation.setName(leadHome.getEntity().getRelatedCompanyName());
			corporation.setCode(leadHome.getEntity().getVoucherNo() + corporation.getName());
			corporation.setPrimaryContact(person);
			contactRepository.save(corporation);

			FeaturePointer fp = new FeaturePointer();
			fp.setBusinessKey(leadHome.getEntity().getVoucherNo());
			fp.setPrimaryKey(leadHome.getEntity().getId());
			fp.setFeature(leadFeature.getName());
			opportunityHome.getEntity().setStarter(fp);

			Class<? extends ViewConfig> result = opportunityHome.create();

			opportunityHome.getEntity().setPrimaryContact(person);
			opportunityHome.getEntity().setAccount(corporation);

			return result;
		}

		return null;
	}

}
