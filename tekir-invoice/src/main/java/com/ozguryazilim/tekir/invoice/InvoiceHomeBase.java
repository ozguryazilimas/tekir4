/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Invoice;
import com.ozguryazilim.tekir.entities.InvoiceItem;
import com.ozguryazilim.tekir.entities.InvoiceSummary;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.ProcessStatus;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateEffect;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditor;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditorListener;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.matcher.MatcherStateChange;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.tekir.voucher.utils.SummaryCalculator;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
public abstract class InvoiceHomeBase<E extends Invoice> extends VoucherFormBase<E> implements VoucherCommodityItemEditorListener<InvoiceItem> {
    
    @Inject
    private VoucherCommodityItemEditor commodityItemEditor;

    @Inject
    private ProcessService processService;
    
    @Inject
    private VoucherMatcherService matcherService;
    
    @Inject
    private CurrencyService currencyService;
    
    
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        
        VoucherState paid = new VoucherState("PAID", VoucherStateType.CLOSE, VoucherStateEffect.POSIVITE);
        VoucherState partialPaid = new VoucherState("PARTIAL", VoucherStateType.OPEN, VoucherStateEffect.NEUTRAL);
        
        VoucherStateAction paidAction = new VoucherStateAction("paid");
        VoucherStateAction partialPaidAction = new VoucherStateAction("partial");
        
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true), new VoucherState("UNPAID", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE));
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, partialPaidAction, partialPaid);
        config.addTranstion(VoucherState.OPEN, paidAction, paid);
        config.addTranstion( partialPaid, paidAction, paid);
        config.addTranstion( partialPaid, new VoucherStateAction("loss", "fa fa-close", true), new VoucherState("PARTUNPAID", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE));
        config.addTranstion( partialPaid, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.REVISE);
        config.addTranstion(VoucherState.REVISE, new VoucherStateAction("publish", "fa fa-check", true), VoucherState.OPEN);
        //config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("unlock", "fa fa-unlock", true ), VoucherState.DRAFT);
        return config;
    }
    
    @Override
    public boolean onAfterLoad() {
        if (!getEntity().getAccount().getContactRoles().contains("ACCOUNT")) {
            FacesMessages.error("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmelisiniz?");
        }
        
        if( getProcessType() == ProcessType.SALES ){
            if (!getEntity().getAccount().getContactRoles().contains("CUSTOMER")) {
                FacesMessages.warn("Seçtiğiniz bağlantı bir Müşteri değil.", "Bağlantıyı müşteri olarak işaretlemek ister misiniz?");
            }
        } else {
            if (!getEntity().getAccount().getContactRoles().contains("VENDOR")) {
                FacesMessages.warn("Seçtiğiniz bağlantı bir tedatikçi değil.", "Bağlantıyı tedarikçi olarak işaretlemek ister misiniz?");
            }
        }
        return super.onAfterLoad(); 
    }
    
    @Override
    public boolean onBeforeSave() {
        if (getEntity().getProcess() == null) {
            getEntity().setProcess(processService.createProcess(getEntity().getAccount(), getEntity().getTopic(), getProcessType()));
        }

        getEntity().setLocalAmount(currencyService.convert(getEntity().getCurrency(), getEntity().getTotal(), getEntity().getDate()));
        
        return super.onBeforeSave();
    }
    
    @Override
    public boolean onAfterSave() {
        //Her hangi bir türlü Publish/Open olduğunda : Open, PartialPaid 
        if( getEntity().getState().getType().equals(VoucherStateType.OPEN)){
            if( getEntity().getStarter() != null ){
                matcherService.updateMachters(getEntity().getStarter(), getFeaturePointer(), getEntity().getCurrency(), getEntity().getTotal(), getEntity().getLocalAmount(), getEntity().getProcess().getProcessNo(), false);
            }
        }
        return super.onAfterSave(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected boolean onBeforeTrigger(VoucherStateChange e) {
        if ("publish".equals(e.getAction().getName())) {
            if (!getEntity().getAccount().getContactRoles().contains("ACCOUNT")) {
                FacesMessages.error("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmelisiniz?");
                return false;
            }
        }
        return super.onBeforeTrigger(e);
    }
    
    
    @Override
    public void addItem() {
        InvoiceItem item = new InvoiceItem();
        item.setMaster(getEntity());
        commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
    }

    @Override
    public void saveItem(InvoiceItem item) {
        if (!getEntity().getItems().contains(item)) {
            getEntity().getItems().add(item);
        }
        calculateSummaries();
    }

    ///////// Buradan aşağıdakiler Quote üzerinden doğrudan kopya bir üst sınıfa alınabilirler sanki.
    public com.ozguryazilim.tekir.entities.Process getProcess() {
        return getEntity().getProcess();
    }

    public void setProcess(com.ozguryazilim.tekir.entities.Process process) {
        getEntity().setProcess(process);
        if (process != null) {
            getEntity().setAccount(process.getAccount());
            getEntity().setTopic(process.getTopic());
        }
    }

    public Contact getAccount() {
        return getEntity().getAccount();
    }

    public void setAccount(Contact account) {
        getEntity().setAccount(account);
        getEntity().setProcess(null);
        if (!account.getContactRoles().contains("ACCOUNT")) {
            FacesMessages.error("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmelisiniz?");
        }
        if( getProcessType() == ProcessType.SALES ){
            if (!getEntity().getAccount().getContactRoles().contains("CUSTOMER")) {
                FacesMessages.warn("Seçtiğiniz bağlantı bir Müşteri değil.", "Bağlantıyı müşteri olarak işaretlemek ister misiniz?");
            }
        } else {
            if (!getEntity().getAccount().getContactRoles().contains("VENDOR")) {
                FacesMessages.warn("Seçtiğiniz bağlantı bir tedatikçi değil.", "Bağlantıyı tedarikçi olarak işaretlemek ister misiniz?");
            }
        }
    }

    public Person getPerson() {
        if (getAccount() instanceof Person) {
            return (Person) getAccount();
        } else {
            return ((Corporation) getAccount()).getPrimaryContact();
        }
    }

    public Corporation getCorporation() {
        if (getAccount() instanceof Corporation) {
            return (Corporation) getAccount();
        } else {
            return ((Person) getAccount()).getCorporation();
        }
    }

    @Override
    public List<InvoiceSummary> getTaxes() {
        List<InvoiceSummary> result = new ArrayList<>();

        getEntity().getSummaries().entrySet().stream()
                .filter(e -> e.getKey().startsWith("Tax."))
                .forEach(e -> {
                    result.add(e.getValue());
                });

        return result;
    }

    @Override
    public void calculateSummaries() {
        SummaryCalculator<E, InvoiceItem, InvoiceSummary> sc = new SummaryCalculator();
        sc.calcSummaries(this::getEntity, getEntity()::getItems, getEntity()::getSummaries, () -> new InvoiceSummary(), getEntity()::setTotal);
    }

    @Override
    public void editItem(InvoiceItem item) {
        commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
    }

    @Override
    public void removeItem(InvoiceItem item) {
        getEntity().getItems().remove(item);
        calculateSummaries();
    }
    
    
    protected abstract ProcessType getProcessType();
    
    
    protected void feedMatcherState(MatcherStateChange event) {
        //TODO: Burada nasıl bir şey yapmalı? Home sınıfı sadece state değiştirmek için çok ağır
        // ama bir yandan da onun üzerinde bir dolu kontrol işlemi var.
        // üstelikte FSM onun üzerinde. Ama ekranda açık olan bir form varsa da onu bozarız.
        
        //Önce doğru entityi bir bulalım ( Burada yetkiler araya girer mi acaba?
        setId(event.getMatchable().getFeature().getPrimaryKey());
        
        if( event.getMatchable().getStatus() == ProcessStatus.CLOSE ){
            if( !"PAID".equals(getEntity().getState().getName())){
                trigger("paid");
            }
            //Süreç varsa ( ki otomatik var ) Order/Contract yoksa süreç kapanmalı
            //Order/Contract varsa onların kapanma durumunu kontrol edip kapanmalı
        } else {
            if( event.getMatchable().getRemainder().compareTo(event.getMatchable().getAmount()) == 0  ){
                //Bu durum aslında payment'ın silinmesi anlamına geliyor.
                //FIXME: Süreçte böyle bişi yok ?! paid ya da partial paid olan bişiyi geri nasıl alacağız?
                //trigger("paid");
            } else {
                if( !"PARTIAL".equals(getEntity().getState().getName())){
                    trigger("partial");
                }
            }
        }
    }
}
