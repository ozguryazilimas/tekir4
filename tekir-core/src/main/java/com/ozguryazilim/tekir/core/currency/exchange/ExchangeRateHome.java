/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency.exchange;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.ExchangeRate;
import com.ozguryazilim.telve.messages.FacesMessages;

import freemarker.template.utility.DateUtil;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.dom4j.DocumentException;
import org.jboss.util.collection.CollectionsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exchange Rate UI controller sınıfı.
 *
 * @author Hakan Uygun
 */
@Named
@GroupedConversationScoped
public class ExchangeRateHome implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateHome.class);

	@Inject
	private CurrencyService currencyService;

	@Inject
	private TCMBRateParser tcmbRateParser;

	@Inject
	private ExchangeRateRepository repository;

	/**
	 * İşlem yapılacak olan tarih
	 */
	private Date date = new Date();

	/**
	 * Değerler
	 */
	private List<ExchangeRate> rates = new ArrayList<>();
	
	private Map<String,BigDecimal> yesterdayRates = new HashMap<>();

	@PostConstruct
	public void init() {
		populateRates();
	}

	/**
	 * Seçilmiş olan tarih için veri tabanını tarar varsa bilgileri toparlar
	 * yoksa, 0 değerler ile kayıt listesi oluşturur.
	 *
	 */
	public void populateRates() {

		rates.clear();

		List<ExchangeRate> ls = repository.findByDate(date);
		if (!ls.isEmpty()) {
			rates.addAll(ls);
		}
		
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
	     calendar.add(Calendar.DATE, -1);
		List<ExchangeRate> yls = repository.findByDate(calendar.getTime());
	
		if (!yls.isEmpty()) {
			yesterdayRates = yls.stream().collect(HashMap<String, BigDecimal>::new, 
	                (m, c) -> m.put(c.getBaseCurrency().getCurrencyCode() + "/" + c.getTermCurrency().getCurrencyCode(), c.getBuyRate()),
	                (m, u) -> {});
		}
		
		

		// Veri tababanından aldığımız değerler içerisinde eşlerin hepsi yoksa
		// ekliyoruz.
		currencyService.getExchangeCurrencyMap().stream().forEach(cm -> {

			Boolean b = rates.stream().anyMatch(x -> {
				String s = x.getBaseCurrency().getCurrencyCode() + "/" + x.getTermCurrency().getCurrencyCode();
				return s.equals(cm);
			});

			if (!b) {
				String[] cms = cm.split("/");
				ExchangeRate xr = new ExchangeRate();
				xr.setDate(date);
				xr.setBaseCurrency(Currency.getInstance(cms[0]));
				xr.setTermCurrency(Currency.getInstance(cms[1]));
				xr.setBuyRate(BigDecimal.ZERO);
				xr.setSellRate(BigDecimal.ZERO);
				rates.add(xr);
			}

		});

	}

	@Transactional
	public void save() {
		rates.stream().forEach(xcr -> repository.save(xcr));
		// TODO: Rate Cache temizlenmeli.
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		
		//Aynı gün değilse set etmesin ki save butonu düzgün çalışabilsin.
		if(!DateUtils.isSameDay(this.date, date)){
			// TODO: önce değerleri bir saklasak mı?			
			save();
		this.date = date;
		// Tarih değişti. Değerler değişti.
		populateRates();
		}
	}
	//Tüm kurlar
	public List<ExchangeRate> getRates() {
		if (rates.isEmpty()) {
			populateRates();
		}
		return rates;
	}
	//TRY bazlı kurlar
	public List<ExchangeRate> getTryRates() {

		List<ExchangeRate> tryRates = getRates().stream()
				.filter(r -> 	r.getTermCurrency().getCurrencyCode().equals("TRY")).collect(Collectors.toList());		
		return tryRates;
	}
	
	//Çapraz kurlar
	public List<ExchangeRate> getCrossRates() {
		
		return new ArrayList(CollectionUtils.subtract(getRates(), getTryRates()));
	}

	public void setRates(List<ExchangeRate> rates) {
		this.rates = rates;
	}

	/**
	 * TCMB'den kurları alır ve veritabanına tarih değerleri ile birlikte yazar.
	 */
	@Transactional
	public void getTCMBRates() {
		try {
			List<ExchangeRate> resultList = tcmbRateParser.getExchangeRatesByDate(date);
			repository.deleteByDate(date);
			for (ExchangeRate er : resultList) {
				repository.save(er);
			}
		} catch (IOException | DocumentException e) {

			LOG.error("Hata", e);		
			FacesMessages.error("exchangeRate.message.Error");
		}
		//Yeni gelen değerler ile tekrar doldursun.
		populateRates();

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
