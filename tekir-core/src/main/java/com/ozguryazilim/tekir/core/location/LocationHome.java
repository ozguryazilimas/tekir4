package com.ozguryazilim.tekir.core.location;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.Location;
import com.ozguryazilim.tekir.entities.LocationType;
import java.util.List;
import javax.inject.Inject;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.Location", size = 3)
public class LocationHome extends TreeBase<Location> {

    @Inject
    private LocationRepository repository;
    
    @Inject
    private AutoCodeService codeService;

    @Override
    public LocationRepository getRepository() {
        return this.repository;
    }

    @Override
    public void createNewChild() {
        //Önce gereken kurallar ile sınıf oluşsun.
        super.createNewChild(); 
        
        String parentCode = null;
        
        if( getEntity().getParent() != null ){
            parentCode = getEntity().getParent().getCode();
        }
        
        getEntity().setCode(codeService.getNewSerialNumber(LocationHome.class.getSimpleName(), parentCode));
    }

    @Override
    public void createNewSibling() {
        //Önce gereken kurallar ile sınıf oluşsun
        super.createNewSibling(); 
        String parentCode = null;
        
        if( getEntity().getParent() != null ){
            parentCode = getEntity().getParent().getCode();
        }
        
        getEntity().setCode(codeService.getNewSerialNumber(LocationHome.class.getSimpleName(), parentCode));
    }
    
    

    public List<LocationType> getAvailableTypes() {
        if (getEntity().getParent() != null) {
            return LocationType.getSubTypes(getEntity().getParent().getType());
        } else {
            return LocationType.getSubTypes(LocationType.LOCATION);
        }
    }

    @Override
    public String getNodeType(Location node) {
        return node.getType().toString().toLowerCase();
    }
}
