package com.ozguryazilim.tekir.core.currency.exchange;

import com.ozguryazilim.tekir.entities.ExchangeRate;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class ExchangeRateRepository extends
        RepositoryBase<ExchangeRate, ExchangeRate>
        implements
        CriteriaSupport<ExchangeRate> {

    
    @Inject
    private ExchangeRateCache rateCache;
    
    
    public abstract List<ExchangeRate> findByDate(Date date);
    
    @Query( value = "select c from ExchangeRate c where c.date = :date and ( c.baseCurrency = :base and c.termCurrency = :term or c.baseCurrency = :term and c.termCurrency = :base )", singleResult = SingleResultType.ANY)
    public abstract ExchangeRate findAnyByDateAndBaseCurrencyAndTermCurrency(@QueryParam("date") Date date, @QueryParam("base") Currency base, @QueryParam("term") Currency term);

    public BigDecimal findRate(Date date, Currency base, Currency term) {
        Map<String, Map<String, BigDecimal>> ccyMap = rateCache.getRates().get(date);

        if (ccyMap == null) {
            populateRates(date, base, term);
            ccyMap = rateCache.getRates().get(date);
        }

        Map<String, BigDecimal> rateMap = ccyMap.get(base.getCurrencyCode());
        if (rateMap == null) {
            populateRates(date, base, term);
            rateMap = ccyMap.get(base.getCurrencyCode());
        }

        //Veri tabanında değer bulunamaz ise hala null.
        if( rateMap == null ){
            return null;
        }
        
        BigDecimal rate = rateMap.get(term.getCurrencyCode());
        if (rate == null) {
            populateRates(date, base, term);
            rate = rateMap.get(term.getCurrencyCode());
        }

        return rate;
    }

    private void populateRates(Date date, Currency base, Currency term) {
        
        Map<String, Map<String, BigDecimal>> ccyMap = rateCache.getRates().get(date);

        //Eğer gün için map yoksa ekleyelim.
        if (ccyMap == null) {
            ccyMap = new ConcurrentHashMap<>();
            rateCache.getRates().put(date, ccyMap);
        }
        
        //Veri tabanından değer alalım
        ExchangeRate xr = findAnyByDateAndBaseCurrencyAndTermCurrency(date, base, term);
        if (xr == null) {
            //Tabloda istenilen kur yok.
            return;
        }

        Map<String, BigDecimal> termRateMap = ccyMap.get(xr.getTermCurrency().getCurrencyCode());
        if( termRateMap == null ){
            //Eğer o günde istenilen base için tablo yoksa oluşturalım
            termRateMap = new ConcurrentHashMap<>();
            ccyMap.put(xr.getTermCurrency().getCurrencyCode(), termRateMap);
        }
         
        Map<String, BigDecimal> baseRateMap = ccyMap.get(xr.getBaseCurrency().getCurrencyCode());
        if( baseRateMap == null ){
            //Eğer o günde istenilen base için tablo yoksa oluşturalım
            baseRateMap = new ConcurrentHashMap<>();
            ccyMap.put(xr.getBaseCurrency().getCurrencyCode(), baseRateMap);
        }
                
        if( xr.getBuyRate().compareTo(BigDecimal.ZERO) != 0){
            termRateMap.put(xr.getBaseCurrency().getCurrencyCode(), BigDecimal.ONE.divide( xr.getBuyRate(), MathContext.DECIMAL128 ).setScale(5, RoundingMode.UP));
        } else {
            termRateMap.put(xr.getBaseCurrency().getCurrencyCode(), BigDecimal.ZERO);
        }
        
        baseRateMap.put(xr.getTermCurrency().getCurrencyCode(), xr.getSellRate());
        
    }
    
    public abstract void deleteByDate(Date date);
    	
    
}
