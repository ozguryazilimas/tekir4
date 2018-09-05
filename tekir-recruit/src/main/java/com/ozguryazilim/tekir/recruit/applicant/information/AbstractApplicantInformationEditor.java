package com.ozguryazilim.tekir.recruit.applicant.information;

import com.ozguryazilim.tekir.entities.Applicant;
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
 * ApplicantInformation editörleri ve widget için taban sınıf.
 * 
 * Hem View,MasterView hem de Edit sayfasında editörler çalışır. Edit sayfasında 
 * yapılan değişiklikler geçici bir entityList'de tutulduğundan ötürü ilgili modelde
 * equals ve hashCode methodları ayarlanmalıdır.
 * 
 * @author Erdem Uslu
 * @param <E> İşlem yapılacak entity sınıfı
 * 
 * @see Object#equals(java.lang.Object) 
 * @see Object#hashCode() 
 */
public abstract class AbstractApplicantInformationEditor<E extends EntityBase> implements Serializable {

    @Inject
    private ViewConfigResolver viewConfigResolver;

    private E entity;

    private Applicant applicant;

    private String pageType;

    private List<E> entityList;

    /**
     * Geriye kullanılacak olan Repository'i döndürür.
     *
     * @return
     */
    protected abstract ApplicantInformationRepositoryBase<E, ?> getRepository();

    /**
     * Verilen entity için hangi icon'un kullanılacağını belirler. 
     * 
     * Widget'da gösterilecek icon.
     *
     * @param entity 
     * @return
     */
    public abstract String getIcon(E entity);

    /**
     * İlgili entity için başvuran ataması yapar.
     * 
     * TODO: Bunu aslında burda implemente etmek hatta yapabilmek gerek. (Hatta bu gereksiz) 
     * Bunun için information modelleri ApplicantInformation gibi bir @MappedSuperclass miras alınıp
     * başvuran field'ı burada tanımlanabilir. Sonuç olarak bu editör modelleri
     * bir applicant'a sahip olmak zorunda.
     * 
     * @param applicant 
     */
    public abstract void setApplicantOfEntity(Applicant applicant);
    
    /**
     * entity listesini hazırlar, editör modelleri için gereken applicantı ve 
     * widget'ın hangi ekran tipinde çalışması gerektiğini ayarlar.
     * 
     * İlgili Editör widget'ında çağırılması ve gerekli olan parametrelerin verilmesi gerekir!
     * 
     * @param applicant
     * @param pageType 
     */
    public void init(Applicant applicant, String pageType) {
        setApplicant(applicant);
        setPageType(pageType);

        if (getApplicant().isPersisted()) {
            setEntityList(getRepository().findByApplicant(getApplicant()));
        } else {
            setEntityList(new ArrayList<>());
        }
    }

