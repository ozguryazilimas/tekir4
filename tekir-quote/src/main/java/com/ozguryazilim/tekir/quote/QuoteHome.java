package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.QuoteItem;
import com.ozguryazilim.tekir.entities.QuoteSummary;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditor;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditorListener;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherStateEffect;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.utils.SummaryCalculator;
import com.ozguryazilim.telve.messages.FacesMessages;
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

    @Override
    protected RepositoryBase<Quote, QuoteViewModel> getRepository() {
        return repository;
    }

    @Override
    public void createNew() {
        super.createNew();

        getEntity().setCurrency(currencyService.getDefaultCurrency());
        //TODO: Bu iş için DateUtils fean yazmak lazım.
        LocalDateTime dt = LocalDateTime.now();
        getEntity().setExpireDate(Date.from(dt.plusDays(30).atZone(ZoneId.systemDefault()).toInstant()));
        getEntity().setRevision(1);
    }

    @Override
    public void addItem() {
        QuoteItem item = new QuoteItem();
        item.setMaster(getEntity());
        commodityItemEditor.openDialog(item, getEntity().getCurrency(), this);
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
            FacesMessages.warn("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmek ister misiniz?");
        }
        return super.onAfterLoad(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onBeforeSave() {
        //TODO: Before Save'de her seferinden yeniden sum hesaplamaya gerek var mı? Arayüze ReCalculate gibi bir düğme mi koysak?
        //calculateSummaries();

        //Eğer daha önce bir process alınmamış ise yeni bir tane oluştur.
        if (getEntity().getProcess() == null) {
            getEntity().setProcess(processService.createProcess(getEntity().getAccount(), getEntity().getTopic(), ProcessType.SALES));
        }

        getEntity().setLocalAmount(currencyService.convert(getEntity().getCurrency(), getEntity().getTotal(), getEntity().getDate()));
        
        return super.onBeforeSave();
    }

    @Override
    public List<QuoteSummary> getTaxes() {
        List<QuoteSummary> result = new ArrayList<>();

        getEntity().getSummaries().entrySet().stream()
                .filter(e -> e.getKey().startsWith("Tax."))
                .forEach(e -> {
                    result.add(e.getValue());
                });

        return result;
    }

    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("won", "fa fa-check", false), new VoucherState("WON", VoucherStateType.CLOSE, VoucherStateEffect.POSIVITE));
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true), new VoucherState("WON", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE));
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.DRAFT);
        //config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("unlock", "fa fa-unlock", true ), VoucherState.DRAFT);
        return config;
    }

    @Override
    public void saveItem(QuoteItem item) {
        if (!getEntity().getItems().contains(item)) {
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
            FacesMessages.warn("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmek ister misiniz?");
        }
    }

    public Person getPerson() {
        if (getAccount() instanceof Person) {
            return (Person) getAccount();
        } else {
            return ((Corporation) getAccount()).getPrimaryContact();
        }
    }

    public Corporation getCorporation() {
        if (getAccount() instanceof Corporation) {
            return (Corporation) getAccount();
        } else {
            return ((Person) getAccount()).getCorporation();
        }
    }

    @Override
    public void calculateSummaries() {
        SummaryCalculator<Quote, QuoteItem, QuoteSummary> sc = new SummaryCalculator();
        sc.calcSummaries(this::getEntity, getEntity()::getItems, getEntity()::getSummaries, () -> new QuoteSummary(), getEntity()::setTotal);
    }
}
