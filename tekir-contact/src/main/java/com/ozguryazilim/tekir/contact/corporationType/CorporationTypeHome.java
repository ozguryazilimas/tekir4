package com.ozguryazilim.tekir.contact.corporationType;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.CorporationType;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class CorporationTypeHome extends ParamBase<CorporationType, Long> {

	@Inject
	private CorporationTypeRepository repository;

	public CorporationTypeRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final CorporationTypeRepository repository) {
		this.repository = repository;
	}
}