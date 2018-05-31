package com.ozguryazilim.tekir.voucher.process.reports;

import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;
import java.util.List;

public class ProcessReportModel implements Serializable {

    private String processNo;
    private String topic;
    private Contact account;
    private String type;
    private String status;
    private List<AccountTxn> detail;

    public ProcessReportModel(String processNo, String topic,
        Contact account, String type, String status) {
        this.processNo = processNo;
        this.topic = topic;
        this.account = account;
        this.type = type;
        this.status = status;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AccountTxn> getDetail() {
        return detail;
    }

    public void setDetail(List<AccountTxn> detail) {
        this.detail = detail;
    }
}
