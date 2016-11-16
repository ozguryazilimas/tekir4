/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.OrderItem;
import com.ozguryazilim.tekir.entities.OrderSummary;
import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateEffect;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditor;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditorListener;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.utils.SummaryCalculator;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormEdit;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit(feature = SalesOrderFeature.class)
public class SalesOrderHome extends VoucherFormBase<SalesOrder> implements VoucherCommodityItemEditorListener {

    @Inject
    private SalesOrderRepository repository;

    @Inject
    private VoucherCommodityItemEditor commodityItemEditor;

    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("won", "fa fa-check", false), new VoucherState("WON", VoucherStateType.CLOSE, VoucherStateEffect.POSIVITE));
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true), new VoucherState("WON", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE));
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.DRAFT);
        //config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("unlock", "fa fa-unlock", true ), VoucherState.DRAFT);
        return config;
    }

    @Override
    protected RepositoryBase<SalesOrder, ?> getRepository() {
        return repository;
    }

    @Override
    public void addItem() {
        OrderItem item = new OrderItem();
        item.setMaster(getEntity());
        commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
    }

    @Override
    public void saveItem(VoucherCommodityItemBase item) {
        getEntity().getItems().add((OrderItem) item);
        calculateSummaries();
    }

    ///////// Buradan aşağıdakiler Quote üzerinden doğrudan kopya bir üst sınıfa alınabilirler sanki.
    public com.ozguryazilim.tekir.entities.Process getProcess() {
        return getEntity().getProcess();
    }

    public void setProcess(com.ozguryazilim.tekir.entities.Process process) {
        getEntity().setProcess(process);
        getEntity().setAccount(process.getAccount());
        getEntity().setTopic(process.getTopic());
    }

    public Contact getAccount() {
        return getEntity().getAccount();
    }

    public void setAccount(Contact account) {
        getEntity().setAccount(account);
        getEntity().setProcess(null);
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
        SummaryCalculator<SalesOrder, OrderItem, OrderSummary> sc = new SummaryCalculator();
        sc.calcSummaries( this::getEntity, getEntity()::getItems, getEntity()::getSummaries, ()-> new OrderSummary(), getEntity()::setTotal);
    }

}
