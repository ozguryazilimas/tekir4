package com.ozguryazilim.finance.dashlets;

import com.ozguryazilim.finance.account.FinanceAccountTxnSumModel;
import com.ozguryazilim.finance.account.txn.FinanceAccountTxnRepository;
import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.mutfak.kahve.annotations.UserAware;
import com.ozguryazilim.tekir.entities.AccountType;
import com.ozguryazilim.tekir.entities.VoucherMatchable;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMachableRepository;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.dashboard.AbstractDashlet;
import com.ozguryazilim.telve.dashboard.Dashlet;
import com.ozguryazilim.telve.dashboard.DashletCapability;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Dashlet(capability = {DashletCapability.canHide, DashletCapability.canEdit, DashletCapability.canMinimize, DashletCapability.canRefresh})
public class FinancialStatusDashlet extends AbstractDashlet{

	@Inject @UserAware
	private Kahve kahve;

	@Inject
	private FinanceAccountTxnRepository financeRepository;

	@Inject
	private VoucherMachableRepository matchableRepository;

	@Inject
	private Identity identity;

	private String featureName = "";

	private BigDecimal sum = BigDecimal.ZERO;

	private BigDecimal receivables = BigDecimal.ZERO;

	private BigDecimal cashBalance = BigDecimal.ZERO;

	private BigDecimal debit = BigDecimal.ZERO;


	@Override
	public void load() {
		populate();
	}

	protected void populate(){
		populateCashBalance();
		populateReceivables();
		populateDebits();
		sum = cashBalance.add(receivables).subtract(debit);
	}
	private void populateCashBalance(){

		cashBalance = BigDecimal.ZERO;

		String username = identity.getLoginName();

		List<AccountType> types = new ArrayList<AccountType>(Arrays.asList(AccountType.values()));

		List<FinanceAccountTxnSumModel> items = financeRepository.findFinanceAccounts("", username, types);

		//Kimi hesaplar borç batağına saplanmış olabilir. Bunları da düşünmek lazım.
		items.forEach((sm) -> {	
			cashBalance = cashBalance.add(sm.getLocalBalance());
		});

	}

	private void populateReceivables(){
		receivables = BigDecimal.ZERO;

		List<VoucherMatchable> items = matchableRepository.findByFeatureName("SalesInvoiceFeature");

		//Kimi hesaplar borç batağına saplanmış olabilir. Bunları da düşünmek lazım.
		items.forEach((sm) -> {
			receivables = receivables.add(sm.getLocalRemainder());			
		});

	}

	private void populateDebits(){
		debit = BigDecimal.ZERO;

		List<VoucherMatchable> items = matchableRepository.findByFeatureName("PurchaseInvoiceFeature");

		//Kimi hesaplar borç batağına saplanmış olabilir. Bunları da düşünmek lazım.
		items.forEach((sm) -> {
			debit = debit.add(sm.getLocalRemainder());			
		});

	}

	@Override
	public void refresh() {
		populate();
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getReceivables() {
		return receivables;
	}

	public void setReceivables(BigDecimal receivables) {
		this.receivables = receivables;
	}

	public BigDecimal getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(BigDecimal cashBalance) {
		this.cashBalance = cashBalance;
	}
}
