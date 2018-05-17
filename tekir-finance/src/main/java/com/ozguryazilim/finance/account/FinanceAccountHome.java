/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.google.common.base.Strings;
import com.ozguryazilim.finance.account.txn.FinanceAccountTxnRepository;
import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.AccountType;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.messages.Messages;
import com.ozguryazilim.telve.utils.DateUtils;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author oyas
 */
@FormEdit(feature = FinanceAccountFeature.class)
@AutoCode(caption = "module.caption.FinanceAccount", size = 3)
public class FinanceAccountHome extends FormBase<FinanceAccount, Long> {

    @Inject 
    private AutoCodeService codeService;

    @Inject
    private FinanceAccountRepository repository;

    @Inject
    private FinanceAccountTxnRepository txnRepository;

    @Inject
    private Identity identity;

    @Inject
    private CurrencyService currencyService;

    @Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    @Inject
    private NavigationParameterContext navigationParameterContext;


    private List<String> selectedRoles = new ArrayList<>();

    private List<FinanceAccountTxn> accountTxns;
    
    private List<FinanceAccountTxnSumModel> currencyBalances;
    
    private List<FinanceAccountBalanceModel> balanceModels;

    private LineChartModel chartModel;
    private BigDecimal balance;
    private BigDecimal takeOverTotal;

    @Override
    public FinanceAccountRepository getRepository() {
        return this.repository;
    }

    private void newAccount(AccountType type) {
	FinanceAccount p = new FinanceAccount();
	p.getAccountRoles().add(type.name());
	p.setType(type);
	p.setOwner(identity.getLoginName());
	p.setCode(codeService.getNewSerialNumber(FinanceAccountHome.class.getSimpleName()));
	setEntity(p);
	selectedRoles.clear();
	navigationParameterContext.addPageParameter("eid", 0);
    }

    public Class<? extends ViewConfig> newCashAccount() {
	newAccount(AccountType.CASH);
	return FinancePages.FinanceAccount.class;
    }

    public Class<? extends ViewConfig> newBankAccount() {
	newAccount(AccountType.BANK);
	return FinancePages.FinanceAccount.class;
    }

    public Class<? extends ViewConfig> newCreditCardAccount() {
	newAccount(AccountType.CREDIT_CARD);
	return FinancePages.FinanceAccount.class;
    }

