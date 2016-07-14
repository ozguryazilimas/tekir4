/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.tekir.entities.Feed;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

/**
 * Feed View Controller sınıfı.
 * 
 * @author Hakan Uygun
 */
@Named
@GroupedConversationScoped
public class FeedController implements Serializable{
    
    @Inject
    private FeedRepository repository;
    
    
    public List<Feed> getFeeds( String feature ){
        return repository.findForFeature(feature);
    }
    
}
