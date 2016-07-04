package com.ozguryazilim.tekir.core.location;

import com.ozguryazilim.tekir.entities.Location;
import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.TreeRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * Repository Class
 * 
 * @author
 */
@Repository
@Dependent
public abstract class LocationRepository extends TreeRepositoryBase<Location> implements CriteriaSupport<Location> {
}