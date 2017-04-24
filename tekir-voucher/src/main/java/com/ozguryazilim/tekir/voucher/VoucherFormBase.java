/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.number.VoucherSerialService;
import com.ozguryazilim.telve.audit.AuditLogger;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifierLiteral;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.qualifiers.AfterLiteral;
import com.ozguryazilim.telve.qualifiers.BeforeLiteral;
import com.ozguryazilim.telve.reports.JasperReportHandler;
import com.ozguryazilim.telve.sequence.SequenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Voucher tabanlı formlar için temel kontrol sınıfı
 *
 * @author Hakan Uygun
 */
public abstract class VoucherFormBase<E extends VoucherBase> extends FormBase<E, Long> {

    private static Logger LOG = LoggerFactory.getLogger(VoucherFormBase.class);

    @Inject
    private Identity identity;

    @Inject
    private SequenceManager sequenceManager;

    @Inject
    private VoucherSerialService voucherSerialService;

    @Inject
    private Event<VoucherStateChange> stateChangeEvent;

    @Inject
    private Event<VoucherOwnerChange> ownerChangeEvent;

    @Inject
    private ViewNavigationHandler viewNavigationHandler;

    @Inject
    @Any
    private Instance<VoucherRedirectHandler> redirectHandlers;

    @Inject
    private JasperReportHandler reportHandler;

    @Inject
    private AuditLogger auditLogger;

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
    public List<VoucherStateAction> getPermittedStateTransitionActions() {

        List<VoucherStateAction> result = new ArrayList<>();

        Map<VoucherStateAction, VoucherState> trn = stateConfig.getTransitions().get(getCurrentState());
        if (trn == null) {
            return Collections.emptyList();
        }
        if (trn.isEmpty()) {
            return Collections.emptyList();
        }

        trn.keySet().stream()
                .filter(act -> !act.getSilence())
                .filter((act) -> (identity.isPermitted(getPermissionDomain() + ":" + act.getPermission() + ":" + getEntity().getOwner())))
                .forEachOrdered((act) -> {
                    result.add(act);
                });

        result.sort((VoucherStateAction t, VoucherStateAction t1) -> {
            return t.getOrder().compareTo(t1.getOrder());
        });

        return result;
    }

    public List<VoucherStateAction> getPermittedStateActions() {

        List<VoucherStateAction> result = new ArrayList<>();

        List<VoucherStateAction> acts = stateConfig.getStateActions(getCurrentState());
        if (acts == null) {
            return Collections.emptyList();
        }
        if (acts.isEmpty()) {
            return Collections.emptyList();
        }

        acts.stream()
                .filter(act -> !act.getSilence())
                .filter((act) -> (identity.isPermitted(getPermissionDomain() + ":" + act.getPermission() + ":" + getEntity().getOwner())))
                .forEachOrdered((act) -> {
                    result.add(act);
                });

        result.sort((VoucherStateAction t, VoucherStateAction t1) -> {
            return t.getOrder().compareTo(t1.getOrder());
        });

        return result;
    }

    @Transactional
    public Class<? extends ViewConfig> triggerExec(String action) {
        for (VoucherStateAction act : stateConfig.getStateActions(getCurrentState())) {
            if (act.getName().equals(action)) {
                return act.execute();
            }
        }

        return null;
    }

