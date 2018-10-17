/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.sales;

import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceHomeBase;
import com.ozguryazilim.tekir.voucher.matcher.MatcherStateChange;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit(feature = SalesInvoiceFeature.class)
public class SalesInvoiceHome extends InvoiceHomeBase<SalesInvoice> {

    private static final String tagKey = "SalesInvoice";

    @Inject
    private SalesInvoiceRepository repository;

    @Override
    protected RepositoryBase<SalesInvoice, ?> getRepository() {
        return repository;
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }

    public void listenMatcherState(@Observes @FeatureQualifier(feauture = SalesInvoiceFeature.class) MatcherStateChange event) {
        feedMatcherState(event);
    }
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
		return FeatureUtils.getFeaturePointer(contact);
    }

    public String getTagKey() {
        return tagKey;
    }

}
