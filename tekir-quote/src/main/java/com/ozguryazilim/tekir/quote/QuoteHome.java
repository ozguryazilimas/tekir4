package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.core.dialogs.ExchangeRateNotFoundDialog;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.QuoteItem;
import com.ozguryazilim.tekir.entities.QuoteSummary;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditor;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditorListener;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;

import java.util.List;
import javax.inject.Inject;

import javax.money.convert.CurrencyConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.utils.SummaryCalculator;
import com.ozguryazilim.tekir.voucher.utils.VoucherItemUtils;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.reports.JasperReportHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Home Control Class
 *
 * @author
 */
@FormEdit(feature = QuoteFeature.class)
public class QuoteHome extends VoucherFormBase<Quote> implements VoucherCommodityItemEditorListener<QuoteItem> {

	private static Logger LOG = LoggerFactory.getLogger(QuoteHome.class);

	@Inject
	private QuoteRepository repository;

	@Inject
	private CurrencyService currencyService;

	@Inject
	private AccountTxnService accountTxnService;

	@Inject
	private VoucherCommodityItemEditor commodityItemEditor;

	@Inject
	private ProcessService processService;

	@Inject
	private JasperReportHandler reportHandler;

    @Inject
    private ExchangeRateNotFoundDialog exchangeRateNotFoundDialog;

	@Override
	protected RepositoryBase<Quote, QuoteViewModel> getRepository() {
		return repository;
	}

	@Override
	public void createNew() {
		super.createNew();

		getEntity().setCurrency(currencyService.getDefaultCurrency());
		// TODO: Bu iş için DateUtils fean yazmak lazım.
		LocalDateTime dt = LocalDateTime.now();
		getEntity().setExpireDate(Date.from(dt.plusDays(30).atZone(ZoneId.systemDefault()).toInstant()));
		getEntity().setRevision(1);
	}

	@Override
	public void addItem() {
        if (getEntity().getAccount() == null) {
            FacesMessages.error("facesMessages.AddItem.NoAccountError",
                "facesMessages.AddItem.NoAccountErrorDetail");
        } else {
            QuoteItem item = new QuoteItem();
            commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
        }
	}

	@Override
	public void editItem(QuoteItem item) {
		commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
	}

	@Override
	public void removeItem(QuoteItem item) {
		getEntity().getItems().remove(item);
		calculateSummaries();
	}

	@Override
	public boolean onAfterLoad() {
		if (!getEntity().getAccount().getContactRoles().contains("ACCOUNT")) {
			FacesMessages.warn("facesMessages.contact.NotAccount", "facesMessages.contact.NotAccountDetail");
		}
		return super.onAfterLoad(); // To change body of generated methods,
									// choose Tools | Templates.
	}

	@Override
	public boolean onBeforeSave() {
		// TODO: Before Save'de her seferinden yeniden sum hesaplamaya gerek var
		// mı? Arayüze ReCalculate gibi bir düğme mi koysak?
		// calculateSummaries();

		// Eğer daha önce bir process alınmamış ise yeni bir tane oluştur.
		if (getEntity().getProcess() == null) {
			getEntity().setProcess(
					processService.createProcess(getEntity().getAccount(), getEntity().getTopic(), ProcessType.SALES));
		}

        try {
            getEntity().setLocalAmount(currencyService.convert(
                getEntity().getCurrency(), getEntity().getTotal(), getEntity().getDate()));
        } catch (CurrencyConversionException CCEx) {
            exchangeRateNotFoundDialog.openDialog();
            return false;
        }

		return super.onBeforeSave();
	}

	@Override
	public List<QuoteSummary> getTaxes() {
            return VoucherItemUtils.getTaxes(getEntity().getSummaries());
	}

	@Override
	protected VoucherStateConfig buildStateConfig() {
		VoucherStateConfig config = new VoucherStateConfig();

		config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("won", "fa fa-check", false), VoucherState.WON);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true), VoucherState.LOSS);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true),
				VoucherState.DRAFT);
		// config.addTranstion(VoucherState.CLOSE, new
		// VoucherStateAction("unlock", "fa fa-unlock", true ),
		// VoucherState.DRAFT);

		config.addStateTypeAction(VoucherStateType.OPEN, new VoucherPrintOutAction(this));
		config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));
		return config;
	}

	@Override
	public void saveItem(QuoteItem item) {
            //Yani yeni ekleniyor ise
            if ( item.getId() == null && item.getMaster() == null ) {
                item.setMaster(getEntity());
                getEntity().getItems().add(item);
            }
            calculateSummaries();
	}

	public Process getProcess() {
		return getEntity().getProcess();
	}

	public void setProcess(Process process) {
		getEntity().setProcess(process);
		if (process != null) {
			getEntity().setAccount(process.getAccount());
			getEntity().setTopic(process.getTopic());
		}
	}

	public Contact getAccount() {
		return getEntity().getAccount();
	}

	public void setAccount(Contact account) {
		getEntity().setAccount(account);
		getEntity().setProcess(null);

		if (!account.getContactRoles().contains("ACCOUNT")) {
			FacesMessages.warn("facesMessages.contact.NotAccount", "facesMessages.contact.NotAccountDetail");
		}
	}

	public AbstractPerson getPerson() {
		if (getAccount() instanceof AbstractPerson) {
			return (AbstractPerson) getAccount();
		} else {
			return ((Corporation) getAccount()).getPrimaryContact();
		}
	}

	public Corporation getCorporation() {
		if (getAccount() instanceof Corporation) {
			return (Corporation) getAccount();
		} else {
			return ((AbstractPerson) getAccount()).getCorporation();
		}
	}

	@Override
	public void calculateSummaries() {
		SummaryCalculator<Quote, QuoteItem, QuoteSummary> sc = new SummaryCalculator();
		sc.calcSummaries(this::getEntity, getEntity()::getItems, getEntity()::getSummaries, () -> new QuoteSummary(),
				getEntity()::setTotal, getEntity().getAccount().getVatWithholding());
	}
	
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }
}