    /**
     * İlgili editör sınıfına ait boş dialog açar.
     * 
     */
    protected void openDialogImpl() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentHeight", 450);

        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }
    
    /**
     * Entity'yi kaydeder ve dialog'u kapatır.
     * 
     * @see AbstractApplicantInformationEditor#save()
     */
    public void closeDialog() {
        if (!onBeforeClose()) {
            return;
        }

        save();

        RequestContext.getCurrentInstance().closeDialog(null);
    }

    /**
     * Dialogu hiç bir şey seçmeden kapatır.
     */
    public void cancelDialog() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    /**
     * Entity kaydedilmeden hemen önce alt editör sınıfları birşey yapmak isterlerse bu
     * methodu override edebilirler...
     * @return 
     */
    protected boolean onBeforeClose() {
        return true;
    }

    /**
     * ApplicantInformationEditor ile işaretlenmiş dialog adını döndürür.
     * 
     * @return 
     */
    public String getDialogName() {
        Class<? extends ViewConfig> page = ((ApplicantInformationEditor) (ProxyUtils.getUnproxiedClass(this.getClass()).getAnnotation(ApplicantInformationEditor.class))).page();
        String viewId = viewConfigResolver.getViewConfigDescriptor(page).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }

    /**
     * İlgili information repository ile yeni bir entity oluşturur.
     * 
     * @return 
     */
    protected E getNewEntity() {
        try {
            return getRepository().createNew();
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

    /**
     * Yeni bir entity ile entity'nin applicant'ı set edilir ve dialog açar.
     * 
     */
    public void create() {
        setEntity(getNewEntity());
        setApplicantOfEntity(getApplicant());

        openDialogImpl();
    }

    /**
     * Düzenlenecek olan entity'yi ayarlayıp dialog açar.
     * 
     * @param entity 
     */
    public void edit(E entity) {
        setEntity(entity);

        openDialogImpl();
    }

    /**
     * Ekranda silinmek için seçilen entity için silme yapısı.
     * 
     * View ekranında silme yapıldığında işlemler repository'de yapılır.
     * Diğer ekranlarda geçici entity listesinden siler.
     * 
     * @see AbstractApplicantInformationEditor#listenApplicantBeforeChange(com.ozguryazilim.telve.forms.EntityChangeEvent) 
     * @see AbstractApplicantInformationEditor#listenApplicantAfterChange(com.ozguryazilim.telve.forms.EntityChangeEvent) 
     * 
     * @param entity 
     */
    @Transactional
    public void delete(E entity) {
        if (isApplicantViewPage()) {
            getRepository().remove(entity);
        }

        getEntityList().remove(entity);
    }

    /**
     * Editörde oluşturulan entity için kayıt yapısı.
     * 
     * View ekranında kayıt yapıldığında işlemler repository'de yapılır.
     * Diğer ekranlarda entity'yi yoksa geçici listeye ekler.
     * 
     * @see AbstractApplicantInformationEditor#listenApplicantBeforeChange(com.ozguryazilim.telve.forms.EntityChangeEvent) 
     * @see AbstractApplicantInformationEditor#listenApplicantAfterChange(com.ozguryazilim.telve.forms.EntityChangeEvent) 
     * 
     */
    @Transactional
    public void save() {
        if (isApplicantViewPage()) {
            getRepository().save(getEntity());
        }

        if (!getEntityList().contains(getEntity())) {
            getEntityList().add(getEntity());
        }
    }

    /**
     * İlgili applicant information editörünün view sayfasında açılıp açılmadığını döndürür.
     * 
     * @return 
     */
    public boolean isApplicantViewPage() {
        return pageType.equals("VIEW") || pageType.equals("MASTER_VIEW");
    }

    /**
     * Herhangi bir sayfada (New/Edit gibi) applicant'ın eklenme/düzenlenme'si sonrasında
     * kaydedilmesini dinler (Kaydedildikten sonra çalışır)
     * ve geçici entity listesinde yapılan değişiklikleri  repository ile kaydeder.
     * 
     * TODO: Bir göz geçirmekte yarar var. Yeni event oluşturmak da düşünülebilir.
     * 
     * @param event 
     */
    protected void listenApplicantAfterChange(@Observes @EntityQualifier(entity = Applicant.class) @After EntityChangeEvent event) {

        switch (event.getAction()) {
            case INSERT:
                getEntityList().forEach((e) -> {
                    getRepository().save(e);
                });
                break;
            case UPDATE:
                List<E> resultList = getRepository().findByApplicant(getApplicant());
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

    /**
     * Herhangi bir sayfada (New/Edit gibi) applicant'ın silinmesini dinler 
     * (Silinmeden önce çalışır) ve geçici entity listesinde yapılan değişiklikleri 
     * repository ile kaydeder.
     * 
     * TODO: Bir göz geçirmekte yarar var. Yeni event oluşturmak da düşünülebilir.
     * 
     * @param event 
     */
    protected void listenApplicantBeforeChange(@Observes @EntityQualifier(entity = Applicant.class) @Before EntityChangeEvent event) {

        switch (event.getAction()) {
            case DELETE:
                getRepository().deleteByApplicant(getApplicant());
                break;
        }
    }

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

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

}
