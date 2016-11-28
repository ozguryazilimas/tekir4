/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency.exchange;

import com.ozguryazilim.tekir.entities.ExchangeRate;
import com.ozguryazilim.telve.dashboard.AbstractDashlet;
import com.ozguryazilim.telve.dashboard.Dashlet;
import com.ozguryazilim.telve.dashboard.DashletCapability;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Günlük kurları gösteren bir dashlet.
 * 
 * @author Hakan Uygun
 */
@Dashlet(capability = {DashletCapability.canHide, DashletCapability.canEdit, DashletCapability.canMinimize, DashletCapability.canRefresh})
public class ExchangeRateDashlet extends AbstractDashlet{
    
    @Inject
    private ExchangeRateRepository repository;
    
    private Date date = new Date();
    private List<ExchangeRate> rates = new ArrayList<>();
    
    @Override
    public void load() {
        populate();
    }
    
    @Override
    public void save(){
        refresh();
    }

    @Override
    public void refresh() {
        populate();
    }
    
    private void populate() {
        rates = repository.findByDate(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRate> rates) {
        this.rates = rates;
    }
    
}
