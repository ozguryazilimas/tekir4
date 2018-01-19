/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.dashlets;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.mutfak.kahve.annotations.UserAware;
import com.ozguryazilim.tekir.contact.CorporationFeature;
import com.ozguryazilim.tekir.contact.AbstractPersonFeature;
import com.ozguryazilim.tekir.feed.FeedRepository;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.dashboard.AbstractDashlet;
import com.ozguryazilim.telve.dashboard.Dashlet;
import com.ozguryazilim.telve.dashboard.DashletCapability;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Feed API üzerinden her hangi bir hareket görmüş 5-10 contact'ı listeler.
 * 
 * Tarih aralığı, Sadece kendisinin hareketleri ve sayı üzerinden liste sunar.
 * 
 * @author Hakan Uygun
 */
@Dashlet(capability = {DashletCapability.canHide, DashletCapability.canMinimize, DashletCapability.canRefresh})
public class ActiveContactsDashlet extends AbstractDashlet{
    @Inject @UserAware
    private Kahve kahve;
    
    @Inject
    private FeedRepository repository;
    
    @Inject
    private Identity identity;
    
    private List<FeaturePointer> items;
    
    private List<String> features = new ArrayList<>();
    private Boolean mineOnly = Boolean.FALSE;
    private Integer limit = 10;
    
    
    //private PieChartModel pieChartModel;
    
    
    private String style="TABLE"; //CHART|TABLE
            
    
    @Override
    public void load() {
    
        features.clear();
        features.add(AbstractPersonFeature.class.getSimpleName());
        features.add(CorporationFeature.class.getSimpleName());
        
        /*
        KahveEntry ke = kahve.get("topAccountsDashlet.limit");
        if( ke != null ){
            limit = ke.getAsInteger();
        }

        ke = kahve.get("topAccountsDashlet.featureName");
        if( ke != null ){
            featureName = ke.getAsString();
        }

        ke = kahve.get("topAccountsDashlet.mineOnly");
        if( ke != null ){
            mineOnly = ke.getAsBoolean();
        }

        
        ke = kahve.get("topAccountsDashlet.startPeriod");
        if( ke != null ){
            startPeriod = ke.getAsString();
        }
        
        ke = kahve.get("topAccountsDashlet.style");
        if( ke != null ){
            style = ke.getAsString();
        }
        */
        populate();
        
    }
    
    @Override
    public void save(){
        /*
        kahve.put( "topAccountsDashlet.limit", limit);
        kahve.put( "topAccountsDashlet.featureName", featureName);
        kahve.put( "topAccountsDashlet.mineOnly", mineOnly);
        kahve.put( "topAccountsDashlet.startPeriod", startPeriod);
        kahve.put( "topAccountsDashlet.style", style);
        */
        refresh();
    }

    protected void populate(){
        
        
        String username = "";
        
        if( getMineOnly() ){
            username = identity.getLoginName();
        }

        
        
        items = repository.findLatestMentionedFeature( features, limit );
        
        
        //buildChartModel();
    }
    
    /*
    protected void buildChartModel(){
        pieChartModel = new PieChartModel();
        items.forEach((sm) -> {
            pieChartModel.set(sm.getAccountName(), sm.getAmount());
        });
        
        pieChartModel.setTitle("Top 10 Customers");
        pieChartModel.setLegendPosition("e");
        pieChartModel.setFill(false);
        pieChartModel.setShowDataLabels(true);
        pieChartModel.setShowDatatip(true);
        pieChartModel.setDiameter(150);
    }
    */
    
    @Override
    public void refresh() {
        populate();
    }

    public List<FeaturePointer> getItems() {
        return items;
    }

    public void setItems(List<FeaturePointer> items) {
        this.items = items;
    }


    public Boolean getMineOnly() {
        return mineOnly;
    }

    public void setMineOnly(Boolean mineOnly) {
        this.mineOnly = mineOnly;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    
    
}
