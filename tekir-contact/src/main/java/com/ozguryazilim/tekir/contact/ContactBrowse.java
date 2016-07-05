package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.StringFilter;
import javax.inject.Inject;

/**
 * Brwose Control Class
 * 
 * @author
 */
@Browse(browsePage = ContactPages.ContactBrowse.class, editPage = ContactPages.Contact.class, viewContainerPage = ContactPages.ContactView.class)
public class ContactBrowse extends BrowseBase<Contact, ContactViewModel> {

	@Inject
	private ContactRepository repository;

	@Override
	protected void buildQueryDefinition(QueryDefinition<Contact, ContactViewModel> queryDefinition) {
                queryDefinition
                    .addFilter(new StringFilter<>(Contact_.code, "general.label.Code"))
                    .addFilter(new StringFilter<>(Contact_.name, "general.label.Name"));
                
                queryDefinition
                    .addColumn(new LinkColumn<>(Contact_.code, "general.label.Code"), true)
                    .addColumn(new LinkColumn<>(Contact_.name, "general.label.Name"), true)
                    .addColumn(new TextColumn<>(Contact_.info, "general.label.Info"), true);
	}

	@Override
	protected RepositoryBase<Contact, ContactViewModel> getRepository() {
		return repository;
	}
}