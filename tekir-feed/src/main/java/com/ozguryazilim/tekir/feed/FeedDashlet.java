/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.tekir.entities.Feed;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.dashboard.AbstractDashlet;
import com.ozguryazilim.telve.dashboard.Dashlet;
import com.ozguryazilim.telve.dashboard.DashletCapability;
import java.util.List;
import javax.inject.Inject;

/**
 * Kullanıcının ayar bilgilerini ( takip listesi, kaç adet, ilgi filtresi v.b. ) kullanarak feed gösterir.
 * 
 * @author Hakan Uygun
 */
@Dashlet(capability = {DashletCapability.canHide, DashletCapability.canMinimize, DashletCapability.canRefresh})
public class FeedDashlet extends AbstractDashlet{
    
    @Inject
    private Identity identity;
    
    @Inject
    private FeedRepository repository;

    private List<Feed> feeds;
    
    @Override
    public void load() {
        //TODO: Filtre felan yapmak lazım. Kullanıcı hangi tür haberleri görmek istediğini seçebilmeli.
        List<String> grps = identity.getGroupsMembers();
        feeds = repository.findForUser(identity.getLoginName(), grps );
    }

    @Override
    public void refresh() {
        load();
    }
    
    public List<Feed> getFeeds(){
        return feeds;
    }
    
}
