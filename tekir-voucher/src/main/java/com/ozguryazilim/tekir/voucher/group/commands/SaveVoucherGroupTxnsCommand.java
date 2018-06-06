package com.ozguryazilim.tekir.voucher.group.commands;

import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messagebus.command.AbstractCommand;
import java.util.Date;

public class SaveVoucherGroupTxnsCommand extends AbstractCommand {

    private VoucherGroup voucherGroup;
    private FeaturePointer featurePointer;
    private String topic;
    private Date date;
    private String owner;
    private VoucherState voucherState;

    public SaveVoucherGroupTxnsCommand(VoucherGroup voucherGroup, FeaturePointer featurePointer,
        String topic, Date date, String owner, VoucherState voucherState) {

        this.voucherGroup = voucherGroup;
        this.featurePointer = featurePointer;
        this.topic = topic;
        this.date = date;
        this.owner = owner;
        this.voucherState = voucherState;
    }

    public VoucherGroup getVoucherGroup() {
        return voucherGroup;
    }

    public void setVoucherGroup(VoucherGroup voucherGroup) {
        this.voucherGroup = voucherGroup;
    }

    public FeaturePointer getFeaturePointer() {
        return featurePointer;
    }

    public void setFeaturePointer(FeaturePointer featurePointer) {
        this.featurePointer = featurePointer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public VoucherState getVoucherState() {
        return voucherState;
    }

    public void setVoucherState(VoucherState voucherState) {
        this.voucherState = voucherState;
    }
}
