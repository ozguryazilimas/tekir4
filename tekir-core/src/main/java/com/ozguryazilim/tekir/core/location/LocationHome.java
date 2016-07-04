package com.ozguryazilim.tekir.core.location;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.Location;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class LocationHome extends TreeBase<Location> {

	@Inject
	private LocationRepository repository;

	public LocationRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final LocationRepository repository) {
		this.repository = repository;
	}
}