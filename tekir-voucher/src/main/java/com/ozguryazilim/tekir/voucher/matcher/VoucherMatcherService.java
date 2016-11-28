/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.matcher;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ProcessStatus;
import com.ozguryazilim.tekir.entities.VoucherMatchable;
import com.ozguryazilim.tekir.entities.VoucherMatcher;
import com.ozguryazilim.tekir.entities.VoucherProcessBase;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifierLiteral;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Named
public class VoucherMatcherService implements Serializable {

    @Inject
    private VoucherMachableRepository matchableRepository;

    @Inject
    private VoucherMatcherRepository matcherRepository;

    @Inject
    private Event<MatcherStateChange> stateChangeEvent;
    
    @Inject
    private ProcessService processService;
    
    @Inject
    private CurrencyService currencyService;
    
    /**
     * Eşleme için master fiş.
     *
     * Örneğin fatura kapanış için payment bekleyecek
     *
     * @param voucher
     * @param ccy
     * @param amount
     */
    public void register(VoucherProcessBase voucher, Currency ccy, BigDecimal amount, BigDecimal localAmount) {
        
        FeaturePointer vp = FeatureUtils.getFeaturePointer(voucher);
        
        VoucherMatchable vm = findMatchableByFeature( vp );
        
        if( vm == null ){
            vm = new VoucherMatchable();
        }
        
        vm.setAccount(voucher.getAccount());
        vm.setAmount(amount);
        vm.setRemainder(amount);
        vm.setLocalAmount(localAmount);
        vm.setLocalRemainder(localAmount);
        vm.setCurrency(ccy);
        vm.setProcessNo(voucher.getProcess().getProcessNo());
        vm.setTopic(voucher.getTopic());
        vm.setType(voucher.getProcess().getType());
        vm.setFeature(FeatureUtils.getFeaturePointer(voucher));
        vm.setTxnDate(voucher.getDate());
        

        //Repository save
        matchableRepository.save(vm);
    }

    /**
     * Geriye Account için eşlenebilecek fişlerin listeni döndürür.
     *
     * @param account
     * @param featureName
     * @param processNo Seçimlik filtre
     * @return
     */
    public List<VoucherMatchable> findMatchableVouchers(Contact account, String featureName, String processNo) {

        if (Strings.isNullOrEmpty(processNo)) {
            return matchableRepository.findByAccountAndFeatureName(account, featureName);
        } else {
            return matchableRepository.findByAccountAndFeature(account, featureName, processNo);
        }

    }
    
    public VoucherMatchable findMatchableByFeature( FeaturePointer feature ) {
        return matchableRepository.findAnyByFeature(feature);
    }
    
    public List<VoucherMatcher> findMatchersForFeature( FeaturePointer feature ) {
        VoucherMatchable vm = findMatchableByFeature( feature );
        if( vm != null ){
            return matcherRepository.findByMatchable(vm);
        }
        return Collections.emptyList();
    }

    public void closeMatchable( FeaturePointer matchablePointer ){
        VoucherMatchable vm = findMatchableByFeature( matchablePointer );
        if( vm != null ){
            vm.setStatus(ProcessStatus.CLOSE);
            matchableRepository.save(vm);
        }
    }
    
    @Transactional
    public void updateMachters(FeaturePointer matchablePointer, FeaturePointer feature, Currency ccy, BigDecimal amount, BigDecimal localAmount, String processId) {
        updateMachters(matchablePointer, feature, ccy, amount, localAmount, processId, true);
    }
    
    @Transactional
    public void updateMachters(FeaturePointer matchablePointer, FeaturePointer feature, Currency ccy, BigDecimal amount, BigDecimal localAmount, String processId, boolean calcRemainder) {
        VoucherMatchable matchable = matchableRepository.findAnyByFeature(matchablePointer);
        //TODO: Burada bir exception fırlatmalı mı?
        if (matchable != null) {
            updateMachters(matchable, feature, ccy, amount, localAmount, processId, calcRemainder);
        }
    }

    @Transactional
    public void updateMachters(VoucherMatchable matchable, FeaturePointer feature, Currency ccy, BigDecimal amount, BigDecimal localAmount, String processId, boolean calcRemainder) {

        //TODO: Belge edit olarak geliyor olabilir dolayısı ile daha önce girilmiş bir matcher var mı diye bakmak lazım.
        VoucherMatcher vm = matcherRepository.findAnyByMatchableAndFeature(matchable, feature);
        if (vm == null) {
            vm = new VoucherMatcher();
            vm.setMatchable(matchable);
            vm.setFeature(feature);
        }

        Currency oldCurrency = vm.getCurrency() != null ? vm.getCurrency() : currencyService.getDefaultCurrency();
        BigDecimal oldValue = vm.getAmount() != null ? vm.getAmount() : BigDecimal.ZERO;
        BigDecimal oldLocalValue = vm.getLocalAmount() != null ? vm.getLocalAmount() : BigDecimal.ZERO;
        
        vm.setAmount(amount);
        vm.setLocalAmount(localAmount);
        vm.setCurrency(ccy);
        matcherRepository.save(vm);

        //Bakiye hesaplanırken durum durum otomatik kapatılıyor.
        if( calcRemainder ){
            //Bakiyeyi hesapla
            //eğer matcher güncelleniyor ise sadece çıkarmak değil önceki durumu da geri almak lazım
            
            //FIXME: aslında matcher'ın tarihine ihtiyacımız var.
            //Matcher dövizini Machable dövizine çeviriyoruz.
            BigDecimal a = currencyService.convert(oldCurrency, oldValue, matchable.getCurrency(), matchable.getTxnDate());
            BigDecimal b = currencyService.convert(vm.getCurrency(), vm.getAmount(), matchable.getCurrency(), matchable.getTxnDate());
            
            matchable.setRemainder(matchable.getRemainder().add(a).subtract(b));
            matchable.setLocalRemainder(matchable.getLocalRemainder().add(oldLocalValue).subtract(localAmount));

            //Eğer bakiye 0 ya da küçükse CLOSE yap. 
            //TODO: 0'dan küçükse hata vermeli mi?
            if (matchable.getLocalRemainder().compareTo(BigDecimal.ZERO) < 1) {
                matchable.setStatus(ProcessStatus.CLOSE);
            } else {
                matchable.setStatus(ProcessStatus.OPEN);
            }

            matchableRepository.save(matchable);
            
            //Burada olan değişiklik ile ilgili event fırlatılıyor
            stateChangeEvent
                .select(new FeatureQualifierLiteral(FeatureRegistery.getFeatureClass(matchable.getFeature().getFeature())))
                .fire(new MatcherStateChange(matchable));
        }
        
        
        
    }

}
