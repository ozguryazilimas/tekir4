/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit.applicant.information;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.util.ProxyUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yusuf
 */
public abstract class AbstractApplicantInformationEditor<E extends EntityBase> implements Serializable {

    @Inject
    private ViewConfigResolver viewConfigResolver;

    @Inject
    private FacesContext facesContext;

    private E entity;

    private Applicant applicantEntity;

    protected abstract RepositoryBase<E, ?> getRepository();

    private List<E> entityList;

    private List<E> deleteEntityList;

    private String pageName;

    protected void openDialogImpl() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentHeight", 450);
        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }

    public abstract List<E> initEntityList(Applicant applicant, Boolean isPostBack);

    public abstract E createNewModel();

    public abstract String getIcon(E information);

    public abstract String getEditorCaption();

    public void create() {
        setEntity(createNewModel());
        openDialogImpl();

    }

    public void closeDialog() {
        if (!onBeforeClose()) {
            return;
        }
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    protected boolean onBeforeClose() {
        return true;
    }

    public void cancelDialog() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public String getDialogName() {
        Class<? extends ViewConfig> page = ((ApplicantInformationEditor) (ProxyUtils.getUnproxiedClass(this.getClass()).getAnnotation(ApplicantInformationEditor.class))).page();
        String viewId = viewConfigResolver.getViewConfigDescriptor(page).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }

    public void edit(E applicantEducationEdit) {
        setEntity(applicantEducationEdit);
        openDialogImpl();
    }

    @Transactional
    public void delete(E applicantEducationDelete) {
        try {
            if (getApplicantEntity().isPersisted() && !getPageName().contains("applicantView")) {
                getDeleteEntityList().add(applicantEducationDelete);
                getEntityList().remove(applicantEducationDelete);
            } else if (getPageName().contains("applicantView")) {
                getRepository().remove(applicantEducationDelete);
            } else {
                getEntityList().remove(applicantEducationDelete);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    protected void getApplicantEntity(@Observes @EntityQualifier(entity = Applicant.class) @After EntityChangeEvent event) {
        //applicant oluştuktan sonra yakalıyoruz.
        if (event.getAction().equals(EntityChangeAction.INSERT) || event.getAction().equals(EntityChangeAction.UPDATE)) {
            setApplicantEntity((Applicant) event.getEntity());
            editApplicantEntity((Applicant) event.getEntity());
            save();
        }

    }

    public void save() {
        //yakaladığımız applicantı oluşturduğumuz liste içindeki educationların applicantları boş olduğu için dolduruyoruz.
        if (getApplicantEntity().isPersisted() && !getDeleteEntityList().isEmpty()) {
            for (E deleteApplicantInfo : getDeleteEntityList()) {
                getRepository().remove(deleteApplicantInfo);
            }
        }
        if (!getEntityList().isEmpty()) {
            for (E newApplicantInfo : getEntityList()) {
                getRepository().save(newApplicantInfo);
            }
            getDeleteEntityList().clear();
        }
    }

    public abstract void editApplicantEntity(Applicant applicant);

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public List<E> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<E> entityList) {
        this.entityList = entityList;
    }

    public Applicant getApplicantEntity() {
        return applicantEntity;
    }

    public void setApplicantEntity(Applicant applicantEntity) {
        this.applicantEntity = applicantEntity;
    }

    public List<E> getDeleteEntityList() {
        return deleteEntityList;
    }

    public void setDeleteEntityList(List<E> deleteEntityList) {
        this.deleteEntityList = deleteEntityList;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

}
