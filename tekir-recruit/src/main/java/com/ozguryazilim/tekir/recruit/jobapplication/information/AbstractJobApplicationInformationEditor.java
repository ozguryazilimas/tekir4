package com.ozguryazilim.tekir.recruit.jobapplication.information;

import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.Before;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.util.ProxyUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author serdar
 */
public abstract class AbstractJobApplicationInformationEditor<E extends EntityBase> implements Serializable {

    @Inject
    private ViewConfigResolver viewConfigResolver;

    private E entity;

    private JobApplication jobApplication;

    private String pageType;

    private List<E> entityList;

    protected abstract JobApplicationInformationRepositoryBase<E, ?> getRepository();

    public abstract String getIcon(E entity);

    public abstract void setJobApplicationOfEntity(JobApplication jobApplication);

    public void init(JobApplication jobApplication, String pageType) {
        setJobApplication(jobApplication);
        setPageType(pageType);

        if (getJobApplication().isPersisted()) {
            setEntityList(getRepository().findByJobApplication(getJobApplication()));
        } else {
            setEntityList(new ArrayList<>());
        }
    }

    protected void openDialogImpl() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentHeight", 450);

        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }

    public void closeDialog() {
        if (!onBeforeClose()) {
            return;
        }

        save();

        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void cancelDialog() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    protected boolean onBeforeClose() {
        return true;
    }

    public String getDialogName() {
        Class<? extends ViewConfig> page = ((JobApplicationInformationEditor) (ProxyUtils.getUnproxiedClass(this.getClass()).getAnnotation(JobApplicationInformationEditor.class))).page();
        String viewId = viewConfigResolver.getViewConfigDescriptor(page).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }

    protected E getNewEntity() {
        try {
            return getRepository().createNew();
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

    public void create() {
        setEntity(getNewEntity());
        setJobApplicationOfEntity(getJobApplication());

        openDialogImpl();
    }

    public void edit(E entity) {
        setEntity(entity);

        openDialogImpl();
    }

    @Transactional
    public void delete(E entity) {
        getRepository().remove(entity);
        getEntityList().remove(entity);
    }

    @Transactional
    public void save() {
        if (isJobApplicationViewPage()) {
            getRepository().save(getEntity());
        }

        if (!getEntityList().contains(getEntity())) {
            getEntityList().add(getEntity());
        }
    }

    public boolean isJobApplicationViewPage() {
        return pageType.equals("VIEW") || pageType.equals("MASTER_VIEW");
    }

    protected void listenJobApplicationAfterChange(@Observes @EntityQualifier(entity = JobApplication.class) @After EntityChangeEvent event) {

        switch (event.getAction()) {
            case INSERT:
                getEntityList().forEach((e) -> {
                    getRepository().save(e);
                });
                break;
            case UPDATE:
                List<E> resultList = getRepository().findByJobApplication(getJobApplication());
                List<Long> entityIdList = getEntityList().stream().map(E::getId).collect(Collectors.toList());

                resultList.stream().filter((e) -> (!entityIdList.contains(e.getId()))).forEachOrdered((e) -> {
                    getRepository().remove(e);
                });

                getEntityList().stream().filter((e) -> (!e.isPersisted() || !resultList.contains(e))).forEachOrdered((e) -> {
                    getRepository().save(e);
                });

                break;
        }
    }

    protected void listenApplicantBeforeChange(@Observes @EntityQualifier(entity = JobApplication.class) @Before EntityChangeEvent event) {

        switch (event.getAction()) {
            case DELETE:
                getRepository().deleteByJobApplication(getJobApplication());
                break;
        }
    }

    public ViewConfigResolver getViewConfigResolver() {
        return viewConfigResolver;
    }

    public void setViewConfigResolver(ViewConfigResolver viewConfigResolver) {
        this.viewConfigResolver = viewConfigResolver;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public List<E> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<E> entityList) {
        this.entityList = entityList;
    }

}
