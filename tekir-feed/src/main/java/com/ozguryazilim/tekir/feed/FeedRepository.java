/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.tekir.entities.Feed;
import com.ozguryazilim.tekir.entities.FeedMention;
import com.ozguryazilim.tekir.entities.Feed_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * Feed Repository Class
 * 
 * @author Hakan Uygun
 */
@Repository
@Dependent
public abstract class FeedRepository extends RepositoryBase<Feed, Feed> implements CriteriaSupport<Feed>{


    /**
     * Geriye ismi verilen feature ile ilgili olan feedleri döndürür.
     * 
     * @param feature
     * @return 
     */
    @Query( "select f from Feed f where f.basePointer.feature = :feature or f.relatedPointer.feature = :feature")
    public abstract List<Feed> findForFeature( @QueryParam("feature") String feature );
    
    @Query( "select f from Feed f INNER JOIN f.mentions m where ( m.featurePointer.feature = :feature and m.featurePointer.primaryKey = :id ) order by f.date desc")
    public abstract QueryResult<Feed> findForFeature( @QueryParam("feature") String feature, @QueryParam("id") Long id );
    
    @Query( "select f from Feed f INNER JOIN f.mentions m where ( m.featurePointer.feature = :feature and m.featurePointer.primaryKey = :id ) and f.feeder not like 'ActivityFeeder.%' and f.feeder != 'NoteFeeder' order by f.date desc")
    public abstract QueryResult<Feed> findForFeaturePost( @QueryParam("feature") String feature, @QueryParam("id") Long id );
    
    public List<Feed> findForFeature( String feature, Long id, Integer limit ){
        return findForFeature(feature, id).maxResults(limit).getResultList();
    }
    
    /**    
     * Activity feedleri dışında kalan feedleri döndürür.
     */ 
    public List<Feed> findForFeaturePost( String feature, Long id, Integer limit ){
        return findForFeaturePost(feature, id).maxResults(limit).getResultList();
    }
    
    @Query( "select f from Feed f where f.user = :user")
    public abstract List<Feed> findForUser( @QueryParam("user") String username);
    
    //FIXME: Aslıdan burada doğrudan feed eden kullanıcı değil parametre olarak verilen kullanıcının grup ve follow bilgilerine göre feed döndürülmeli.
    public abstract List<Feed> findTop10ByUserOrderByDateDesc( String username);
    
    
    public List<Feed> findForUser( String username, List<String> groupUsers ){
        if( groupUsers.isEmpty() ){
            groupUsers.add( username );
        }
        String[] s = groupUsers.toArray(new String[]{});
        return criteria().in(Feed_.user, s)
                .orderDesc(Feed_.date)
                .createQuery()
                .setMaxResults(10)
                .getResultList();
    }
    
    @Query( "select m from Feed f INNER JOIN f.mentions m where ( m.featurePointer.feature in :features ) order by f.date desc")
    public abstract QueryResult<FeedMention> findMentionedFeature( @QueryParam("features") List<String> features );
    
    public List<FeedMention> findMentionedFeature( List<String> features, Integer limit ){
        return findMentionedFeature( features ).maxResults(limit).getResultList();
    }
    
    
    @Query( "select m.featurePointer from Feed f INNER JOIN f.mentions m where ( m.featurePointer.feature in :features ) group by m.featurePointer order by max(f.date) desc")
    public abstract QueryResult<FeaturePointer> findLatestMentionedFeature( @QueryParam("features") List<String> features );
    
    public List<FeaturePointer> findLatestMentionedFeature( List<String> features, Integer limit ){
        return findLatestMentionedFeature( features ).maxResults(limit).getResultList();
    }
}
