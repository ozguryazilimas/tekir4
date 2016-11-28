/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ozguryazilim.mutfak.kahve.Kahve;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.ConversionQuery;
import javax.money.convert.ConversionQueryBuilder;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.javamoney.moneta.Money;

/**
 * Sistemde tanımlı olan currency tanımlarını sunar.
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
@Named
public class CurrencyService implements Serializable{
    
    private static final String AVAILABLE_CURRENCIES = "tekir.currency.avilable";
    private static final String USABLE_CURRENCIES = "tekir.currency.useable";
    
    private static final String DEFAULT_CURRENCY = "tekir.currency.default";
    private static final String REPORT_CURRENCY = "tekir.currency.report";
    
    private static final String DEFAULT_REPORT_CURRENCY = "TRY";
    
    private static final String EXCHANGE_CURRENCY_MAP = "tekir.currency.exchangeMap";
    private static final String DEFAULT_EXCHANGE_CURRENCY_MAP = "USD/TRY,EUR/TRY";
    
    @Inject
    private Kahve kahve;
            
    
    private List<Currency> availableCurrencies = new ArrayList<>();
    private List<Currency> usableCurrencies = new ArrayList<>();
    private Currency defaultCurrency;
    private Currency reportCurrency;
    private List<String> exchangeCurrencyMap = new ArrayList<>();
    
    /**
     * Config ve Kahve'den gereken bilgileri toplayarak bileşeni hazırlar.
     */
    @PostConstruct
    public void init(){
        //Config'de yazılı para birimleri alınıp available olarak setleniyor
        String vals = ConfigResolver.getPropertyValue(AVAILABLE_CURRENCIES);
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(vals)
                .forEach(ccy -> availableCurrencies.add(Currency.getInstance(ccy)));
        
        vals = kahve.get(USABLE_CURRENCIES,ConfigResolver.getPropertyValue(USABLE_CURRENCIES)).getAsString();
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(vals)
                .forEach(ccy -> usableCurrencies.add(Currency.getInstance(ccy)));
        
        defaultCurrency = Currency.getInstance(kahve.get(DEFAULT_CURRENCY, ConfigResolver.getPropertyValue(DEFAULT_CURRENCY)).getAsString());
        reportCurrency = Currency.getInstance(ConfigResolver.getPropertyValue(REPORT_CURRENCY,DEFAULT_REPORT_CURRENCY));
        
        vals = kahve.get(EXCHANGE_CURRENCY_MAP,ConfigResolver.getPropertyValue(EXCHANGE_CURRENCY_MAP, DEFAULT_EXCHANGE_CURRENCY_MAP)).getAsString();
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(vals)
                .forEach(ccy -> exchangeCurrencyMap.add(ccy));
        
    }
    
    /**
     * Sistem de tanımlı para birimlerinin listesini döndürür.
     * Bu birimler tekir.currencies property'alanında tanımlanırlar
     * 
     * @return 
     */
    public List<Currency> getAvailableCurrencies(){
        return availableCurrencies;
    }

    /**
     * Uygulama içinde kullanılacak olan varsayılan para birimni
     * 
     * @return 
     */
    public Currency getDefaultCurrency(){
        return defaultCurrency;
    }

    /**
     * Raporlama için sistem'de kullanılacak olan döviz türü.
     * 
     * Bu kurulum ayarlarının bir parçasıdır. Değiştiğinde fişler üzerinde tutulan hesaplamalarında değişmesi gerekir.
     * 
     * @return 
     */
    public Currency getReportCurrency() {
        return reportCurrency;
    }

    /**
     * Geriye kur girişi için hangi döviz çiftlerinin kullanılabileceği listesini döndürür.
     * 
     * Örneğin : USD/TRY, EUR/TRY, EUR/USD gibi.
     * 
     * @return 
     */
    public List<String> getExchangeCurrencyMap() {
        return exchangeCurrencyMap;
    }
    
    /**
     * Verilen currency'i default olarak setler
     * @param ccy 
     */
    public void setDefaultCurrency(Currency ccy ){
        defaultCurrency = ccy;
        kahve.put(DEFAULT_CURRENCY, defaultCurrency.getCurrencyCode());
    }
    
    /**
     * Geriye sistemde kullanılabilecek olan para birimi listesini döndürür.
     * @return 
     */
    public List<Currency> getCurrencies(){
        return usableCurrencies;
    }
    
    
    /**
     * Verilen listeyi kullanılabilir pra birimleri olrak setler
     * 
     * @param currencies 
     */
    public void setCurrencies( List<Currency> currencies ){
        
        List<String> ls = new ArrayList();
        currencies.forEach(ccy -> ls.add(ccy.getCurrencyCode()));
        
        StringBuilder sb = new StringBuilder();
        Joiner.on(',').appendTo(sb, ls);
        
        kahve.put(USABLE_CURRENCIES, sb.toString());
        
        usableCurrencies.clear();
        usableCurrencies.addAll(currencies);
        
    }


    /**
     * Verilen değeri güncel kur ile rapor dövizine çevirir.
     * 
     * @param fromCurrency
     * @param amount
     * @return 
     */
    public BigDecimal convert( String fromCurrency, BigDecimal amount ){
        return convert(fromCurrency, amount, reportCurrency.getCurrencyCode(), new Date());
    }
    
    /**
     * Verilen değeri isetenilen döviz türüne çevirir
     * @param fromCurrency
     * @param amount
     * @param toCurrency
     * @return 
     */
    public BigDecimal convert( String fromCurrency, BigDecimal amount, String toCurrency ){
        return convert(fromCurrency, amount, toCurrency, new Date());
    }
    
    
    /**
     * Veirlen değeri istenilen döviz türüne verilen tarih ile çevirir
     * @param fromCurrency
     * @param amount
     * @param toCurrency
     * @param date
     * @return 
     */
    public BigDecimal convert( String fromCurrency, BigDecimal amount, String toCurrency, Date date ){
        CurrencyUnit c = Monetary.getCurrency(toCurrency);
        MonetaryAmount a = Money.of( amount, fromCurrency);
        LocalDate ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        MonetaryAmount r = convert(a, c, ldt);
        return r.getNumber().numberValue(BigDecimal.class);
    }
    
    /**
     * Asıl çevrim işe JSR-354 API kullanılarak yapılıyor.
     * @param m
     * @param toCurrency
     * @param date
     * @return 
     */
    public MonetaryAmount convert( MonetaryAmount m, CurrencyUnit toCurrency, LocalDate date ){
        
        ConversionQuery q = ConversionQueryBuilder.of().setProviderName("TKR").setTermCurrency(toCurrency).set(date).build();
        CurrencyConversion cc = MonetaryConversions.getConversion(q);
        MonetaryAmount a = cc.apply(m);
        
        return a;
    }
    
    
    public void convert(){
        CurrencyUnit c = Monetary.getCurrency("TRY");
        ConversionQuery q = ConversionQueryBuilder.of().setProviderName("TKR").setTermCurrency(c).set(LocalDate.now()).build();
        CurrencyConversion cc = MonetaryConversions.getConversion(q);
        MonetaryAmount a = cc.apply(Money.of(10, "EUR"));
        
        System.err.println(a);
        
        
        c = Monetary.getCurrency("TRY");
        q = ConversionQueryBuilder.of().setProviderName("TKR").setTermCurrency(c).build();
        cc = MonetaryConversions.getConversion(q);
        a = cc.apply(Money.of(10, "EUR"));
        
        System.err.println(a);
        
        
        c = Monetary.getCurrency("EUR");
        q = ConversionQueryBuilder.of().setProviderName("TKR").setTermCurrency(c).build();
        cc = MonetaryConversions.getConversion(q);
        a = cc.apply(Money.of(100, "TRY"));
        
        System.err.println(a);
        
        c = Monetary.getCurrency("TRY");
        q = ConversionQueryBuilder.of().setProviderName("TKR").setTermCurrency(c).build();
        cc = MonetaryConversions.getConversion(q);
        a = cc.apply(Money.of(100, "TRY"));
        
        System.err.println(a);
        
        /*
        c = Monetary.getCurrency("TRY");
        q = ConversionQueryBuilder.of().setProviderName("TKR").setTermCurrency(c).build();
        cc = MonetaryConversions.getConversion(q);
        a = cc.apply(Money.of(10, "USD"));
        
        System.err.println(a);
        */


        BigDecimal b = convert("TRY", BigDecimal.TEN, "EUR");
        System.err.println(b);
        
        b = convert("EUR", BigDecimal.TEN);
        System.err.println(b);
        
        //MonetaryConversions.getConversion(ConversionQueryBuilder.of().setRateTypes(RateTypes.))DEFAULT_CURRENCY, "IMF").getExchangeRate(null).
    }
}
