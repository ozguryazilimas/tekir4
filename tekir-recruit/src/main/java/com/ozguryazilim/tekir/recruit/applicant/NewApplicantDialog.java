package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.auth.Identity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.primefaces.context.RequestContext;

/**
 * Yeni Applicant Popup Controller.
 *
 * //TODO: Buna gerek var mi belli degil. inputQuickDialog (hizli menu gecilebilir).
 * 
 * @author Erdem Uslu
 */
@Named
@SessionScoped
public class NewApplicantDialog implements Serializable {

    @Inject
    private ViewConfigResolver viewConfigResolver;

    @Inject
    private ApplicantRepository repository;
    
    @Inject
    private Identity identity;

    private Applicant applicant;

    /**
     * Applicant oluştur ve dialog'u göster.
     */
    public void openDialog() {
        applicant = new Applicant();
        applicant.setOwner(identity.getLoginName());
        
        openDialogImpl();
    }

    /**
     * Dialog özelliklerini ayarla ve dialog oluştur.
     */
    protected void openDialogImpl() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        // options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 450);

        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }

    /**
     * startPopup dialog adını döndürür.
     *
     * @return
     */
    public String getDialogName() {
        String viewId = viewConfigResolver.getViewConfigDescriptor(RecruitPages.applicant.NewApplicantPopup.class).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }
    
    /**
     * Yeni applicant'ı save eder.
     */
    public void closeDialog() {
        //Applicant'ının ad soyadından bütünleşik isim elde ediyoruz.
        applicant.setName(applicant.getFirstName() + " " + applicant.getLastName());
        
        //Sonra da kaydediyoruz.
        repository.save(applicant);
        
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    /**
     * Bir şey yapmadan çık.
     */
    public void cancelDialog() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

}
