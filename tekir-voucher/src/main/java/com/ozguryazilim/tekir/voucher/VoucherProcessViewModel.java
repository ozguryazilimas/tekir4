/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author oyas
 */
public class VoucherProcessViewModel extends VoucherViewModel{
    
    private static final Logger LOG = LoggerFactory.getLogger(VoucherProcessViewModel.class);
    
    private Process process;
    private Contact account;

    public VoucherProcessViewModel(Long id, Process process, Contact account, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic) {
        super(id, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        this.process = process;
        this.account = account;
    }
    
    public VoucherProcessViewModel(Long id, Long processId, String processNo, Long accountId, String accountName, Class<? extends Contact> accountType, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo, String topic) {
        super(id, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic);
        this.process = new Process();
        this.process.setId(processId);
        this.process.setProcessNo(processNo);
        
        try {
            this.account = accountType.newInstance();
            this.account.setId(accountId);
            this.account.setName(accountName);
        } catch (InstantiationException | IllegalAccessException ex) {
            LOG.error("Contact type not found.", ex);
        }
        
        
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }
    
    
}
