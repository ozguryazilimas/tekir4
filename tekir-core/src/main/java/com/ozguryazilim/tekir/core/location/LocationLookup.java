package com.ozguryazilim.tekir.core.location;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.lookup.LookupTreeControllerBase;
import com.ozguryazilim.telve.lookup.LookupTreeModel;
import com.ozguryazilim.tekir.entities.Location;
import com.ozguryazilim.tekir.entities.Location_;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = CorePages.LocationLookup.class)
public class LocationLookup
		extends
			LookupTreeControllerBase<Location, Location> {

	@Inject
	private LocationRepository repository;

	@Override
	protected RepositoryBase<Location, Location> getRepository() {
		return repository;
	}
	
	@Override
	public String getNodeType(Location node) {
		return node.getType().toString().toLowerCase();
	}
	@Override
	public String getCaptionFieldName() {
		return Location_.name.getName();
	}
}