    @Transactional
    public Class<? extends ViewConfig> triggerExec(VoucherStateAction action) {
        return triggerExec(action.getName());
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
        String fromStateReason = getEntity().getStateReason();
        String fromStateInfo = getEntity().getStateInfo();

        //Şimdi varılacak olan state'i bulalım
        Map<VoucherStateAction, VoucherState> trn = stateConfig.getTransitions().get(getCurrentState());
        if (trn == null) {
            return null;
        }
        VoucherState toState = trn.get(action);
        if (toState == null) {
            return null;
        }

        //Gönderilecek olan event'i hazırlayalım
        VoucherStateChange e = new VoucherStateChange(fromState, action, toState, getEntity());

        if (!onBeforeTrigger(e)) {
            return null;
        }

        //Before event'ini gönderelim
        stateChangeEvent
                .select(new FeatureQualifierLiteral(getFeatureClass()))
                .select(new BeforeLiteral())
                .fire(e);

        //State değiştirip saklayalım
        getEntity().setState(toState);

        //State reason yoksa eskisini silelim
        if (!action.hasDialog()) {
            getEntity().setStateReason(null);
            getEntity().setStateInfo(null);
        }

        try {
            Class<? extends ViewConfig> result = save();

            //After event'ini gönderelim
            stateChangeEvent
                    .select(new FeatureQualifierLiteral(getFeatureClass()))
                    .select(new AfterLiteral())
                    .fire(e);

            //Burada redirect edilecek bişey var mı diye kontrol edilecek.
            //BeanProvider.getContextualReferences(result, true)
            for (VoucherRedirectHandler vrh : redirectHandlers.select(new FeatureQualifierLiteral(getFeatureClass()))) {
                Class<? extends ViewConfig> r = vrh.redirect(e);
                if (r != null) {
                    return r;
                }
            }

            //Ve bitti
            return result;
        } catch (Exception ex) {
            getEntity().setState(fromState);
            if (!action.hasDialog()) {
                getEntity().setStateReason(fromStateReason);
                getEntity().setStateInfo(fromStateInfo);
            }
            throw ex;
        }
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

    public FeaturePointer getFeaturePointer() {
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
    @Transactional
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
    @Transactional
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
        //DRAFT olmayan hiç bir durumda edit yapılamaz gerisine bakmaya gerek yok.
        if (!getEntity().getState().getType().equals(VoucherStateType.DRAFT)) {
            return false;
        }

        return identity.isPermitted(getPermissionDomain() + ":update:" + getEntity().getOwner());
    }

    @Override
    public Boolean hasDeletePermission() {
        //TODO: DELETE durumu biraz karışık. DRAFT state'i siler gerisini silemez demek makul mü? Yada close olanları silemez mi demeli? Ya da bunu config'den mi almalı?
        if (!getEntity().getState().getType().equals(VoucherStateType.DRAFT)) {
            return false;
        }
        return identity.isPermitted(getPermissionDomain() + ":delete:" + getEntity().getOwner());
    }

    /**
     * Belge sahipliğini değiştirme yetkisi var mı?
     *
     * @return
     */
    public Boolean hasChangeOwnerPermission() {
        return identity.isPermitted(getPermissionDomain() + ":changeOwner:" + getEntity().getOwner());
    }

    /**
     * State action tetiklemeden hemen önce çağrılır.
     *
     * Home sınıf üzerinde iş kuralları ile ilgili ek kontroller için
     * kullanılır. Geriye false dönerse akış devam etmez.
     *
     * @param e
     * @return
     */
    protected boolean onBeforeTrigger(VoucherStateChange e) {
        return true;
    }

    /**
     * ClassPath üzerinde jasper bularak onu çalıştırır.
     *
     * printout.featureName keyi ile arama yapar. Bulamaz ise featureName.jasper
     * arar.
     *
     */
    protected void printOut() {

        String fhn = getFeature().getName();

        String jasperName = ConfigResolver.getPropertyValue("printout." + fhn, fhn);

        Map<String, Object> params = new HashMap<>();

        params.put("EID", getEntity().getId());

        decoratePrintOutParams(params);

        try {
            reportHandler.reportToPDF(jasperName, getEntity().getVoucherNo(), params);
        } catch (JRException ex) {
            LOG.error("Error on printout", ex);
        }

        auditLogger.actionLog(getEntity().getClass().getSimpleName(), getEntity().getId(), getEntity().getVoucherNo(), "ACTION", "PRINT_OUT", identity.getLoginName(), jasperName);
    }

    /**
     * PrintOut için ek parametre gönderilmek istenirse oveeride edilebilir.
     *
     * @param params
     */
    protected void decoratePrintOutParams(Map<String, Object> params) {
        //Varsayılan olarak içi boş
    }

    /**
     * Belge Sahibini değiştirir.
     *
     * @param event
     */
    public void onOwnerChange(SelectEvent event) {
        String oldOwner = getEntity().getOwner();
        String userName = (String) event.getObject();
        if (Strings.isNullOrEmpty(userName)) {
            return;
        }
        getEntity().setOwner(userName);
        save();

        VoucherOwnerChange e = new VoucherOwnerChange(oldOwner, userName, getEntity());

        ownerChangeEvent
                .select(new FeatureQualifierLiteral(getFeatureClass()))
                .select(new AfterLiteral())
                .fire(e);
    }

    public VoucherStateConfig getStateConfig() {
        return stateConfig;
    }

}
