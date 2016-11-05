package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.util.Date;

/**
 * View Model Class
 *
 * @author
 */
public class QuoteViewModel implements ViewModel, Serializable {

    private Long id;
    private String code;
    private String voucherNo;
    private String info;
    private String referenceNo;
    private Date date;
    private String processId;
    private String owner;
    private VoucherState state;

    public QuoteViewModel(Long id, String code, String voucherNo, String info, String referenceNo, Date date, String processId, String owner, VoucherState state) {
        this.id = id;
        this.code = code;
        this.voucherNo = voucherNo;
        this.info = info;
        this.referenceNo = referenceNo;
        this.date = date;
        this.processId = processId;
        this.owner = owner;
        this.state = state;
    }
    
    

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public VoucherState getState() {
        return state;
    }

    public void setState(VoucherState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuoteViewModel)) {
            return false;
        }
        QuoteViewModel other = (QuoteViewModel) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
