/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency.exchange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Date;
import java.util.Objects;
import javax.money.convert.ConversionQuery;
import javax.money.convert.ExchangeRate;
import javax.money.convert.ProviderContext;
import javax.money.convert.ProviderContextBuilder;
import javax.money.convert.RateType;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.javamoney.moneta.convert.ExchangeRateBuilder;
import org.javamoney.moneta.spi.AbstractRateProvider;
import org.javamoney.moneta.spi.DefaultNumberValue;

/**
 * Moneytary için Rate provider.
 * 
 * 
 * Verileri Tekir Veri tabanından çeker ve cacheler.
 * 
 * @author Hakan Uygun
 */
public class TekirRateProvider extends AbstractRateProvider {

    private static final ProviderContext CONTEXT =
            ProviderContextBuilder.of("TKR", RateType.HISTORIC, RateType.DEFERRED)
                    .set("providerDescription", "Tekir").set("days", 1500).build();


    
    public TekirRateProvider() {
        super(CONTEXT);
    }

    @Override
    public ExchangeRate getExchangeRate(ConversionQuery conversionQuery) {

        Objects.requireNonNull(conversionQuery);
        
        BigDecimal result = findExchangeRate(conversionQuery);

        if( result == null || result.compareTo(BigDecimal.ZERO) == 0){
            return null;
        }
        
        ExchangeRateBuilder builder = getBuilder(conversionQuery);

        return builder.setFactor(DefaultNumberValue.of(result)).build();

    }

    private ExchangeRateRepository getRepository(){
        return BeanProvider.getContextualReference(ExchangeRateRepository.class, true);
    }
    
    private ExchangeRateBuilder getBuilder(ConversionQuery query) {
        ExchangeRateBuilder builder = new ExchangeRateBuilder(getExchangeContext("tkr.digit.fraction"));
        builder.setBase(query.getBaseCurrency());
        builder.setTerm(query.getCurrency());

        return builder;
}

    private BigDecimal findExchangeRate(ConversionQuery conversionQuery) {
        LocalDate[] dates = getQueryDates(conversionQuery);
        
        LocalDate dt;
        if( dates == null ){
            dt = LocalDate.now();
        } else {
            dt = dates[0];
        }
        
        Date date = Date.from(dt.atStartOfDay(ZoneId.systemDefault()).toInstant());
        BigDecimal rate = getRepository().findRate(date, Currency.getInstance(conversionQuery.getBaseCurrency().getCurrencyCode()), Currency.getInstance(conversionQuery.getCurrency().getCurrencyCode()));
        
        return rate;
    }
}
