package com.ozguryazilim.tekir.core.location;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.Location;
import com.ozguryazilim.tekir.entities.LocationType;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;

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
        
        public List<LocationType> getAvailableTypes(){
            if(getEntity().getParent() != null){
                return LocationType.getSubTypes(getEntity().getParent().getType());
            }
            else{
                return LocationType.getSubTypes(LocationType.LOCATION);
            }
        }
        
        @Override
	public String getNodeType(Location node) {
		// TODO Auto-generated method stub
		return node.getType().toString().toLowerCase();
	}
}