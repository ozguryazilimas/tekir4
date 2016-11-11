/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.tekir.entities.FeedFollow;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class FeedFollowRepository extends RepositoryBase<FeedFollow, FeedFollow> implements CriteriaSupport<FeedFollow>{
    
    @Query( value = "select f from FeedFollow f where f.featurePointer.feature = :feature and f.featurePointer.primaryKey = :id and f.user = :username", singleResult = SingleResultType.OPTIONAL)
    public abstract FeedFollow findByUserAndFeature( @QueryParam("username") String username, @QueryParam("feature") String feature, @QueryParam("id") Long id );
    
    
}
