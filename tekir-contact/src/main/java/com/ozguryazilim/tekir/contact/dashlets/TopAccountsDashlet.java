/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.dashlets;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.mutfak.kahve.KahveEntry;
import com.ozguryazilim.mutfak.kahve.annotations.UserAware;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.AccountTxnSumModel;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.dashboard.AbstractDashlet;
import com.ozguryazilim.telve.dashboard.Dashlet;
import com.ozguryazilim.telve.dashboard.DashletCapability;
import com.ozguryazilim.telve.utils.DateUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author oyas
 */
@Dashlet(capability = {DashletCapability.canHide, DashletCapability.canEdit, DashletCapability.canMinimize, DashletCapability.canRefresh})
public class TopAccountsDashlet extends AbstractDashlet{
    
    @Inject @UserAware
    private Kahve kahve;
    
    @Inject
    private AccountTxnRepository repository;
    
    @Inject
    private Identity identity;
    
    private List<AccountTxnSumModel> items;
    
    private String featureName = "SalesInvoiceFeature";
    private Boolean mineOnly = Boolean.FALSE;
    private Integer limit = 10;
    private String startPeriod = "1m";
    
    private PieChartModel pieChartModel;
    private BigDecimal sum = BigDecimal.ZERO;
    
    private String style="TABLE"; //CHART|TABLE
            
    
    @Override
    public void load() {
        
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
        
        populate();
        
    }
    
    @Override
    public void save(){
        kahve.put( "topAccountsDashlet.limit", limit);
        kahve.put( "topAccountsDashlet.featureName", featureName);
        kahve.put( "topAccountsDashlet.mineOnly", mineOnly);
        kahve.put( "topAccountsDashlet.startPeriod", startPeriod);
        kahve.put( "topAccountsDashlet.style", style);
        refresh();
    }

    protected void populate(){
        
        sum = BigDecimal.ZERO;
        
        String username = "";
        
        if( getMineOnly() ){
            username = identity.getLoginName();
        }

        Date d = DateUtils.getDateBeforePeriod(startPeriod, new Date());
        
        items = repository.findTopAccounts(getFeatureName(), username, d, getLimit());
        
        items.forEach((sm) -> {
            sum = sum.add(sm.getAmount());
        });
        
        items.forEach((sm) -> {
            sm.setRate( sm.getAmount().multiply( new BigDecimal(100)).divide(sum, MathContext.DECIMAL32).intValue());
        });
        
        buildChartModel();
    }
    
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
    
    @Override
    public void refresh() {
        populate();
    }

    public List<AccountTxnSumModel> getSums() {
        return items;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
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

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    
    
    
}
