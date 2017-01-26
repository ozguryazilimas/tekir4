/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.opportunity.dashlets;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.mutfak.kahve.KahveEntry;
import com.ozguryazilim.mutfak.kahve.annotations.UserAware;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.AccountTxnSumModel;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.opportunity.OpportunityRepository;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.dashboard.AbstractDashlet;
import com.ozguryazilim.telve.dashboard.Dashlet;
import com.ozguryazilim.telve.dashboard.DashletCapability;
import com.ozguryazilim.telve.utils.DateUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author oyas
 */
@Dashlet(capability = {DashletCapability.canHide, DashletCapability.canEdit, DashletCapability.canMinimize, DashletCapability.canRefresh})
public class TopOpportunitiesDashlet extends AbstractDashlet{
    
    @Inject @UserAware
    private Kahve kahve;
    
    @Inject
    private OpportunityRepository repository;
    
    @Inject
    private Identity identity;
    
    private List<TopOpportunityModel> items;
    
    private Boolean mineOnly = Boolean.FALSE;
    private Integer limit = 10;
    private String startPeriod = "1m";             
    
    @Override
    public void load() {
        
        KahveEntry ke = kahve.get("topOpportunitiesDashlet.limit");
        if( ke != null ){
            limit = ke.getAsInteger();
        }

        ke = kahve.get("topOpportunitiesDashlet.mineOnly");
        if( ke != null ){
            mineOnly = ke.getAsBoolean();
        }

        
        ke = kahve.get("topOpportunitiesDashlet.startPeriod");
        if( ke != null ){
            startPeriod = ke.getAsString();
        }
                
        populate();
        
    }
    
    @Override
    public void save(){
        kahve.put( "topOpportunitiesDashlet.limit", limit);
        kahve.put( "topOpportunitiesDashlet.mineOnly", mineOnly);
        kahve.put( "topOpportunitiesDashlet.startPeriod", startPeriod);
        refresh();
    }

    protected void populate(){       
        String username = "";
        
        if( getMineOnly() ){
            username = identity.getLoginName();
        }

        Date d = DateUtils.getDateBeforePeriod(startPeriod, new Date());
        
        items = repository.findTopOpportunities( username, d, getLimit());
  
    }

    @Override
    public void refresh() {
        populate();
    }

    public List<TopOpportunityModel> getSums() {
        return items;
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

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }   
}
