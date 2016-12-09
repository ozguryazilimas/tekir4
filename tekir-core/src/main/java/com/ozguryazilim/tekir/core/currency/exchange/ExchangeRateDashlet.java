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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

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
	private Map<String,BigDecimal> yesterdayRates = new HashMap<>();

    
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
        Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
	     calendar.add(Calendar.DATE, -1);
		List<ExchangeRate> yls = repository.findByDate(calendar.getTime());
	
		if (!yls.isEmpty()) {
			yesterdayRates = yls.stream().collect(HashMap<String, BigDecimal>::new, 
	                (m, c) -> m.put(c.getBaseCurrency().getCurrencyCode() + "/" + c.getTermCurrency().getCurrencyCode(), c.getBuyRate()),
	                (m, u) -> {});
		}
		
		
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
    
  //TRY bazlı kurlar
  	public List<ExchangeRate> getTryRates() {

  		List<ExchangeRate> tryRates = getRates().stream()
  				.filter(r -> 	r.getTermCurrency().getCurrencyCode().equals("TRY")).collect(Collectors.toList());		
  		return tryRates;
  	}
  	
  	//Çapraz kurlar
  	public List<ExchangeRate> getCrossRates() {
  		
  		return new ArrayList<ExchangeRate>(CollectionUtils.subtract(getRates(), getTryRates()));
  	}
    public int currencyStatus(ExchangeRate rate){
		BigDecimal yesterdayRate = yesterdayRates.get(rate.getBaseCurrency().getCurrencyCode() + "/" +
													  rate.getTermCurrency().getCurrencyCode());
		if(yesterdayRate == null){
		return 0;
		}
		
		return rate.getBuyRate().compareTo(yesterdayRate);
		
	}
    
}
