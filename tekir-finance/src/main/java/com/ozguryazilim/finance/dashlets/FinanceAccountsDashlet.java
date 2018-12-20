package com.ozguryazilim.finance.dashlets;

import com.ozguryazilim.finance.account.FinanceAccountFeature;
import com.ozguryazilim.finance.account.FinanceAccountTxnSumModel;
import com.ozguryazilim.finance.account.txn.FinanceAccountTxnRepository;
import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.mutfak.kahve.KahveEntry;
import com.ozguryazilim.mutfak.kahve.annotations.UserAware;
import com.ozguryazilim.tekir.entities.AccountType;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.dashboard.AbstractDashlet;
import com.ozguryazilim.telve.dashboard.Dashlet;
import com.ozguryazilim.telve.dashboard.DashletCapability;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.utils.DateUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author oyas
 */
@Dashlet(capability = {DashletCapability.canHide, DashletCapability.canEdit, DashletCapability.canMinimize, DashletCapability.canRefresh})
public class FinanceAccountsDashlet extends AbstractDashlet{

	@Inject @UserAware
	private Kahve kahve;

	@Inject
	private FinanceAccountTxnRepository repository;

	@Inject
	private Identity identity;

	private List<FinanceAccountTxnSumModel> items;

	private String featureName = "";
	private Boolean excludeCreditCards = Boolean.FALSE;

	private BigDecimal sum = BigDecimal.ZERO;

	private BigDecimal debit = BigDecimal.ZERO;


	@Override
	public void load() {

		KahveEntry ke = kahve.get("financeAccountsDashlet.featureName");
		if( ke != null ){
			featureName = ke.getAsString();
		}

		ke = kahve.get("financeAccountsDashlet.excludeCreditCards");
		if( ke != null ){
			excludeCreditCards = ke.getAsBoolean();
		}

		populate();

	}

	@Override
	public void save(){
		kahve.put( "financeAccountsDashlet.featureName", featureName);
		kahve.put( "financeAccountsDashlet.excludeCreditCards", excludeCreditCards);  
		refresh();
	}

	protected void populate(){

		sum = BigDecimal.ZERO;
		debit = BigDecimal.ZERO;

		String username = identity.getLoginName();
		List<AccountType> types = new ArrayList<AccountType>(Arrays.asList(AccountType.values()));
		if(getExcludeCreditCards()){
			types.remove(AccountType.CREDIT_CARD);
		}

		items = repository.findFinanceAccounts(getFeatureName(), username, types);

		items.sort(new Comparator<FinanceAccountTxnSumModel>() {
			@Override
			public int compare(FinanceAccountTxnSumModel t, FinanceAccountTxnSumModel t1) {
				return t.getLocalBalance().compareTo(t1.getLocalBalance()) * -1;
			}
		});
		
		//Kimi hesaplar borç batağına saplanmış olabilir. Bunları da düşünmek lazım.
		items.forEach((sm) -> {        	
			if(sm.getAccount().getAccountRoles().contains("MULTI_CURRENCY")){
				sm.setAccountName(sm.getAccount().getName() + "(" + sm.getCurrency() + ")");
			}

			if(sm.getLocalBalance().signum() == 1){
				sum = sum.add(sm.getLocalBalance());
			}
			else{
				debit = debit.add(sm.getLocalBalance());
			}
		});

		items.forEach((sm) -> {
			if(sm.getLocalBalance().signum() == 1){
				sm.setRate( sm.getLocalBalance().multiply( new BigDecimal(100)).divide(sum, MathContext.DECIMAL32).intValue());
			}
			else{
				sm.setRate( sm.getLocalBalance().multiply( new BigDecimal(100)).divide(debit, MathContext.DECIMAL32).intValue());
			}
		});        
	}


	@Override
	public void refresh() {
		populate();
	}

	public FeaturePointer getAccountFeaturePointer(FinanceAccount account) {
		FeaturePointer result = new FeaturePointer();
		result.setBusinessKey(account.getName());
		result.setFeature(FinanceAccountFeature.class.getSimpleName());
		result.setPrimaryKey(account.getId());
		return result;
	}

	public List<FinanceAccountTxnSumModel> getSums() {
		return items;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Boolean getExcludeCreditCards() {
		return excludeCreditCards;
	}

	public void setExcludeCreditCards(Boolean excludeCreditCards) {
		this.excludeCreditCards = excludeCreditCards;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
}
