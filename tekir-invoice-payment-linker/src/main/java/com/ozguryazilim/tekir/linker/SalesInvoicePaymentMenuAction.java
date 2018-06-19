/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.linker;

import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.invoice.sales.SalesInvoiceHome;
import com.ozguryazilim.tekir.payment.sales.PaymentReceivedHome;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ozguryazilim.telve.messages.Messages;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 *
 * @author oyas
 */
@Named
@RequestScoped
public class SalesInvoicePaymentMenuAction implements Serializable {
    
    @Inject
    private PaymentReceivedHome paymentHome;

    @Inject
    private SalesInvoiceHome invoiceHome;
    
    @Inject
    private VoucherMatcherService matcherService;

    public Class<? extends ViewConfig> newPayment() {

        //Once yeni kayıt oluşturtalım
        Class<? extends ViewConfig> result = paymentHome.create();

        SalesInvoice entity = (SalesInvoice) invoiceHome.getEntity();

        //Account
        paymentHome.getEntity().setAccount(entity.getAccount());

        //Nereden üretildiğinin linkini koyalım
        FeaturePointer fp = invoiceHome.getFeaturePointer();

        paymentHome.getEntity().setStarter(fp);

        paymentHome.setMatchable(matcherService.findMatchableByFeature(fp));
        
        //Process bilgisi v.s.
        //paymentHome.getEntity().setTopic(entity.getTopic());
        //paymentHome.getEntity().setProcess(entity.getProcess());
        paymentHome.getEntity().setGroup(entity.getGroup());

        //Detayları dolduralım
        FacesMessages.info("invoice.messages.PayInvoice", Messages.getMessage("invoice.messages.PayInvoiceDetail", entity.getVoucherNo(), entity.getTopic()));
        return result;
    }
}
