/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.ActivityMention;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.lookup.LookupSelectTuple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.primefaces.event.SelectEvent;

/**
 * @author oyas
 */
@FormEdit(feature = ActivityFeature.class)
public class ActivityHome extends FormBase<Activity, Long> {

    @Inject
    private ViewConfigResolver viewConfigResolver;

    @Inject
    private ActivityRepository repository;

    @Inject
    private Event<ActivityMentionEvent> event;

    private Class<? extends ViewConfig> returnPage;

    @Override
    protected RepositoryBase<Activity, ?> getRepository() {
        return repository;
    }

    public String getViewFragment() {
        ActivityController ac = ActivityRegistery.getMetaData(getEntity());
        if (ac != null) {
            return viewConfigResolver.getViewConfigDescriptor(ac.viewer()).getViewId();
        }

        return "";
    }

    public String getEditFragment() {
        ActivityController ac = ActivityRegistery.getMetaData(getEntity());
        if (ac != null) {
            return viewConfigResolver.getViewConfigDescriptor(ac.editor()).getViewId();
        }

        return "";
    }

    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getActivityNo());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }

    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact) {
        return FeatureUtils.getFeaturePointer(contact);
    }

    @Override
    public Class<? extends ViewConfig> getReturnPage() {
        //Eğer geri dönüş için bir sayfa verilmiş ve Status SUCCESS, FAILD v.b. ise 
        if (returnPage != null && (getEntity().getStatus().equals(ActivityStatus.SUCCESS) || getEntity().getStatus().equals(ActivityStatus.FAILED))) {
            return returnPage;
        }

        return super.getReturnPage();
    }

    @Override
    public Class<? extends ViewConfig> getCloseReturnPage() {
        //Eğer geri dönüş için bir sayfa verilmiş ve Status SUCCESS, FAILD v.b. ise 
        if (returnPage != null) {
            return returnPage;
        }

        return super.getCloseReturnPage();
    }


    public void setReturnPage(Class<? extends ViewConfig> returnPage) {
        this.returnPage = returnPage;
    }

    //***********************************
    // Mention Ekleme Fonksiyonları

    /**
     * Contact Seçim dialog sonucu çağrılır
     *
     * @param event
     */
    public void onContactSelect(SelectEvent event) {

        if (event.getObject() != null) {
            List<Contact> ls = getContacts(event);
            addContacts(ls);
        }
    }

    /**
     * Verilen tanıları muayene'ye ekler
     *
     * @param discs
     */
    protected void addContacts(List<Contact> discs) {
        for (Contact c : discs) {

            FeaturePointer pp = new FeaturePointer();
            pp.setBusinessKey(c.getName());
            pp.setPrimaryKey(c.getId());
            pp.setFeature(FeatureRegistery.getFeatureClass(c.getClass()).getSimpleName());

            if (!isMentionAdded(pp)) {
                ActivityMention mention = new ActivityMention();
                mention.setActivity(getEntity());
                mention.setFeaturePointer(pp);
                getEntity().getMentions().add(mention);
            }
        }
    }

    /**
     * Verilen tanının daha önce listeye eklenip eklenmediğine bakar.
     *
     * @param c
     * @return
     */
    protected boolean isMentionAdded(FeaturePointer c) {
        for (ActivityMention mc : getEntity().getMentions()) {
            if (mc.getFeaturePointer().equals(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Seçim eventinden diagnosis listesini döndürür.
     */
    @SuppressWarnings({"unchecked"})
    private List<Contact> getContacts(SelectEvent event) {
        LookupSelectTuple tuple = (LookupSelectTuple) event.getObject();
        List<Contact> ls = new ArrayList<>();
        if (tuple != null) {
            if (tuple.getValue() instanceof List) {
                ls.addAll((List<Contact>) tuple.getValue());
            } else {
                ls.add((Contact) tuple.getValue());
            }
        }

        return ls;
    }

    public void removeMention(int index) {
        getEntity().getMentions().remove(index);
    }

    @Override
    public boolean onBeforeSave() {

        //Person kontrolü
        if (getEntity().getPerson() != null) {

            FeaturePointer pp = new FeaturePointer();
            pp.setBusinessKey(getEntity().getPerson().getName());
            pp.setPrimaryKey(getEntity().getPerson().getId());
            pp.setFeature(FeatureRegistery.getFeatureClass(getEntity().getPerson().getClass()).getSimpleName());

            checkPrimaryMention(pp, "PERSON");

        }

        //Corporation kontrolü
        if (getEntity().getCorporation() != null) {

            FeaturePointer pp = new FeaturePointer();
            pp.setBusinessKey(getEntity().getCorporation().getName());
            pp.setPrimaryKey(getEntity().getCorporation().getId());
            pp.setFeature(FeatureRegistery.getFeatureClass(getEntity().getCorporation().getClass()).getSimpleName());

            checkPrimaryMention(pp, "CORPORATION");

        }

        //Regarding kontrolü
        if (getEntity().getRegarding() != null) {
            checkPrimaryMention(getEntity().getRegarding(), "REGARDING");
        }


        //Şimdi başka mention eklemek isteyen varsa ekleyebilsin diye event fırlatıyoruz.
        ActivityMentionEvent mentionEvent = new ActivityMentionEvent(getEntity());
        event.fire(mentionEvent);

        //Şimdi geriye gelenleri ekliyoruz.
        for (FeaturePointer fp : mentionEvent.getMentionList()) {
            if (!isMentionAdded(fp)) {
                ActivityMention mention = new ActivityMention();
                mention.setActivity(getEntity());
                mention.setFeaturePointer(fp);
                getEntity().getMentions().add(mention);
            }
        }

        //Eğer yeni kayıt ise state'i düzeltelim.
        if (ActivityStatus.DRAFT.equals(getEntity().getStatus())) {
            if (getEntity().getDueDate() != null) {
                getEntity().setStatus(ActivityStatus.SCHEDULED);
            } else {
                getEntity().setStatus(ActivityStatus.OPEN);
            }
        }

        return super.onBeforeSave();
    }

    private void checkPrimaryMention(FeaturePointer fp, String type) {

        //Silinecek mention listesini tutar
        List<ActivityMention> deleteList = new ArrayList<>();
        boolean handled = false;

        for (ActivityMention mention : getEntity().getMentions()) {
            if (mention.getType().equals(type)) {
                handled = true;

                if (!mention.getFeaturePointer().equals(fp)) {
                    mention.setFeaturePointer(fp);
                }
            } else if (mention.getFeaturePointer().equals(fp)) {
                //PRIMARY değil ama var. Sileceğiz.
                deleteList.add(mention);
            }
        }

        //silincekleri silelim
        deleteList.forEach((mention) -> {
            getEntity().getMentions().remove(mention);
        });

        //Eğer update edilmemiş ise yeni ekleyelim.
        if (!handled) {
            ActivityMention m = new ActivityMention();
            m.setActivity(getEntity());
            m.setType(type);
            m.setFeaturePointer(fp);
            getEntity().getMentions().add(m);
        }
    }


    public Class<? extends ViewConfig> closeSuccess() {
        getEntity().setDate(new Date());
        getEntity().setStatus(ActivityStatus.SUCCESS);
        return save();
    }

    public Class<? extends ViewConfig> closeFaild() {
        getEntity().setDate(new Date());
        getEntity().setStatus(ActivityStatus.FAILED);
        return save();
    }
}
