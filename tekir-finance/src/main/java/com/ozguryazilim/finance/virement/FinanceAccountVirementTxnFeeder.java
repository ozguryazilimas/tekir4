/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.virement;

import com.ozguryazilim.finance.account.txn.FinanceAccountTxnService;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 * Virman dekontu'nu cari hareket'e işler.
 * @author Hakan Uygun
 */
@Dependent
public class FinanceAccountVirementTxnFeeder implements Serializable{

	@Inject
	private FinanceAccountTxnService financeAccountTxnService;

	@Inject
	private CurrencyService currencyService;

	public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = FinanceAccountVirement.class) @After EntityChangeEvent event) {
        FinanceAccountVirement entity = (FinanceAccountVirement) event.getEntity();

        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        if( event.getAction() != EntityChangeAction.DELETE   ) {

			BigDecimal fromLocalAmount;
			BigDecimal toLocalAmount;
			if(entity.getFromCurrency() == currencyService.getReportCurrency()){
				fromLocalAmount = entity.getFromAmount();
			}
			else{
				fromLocalAmount = entity.getLocalAmount();
			}

			if(entity.getToCurrency() == currencyService.getReportCurrency()){
				toLocalAmount = entity.getToAmount();
			}
			else{
				toLocalAmount = entity.getLocalAmount();
			}


			financeAccountTxnService.saveFeature(voucherPointer, entity.getFromAccount(), entity.getInfo(),
					entity.getTags(), Boolean.TRUE, Boolean.TRUE, entity.getFromCurrency(), entity.getFromAmount(),
					fromLocalAmount, entity.getDate(), entity.getOwner(), null, entity.getState().toString(),
					entity.getStateReason(), null);

			financeAccountTxnService.saveFeature(voucherPointer, entity.getToAccount(), entity.getInfo(),
					entity.getTags(), Boolean.TRUE, Boolean.FALSE, entity.getToCurrency(), entity.getToAmount(),
					toLocalAmount, entity.getDate(), entity.getOwner(), null, entity.getState().toString(),
					entity.getStateReason(), null);
		} else if (event.getAction() == EntityChangeAction.DELETE) {
            financeAccountTxnService.deleteFeature(voucherPointer);
        }

    }
}