    @Override
    public boolean onBeforeSave() {
        //Eğer person ise name alanını düzeltmek lazım
        if (getEntity().getType() == AccountType.BANK) {
            getEntity().setName(getEntity().getBank() + " " + getEntity().getBranch() + " " + getEntity().getAccountNo());
        }

        //Önce kullanıcı seçimli olmayan rolleri bir toparlayalım
        List<String> ls = getEntity().getAccountRoles().stream()
                .filter(p -> !getAccountRoles().contains(p))
                .collect(Collectors.toList());

        //Şimdi kullanıcın seçtiklerini ekleyelim
        ls.addAll(selectedRoles);

        //Şimdi de yeni durumu yerleştirelim.
        getEntity().getAccountRoles().clear();
        getEntity().getAccountRoles().addAll(ls);

        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onAfterSave() {
        //Burda refresh atiyoruzki kayit olustuktan sonra olusan ilk chart dogru degerleri alsin
        refreshTxns();

        return super.onAfterSave();
    }

    @Override
    public boolean onAfterLoad() {

        //FIXME: Burayı generic bir hale getirmek lazım                
        if (!identity.isPermitted("financeAccount:select:" + getEntity().getOwner())) {
            FacesMessages.error("facesMessages.error.NoPermission");
            createNew();
            viewNavigationHandler.navigateTo(FinancePages.FinanceAccountBrowse.class);
            return false;
        }

        selectedRoles = getEntity().getAccountRoles().stream()
                .filter(p -> getAccountRoles().contains(p))
                .collect(Collectors.toList());

        balanceModels = null;
        chartModel = null;
        balance = null;
        takeOverTotal = null;
        setCurrencyBalances(null);
        
        return super.onAfterLoad();
    }
    
    /**
     * Belge sahipliğini değiştirme yetkisi var mı?
     * @return 
     */
    public Boolean hasChangeOwnerPermission() {
        return identity.isPermitted(getPermissionDomain() + ":changeOwner:" + getEntity().getOwner());
    }
    
    /**
     * Belge Sahibini değiştirir.
     * @param event 
     */
    public void onOwnerChange(SelectEvent event) {
        String userName = (String) event.getObject();
        if( Strings.isNullOrEmpty(userName)) return;
        getEntity().setOwner(userName);
        save();
   }

    public List<String> getAccountRoles() {
        return AccountRoleRegitery.getSelectableAccountRoles();
    }

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    @Override
    public Class<? extends FeatureHandler> getFeatureClass() {
        return FinanceAccountFeature.class;
    }

    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getName());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }

    public List<FinanceAccountBalanceModel> getBalanceModels() {
        if( balanceModels == null ){
            refreshTxns();
        }
        return balanceModels;
    }

    public BigDecimal getBalance() {
        if (balance == null) {
            balance = txnRepository.findByAccountBalance(getEntity(), new Date());
            if (balance == null) {
                balance = BigDecimal.ZERO;
            }
        }
        return balance;
    }   

	public List<FinanceAccountTxnSumModel> getCurrencyBalances() {
		  if (currencyBalances == null) {
	            refreshTxns();
	        }	  
		return currencyBalances;
	}

	public void setCurrencyBalances(List<FinanceAccountTxnSumModel> currencyBalances) {
		this.currencyBalances = currencyBalances;
	}

    
    public List<FinanceAccountTxn> getAccountTxns() {
        if (accountTxns == null) {
            refreshTxns();
        }
        return accountTxns;
    }

    public LineChartModel getChartModel() {
        if (chartModel == null) {
            refreshTxns();
        }
        return chartModel;
    }

    protected void refreshTxns() {
        //FIXME: Buradaki period UI'dan alınmalı.
        Date dt = DateUtils.getDateBeforePeriod("10d", new Date());
        accountTxns = txnRepository.findByAccountAndDateGreaterThanEqualsOrderByDateAsc(getEntity(), dt);
        takeOverTotal = txnRepository.findByAccountBalance(getEntity(), dt);
        
        if (takeOverTotal == null) {
            takeOverTotal = BigDecimal.ZERO;
        }
        
        if(isCurrencyTableRenderable()){
        buildCurrencyBalanceMap();
        }
        
        buildBalanceModel( dt );
        buildChartModel( dt );
    }

    protected void buildChartModel( Date startDate) {
        chartModel = new LineChartModel();

        LineChartSeries debitSeries = new LineChartSeries();
        debitSeries.setLabel(Messages.getMessage("finance.label.Debit"));
        debitSeries.setShowLine(false);
        //debitSeries.setDisableStack(true);

        LineChartSeries creditSeries = new LineChartSeries();
        creditSeries.setLabel(Messages.getMessage("finance.label.Credit"));
        creditSeries.setShowLine(false);
        //creditSeries.setDisableStack(true);

        LineChartSeries stateSeries = new LineChartSeries();
        stateSeries.setLabel(Messages.getMessage("general.label.Balance"));
        stateSeries.setFill(true);
        stateSeries.setDisableStack(true);
        stateSeries.setFillAlpha(0.2);

        String pattern = Messages.getMessage("general.format.Date");
        DateFormat df = new SimpleDateFormat(pattern);
        
        for( FinanceAccountBalanceModel bm : balanceModels ){
            String dts = df.format(bm.getDate());
            stateSeries.set(dts, bm.getBalance());
            if( "TXN".equals(bm.getLineType())){
                if( bm.getAmount().compareTo(BigDecimal.ZERO) < 1 ){
                    BigDecimal bd = (BigDecimal) debitSeries.getData().get(dts);
                    if( bd != null ){
                        debitSeries.set(dts, bm.getAmount().add(bd));
                    } else {
                        debitSeries.set(dts, bm.getAmount());
                    }
                } else {
                    BigDecimal bd = (BigDecimal) creditSeries.getData().get(dts);
                    if( bd != null ){
                        creditSeries.set(dts, bm.getAmount().add(bd));
                    } else {
                        creditSeries.set(dts, bm.getAmount());
                    }
                }
            } 
        }
        
        
        chartModel.addSeries(stateSeries);
        chartModel.addSeries(creditSeries);
        chartModel.addSeries(debitSeries);
        
        //TODO: Bu renk işini temaya almak lazım
        //lineChartModel.setSeriesColors("3c8dbc,f56954,dd4b39,dd4b39");
        chartModel.setExtender("chartExt");
        
        
        
        //chartModel.setTitle("States");
        chartModel.setLegendPosition("nw");
        chartModel.setAnimate(true);
        chartModel.setShadow(false);
        //chartModel.setStacked(true);

        //Axis xAxis = new CategoryAxis();
        //lineChartModel.getAxes().put(AxisType.X, xAxis);
        
        DateAxis axis = new DateAxis(Messages.getMessage("general.label.Date"));
        //axis.setTickAngle(-50);
        axis.setMin(df.format(startDate));
        axis.setMax(df.format(new Date()));
        axis.setTickFormat("%#d %b");
        chartModel.getAxes().put(AxisType.X, axis);

        String yLabel = Messages
            .getMessage("currency." + getEntity().getCurrency().getCurrencyCode());
        Axis yAxis = chartModel.getAxis(AxisType.Y);
        yAxis.setLabel(yLabel);
    }
    
    private void buildCurrencyBalanceMap(){
    	currencyBalances = txnRepository.findCurrencyBalances(getEntity());         
    }

    private void buildBalanceModel( Date startDate ) {

        balanceModels = new ArrayList<>();

        FinanceAccountBalanceModel m = new FinanceAccountBalanceModel();
        m.setLineType("TAKE-OVER");
        m.setDate(startDate);
        m.setTopic(Messages.getMessage("finance.label.TakeOver"));
        m.setAmount(BigDecimal.ZERO);
        m.setBalance(takeOverTotal);
        balanceModels.add(m);

        for (FinanceAccountTxn txn : accountTxns) {
            m = new FinanceAccountBalanceModel();

            m.setFeaturePointer(txn.getFeature());
            m.setTopic(txn.getInfo());
            if (txn.getDebit()) {
                m.setAmount(txn.getAmount().negate());
                takeOverTotal = takeOverTotal.subtract(txn.getLocalAmount());
            } else {
                m.setAmount(txn.getAmount());
                takeOverTotal = takeOverTotal.add(txn.getLocalAmount());
            }
            m.setCcy(txn.getCurrency());
            m.setBalance(takeOverTotal);
            m.setDate(txn.getDate());
            balanceModels.add(m);
        }
        
        m = new FinanceAccountBalanceModel();
        m.setDate(new Date());
        m.setLineType("RESULT");
        m.setTopic(Messages.getMessage("general.label.Result"));
        m.setAmount(BigDecimal.ZERO);
        m.setBalance(takeOverTotal);
        balanceModels.add(m);

    }
    
    public boolean isCurrencyTableRenderable(){
        return getEntity().getCurrency() != currencyService.getReportCurrency() 
        		|| getEntity().getAccountRoles().contains("MULTI_CURRENCY");
    }


}

