/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.number.VoucherSerialService;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifierLiteral;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.qualifiers.AfterLiteral;
import com.ozguryazilim.telve.qualifiers.BeforeLiteral;
import com.ozguryazilim.telve.sequence.SequenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 * Voucher tabanlı formlar için temel kontrol sınıfı
 *
 * @author Hakan Uygun
 */
public abstract class VoucherFormBase<E extends VoucherBase> extends FormBase<E, Long> {

    @Inject
    private Identity identity;

    @Inject
    private SequenceManager sequenceManager;
    
    @Inject
    private VoucherSerialService voucherSerialService;

    @Inject
    private Event<VoucherStateChange> stateChangeEvent;

    @Inject
    private ViewNavigationHandler viewNavigationHandler;

    private VoucherStateConfig stateConfig;

    /**
     * Dialog ihtiyacı olan state'ler için dialog'un hangi action için açıldığı
     */
    private VoucherStateAction dlgStateAction;

    @PostConstruct
    public void init() {
        super.init();
        stateConfig = buildStateConfig();
    }

    /**
     * Voucher State Machine için transition configürasyonu yapılır.
     *
     * @return
     */
    protected abstract VoucherStateConfig buildStateConfig();

    @Override
    protected E getNewEntity() {
        E e = super.getNewEntity();

        e.setDate(new Date());
        //FIXME: Bunu config'den sınıf adına göre almak en temizi olur.
        String s = e.getClass().getSimpleName().substring(0, 2);
        e.setVoucherNo(voucherSerialService.getNewSerialNumber(getFeature()));
        
        e.setOwner(identity.getLoginName());

        return e;
    }

    /**
     * Mevcut durum bilgisi.
     *
     *
     * Entity.state ile de bulunabilir. Ama override edilerek hile yapılması
     * için FSM tarafından bu kullanılır.
     *
     * @return
     */
    public VoucherState getCurrentState() {
        return getEntity().getState();
    }

    /**
     * Geriye mevcut state için olası actionların listesi döner.
     *
     * FIXME: yetki kontrolleri yapılmalı.
     *
     * @return
     */
    public List<VoucherStateAction> getPermittedStateActions() {
        Map<VoucherStateAction, VoucherState> trn = stateConfig.getTransitions().get(getCurrentState());
        if (trn == null) {
            return Collections.emptyList();
        }
        if (trn.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(trn.keySet());
    }

    /**
     * Bir state'den bir başka state'e geçiş için trigger.
     *
     * FIXME: Doğru action kontrolü yapılmalı. FIXME: NPE kontrolleri yapılmalı
     * FIXME: Yetki kontrolleri yapılmalı
     *
     * @param action
     * @return
     */
    @Transactional
    public Class<? extends ViewConfig> trigger(VoucherStateAction action) {

        //Önce mevcut State'i alalım
        VoucherState fromState = getCurrentState();

        //Şimdi varılacak olan state'i bulalım
        Map<VoucherStateAction, VoucherState> trn = stateConfig.getTransitions().get(getCurrentState());
        VoucherState toState = trn.get(action);

        //Gönderilecek olan event'i hazırlayalım
        VoucherStateChange e = new VoucherStateChange(fromState, action, toState, getEntity());

        //Before event'ini gönderelim
        stateChangeEvent
                .select(new FeatureQualifierLiteral(getFeatureClass()))
                .select(new BeforeLiteral())
                .fire(e);

        //State değiştirip saklayalım
        getEntity().setState(toState);
        Class<? extends ViewConfig> result = save();

        //After event'ini gönderelim
        stateChangeEvent
                .select(new FeatureQualifierLiteral(getFeatureClass()))
                .select(new AfterLiteral())
                .fire(e);

        //Ve bitti
        return result;
    }

    @Override
    public boolean onAfterCreate() {
        //Feeder'larda StateChange sırasında kullanılabilmesi için. İlk oluştutulduğunda da bir StateChange eventi fırlatıyoruz.
        VoucherStateChange e = new VoucherStateChange(VoucherState.CREATE, VoucherStateAction.CREATE, getEntity().getState(), getEntity());

        stateChangeEvent
                .select(new FeatureQualifierLiteral(getFeatureClass()))
                .select(new AfterLiteral())
                .fire(e);

        getEntity().setOwner(identity.getLoginName());

        return super.onAfterCreate();
    }

    @Override
    public boolean onAfterLoad() {

        //TODO: AfterLoad yerine başka bir method mu yazsak? 
        if (!hasViewPermission()) {
            //FIXME: i18n
            FacesMessages.error("Kayda erişim için yetkiniz yok!");
            createNew();
            viewNavigationHandler.navigateTo(getBrowsePage());
            return false;
        }

        return super.onAfterLoad();
    }

    public FeaturePointer getFeaturePointer(){
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(getEntity().getVoucherNo());
        fp.setPrimaryKey(getEntity().getId());
        fp.setFeature(getFeature().getName());
        return fp;
    }
    
    /**
     * Action name üzerinden trigger tetikler.
     *
     * @param action
     * @return
     */
    public Class<? extends ViewConfig> trigger(String action) {
        return trigger(stateConfig.getActions().get(action));
    }

    /**
     * Dialog açmadan hemen önce dialog kapanırken hangi action2ı
     * tetikleyeceğini bir saklayalım.
     *
     * @param action
     */
    public void prepareTriggerDlg(VoucherStateAction action) {
        dlgStateAction = action;
    }

    /**
     * stateDlg Dialog tarafından çağrıldı. Dolayısı ile önceden alınmış olan
     * action tetiklenecek.
     *
     * @return
     */
    public Class<? extends ViewConfig> triggerDlgAction() {
        return trigger(dlgStateAction);
    }

    public VoucherStateAction getDlgStateAction() {
        return dlgStateAction;
    }

    public Boolean hasViewPermission() {
        return identity.isPermitted(getPermissionDomain() + ":select:" + getEntity().getOwner());
    }
    
    @Override
    public Boolean hasUpdatePermission() {
        return identity.isPermitted(getPermissionDomain() + ":update:" + getEntity().getOwner());
    }

    @Override
    public Boolean hasDeletePermission() {
        return identity.isPermitted(getPermissionDomain() + ":delete:" + getEntity().getOwner());
    }

}
