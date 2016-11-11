/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.tekir.entities.FeedFollow;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 * Fişler üzerinde Feed için gereken UI kontrol işlemleri sınıfı.
 * 
 * Contact üzerinde bir menü item ile takip ve takipten çıkma olanağı sağlar.
 * 
 * @author Hakan Uygun
 */
@Named
@RequestScoped
public class FeedFollowMenuAction implements Serializable{

    @Inject
    private Identity identity;
    
    @Inject
    private FeedFollowRepository repository;
    
    @Transactional
    public void toggle( FeaturePointer featurePointer ){
        System.out.println("Follow toggled" + featurePointer);
        FeedFollow ff = repository.findByUserAndFeature(identity.getLoginName(), featurePointer.getFeature(), featurePointer.getPrimaryKey());
        if( ff == null ){
            FeedFollow fn = new FeedFollow();
            fn.setDate(new Date());
            fn.setFeaturePointer(featurePointer);
            fn.setUser( identity.getLoginName());
            repository.save(fn);
        } else {
            repository.remove(ff);
        }
    }
    
    public Boolean isFeatureFollowed( FeaturePointer featurePointer ){
        FeedFollow ff = repository.findByUserAndFeature(identity.getLoginName(), featurePointer.getFeature(), featurePointer.getPrimaryKey());
        return ff != null;
    }
    
}
