/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Order;
import com.ozguryazilim.tekir.entities.OrderItem;
import com.ozguryazilim.tekir.entities.OrderSummary;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateEffect;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditor;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditorListener;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.tekir.voucher.utils.SummaryCalculator;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.reports.JasperReportHandler;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
public abstract class OrderHomeBase<E extends Order> extends VoucherFormBase<E> implements VoucherCommodityItemEditorListener<OrderItem>{
    
	private static Logger LOG = LoggerFactory.getLogger(OrderHomeBase.class);
	
    @Inject
    private VoucherCommodityItemEditor commodityItemEditor;

    @Inject
    private ProcessService processService;
    
    @Inject
    private VoucherMatcherService matcherService;
    
    @Inject
    private CurrencyService currencyService;
    
    @Inject
    private JasperReportHandler reportHandler;
    
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        VoucherState complete = new VoucherState("COMPLETE", VoucherStateType.CLOSE, VoucherStateEffect.POSIVITE);
        VoucherState loss = new VoucherState("LOSS", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE);
        
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("complete", "fa fa-check", false), complete);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true), loss);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.REVISE);
        config.addTranstion(VoucherState.REVISE, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
        
        config.addStateAction(VoucherState.REVISE, new VoucherPrintOutAction(this));
        config.addStateTypeAction(VoucherStateType.OPEN, new VoucherPrintOutAction(this));
        config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));
        return config;
    }
    
    @Override
    public boolean onAfterLoad() {
        //FIXME: i18n
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
        //Her hangi bir türlü Close olduğunda
        if( getEntity().getState().getType().equals(VoucherStateType.CLOSE)){
            if( getEntity().getStarter() != null ){
                matcherService.closeMatchable(getFeaturePointer());
            }
        }
    
        return super.onAfterSave();
    }
    
    @Override
    protected boolean onBeforeTrigger(VoucherStateChange e) {
        if ("publish".equals(e.getAction().getName())) {
            if (!getEntity().getAccount().getContactRoles().contains("ACCOUNT")) {
                //FIXME: i18n
                FacesMessages.error("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmelisiniz?");
                return false;
            }
        }
        return super.onBeforeTrigger(e);
    }

    
    
    @Override
    public void addItem() {
        OrderItem item = new OrderItem();
        item.setMaster(getEntity());
        commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
    }

    @Override
    public void saveItem(OrderItem item) {
        if (!getEntity().getItems().contains(item)) {
            getEntity().getItems().add(item);
        }
        calculateSummaries();
    }

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
        
        //FIXME: i18n
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
    public List<OrderSummary> getTaxes() {
        List<OrderSummary> result = new ArrayList<>();

        getEntity().getSummaries().entrySet().stream()
                .filter(e -> e.getKey().startsWith("Tax."))
                .forEach(e -> {
                    result.add(e.getValue());
                });

        return result;
    }

    @Override
    public void calculateSummaries() {
        SummaryCalculator<E, OrderItem, OrderSummary> sc = new SummaryCalculator();
        sc.calcSummaries(this::getEntity, getEntity()::getItems, getEntity()::getSummaries, () -> new OrderSummary(), getEntity()::setTotal);
    }

    @Override
    public void editItem(OrderItem item) {
        commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
    }

    @Override
    public void removeItem(OrderItem item) {
        getEntity().getItems().remove(item);
        calculateSummaries();
    }

    
    protected abstract ProcessType getProcessType();
}
