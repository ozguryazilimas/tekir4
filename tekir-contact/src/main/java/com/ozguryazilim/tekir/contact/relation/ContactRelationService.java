/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.relation;

import com.google.common.base.Joiner;
import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactRelation;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.RelatedContact;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Bağlantılar arası ilişkilerin yönetim için temel servis.
 *
 * Bağlantı bulma, saklama ve silme operasyonları bu servis üzerinden
 * yapılmalıdır.
 *
 * @author Hakan Uygun
 */
@ApplicationScoped
public class ContactRelationService implements Serializable {

    @Inject
    private RelatedContactRepository relatedContactRepository;

    @Inject
    private ContactRelationRepository contactRelationRepository;

    @Inject
    private ContactRepository contactRepository;

    /**
     * Verilen contact için ilişkili diğer contactları bulup döndürür.
     *
     * @param contact
     * @return
     */
    public List<RelatedContactViewModel> getRelatedContacts(Contact contact) {

        //TODO:cacheleme yapılabilir mi? Tüm edit ve delete işlemleri buradan olacak.
        List<RelatedContactViewModel> results = new ArrayList<>();

        //contactRepository.
        //Contact tipine göre Person ise çalıştığı kurum ilişki olarak eklenecek
        if (contact instanceof Person) {
            populatePersonCorporation((Person) contact, results);
        }

        if (contact instanceof Corporation) {
            //Contact tipine göre Kurum ise çalıştığı kurum kendisi olan diğer contactlar bulunup ilişki olarak eklenecek
            populateEmployees((Corporation) contact, results);

            //Contact tipine göre Kurum ise parenCorp kendisi olan diğer contactlar bulunup ilişki olarak eklenecek
            populateSubCorporation((Corporation) contact, results);

            //Contact tipine göre Kurum ise parenCorp ilişki olarak eklenecek
            populateParentCorporation((Corporation) contact, results);

        }
        
        //Source'u kendisi olanlar ilişki tablosundan bulunup eklenecek
        List<RelatedContact> sc = relatedContactRepository.findBySourceContact(contact);
        populateSourceRelations(sc, results);
        
        
        
        //Target'ı kendisi olanlar ilişki tablosundan bulunup eklenecek
        sc = relatedContactRepository.findByTargetContact(contact);
        populateTargetRelations(sc, results);

        //İlişki ağırlığına göre sıralama yapılacak
        Collections.sort(results, (t, t1) -> {
            return t.getRelation().getWeigth().compareTo(t1.getRelation().getWeigth());
        });

        //TODO: Limit nasıl olabilir acaba?
        //TODO: Level / Derinlik burada yapılabilir mi?
        return results;
    }

    /**
     * Verilen ilişkili contact'ı saklar.
     *
     * @param rcvm
     */
    public void saveRelatedContact(RelatedContactViewModel rcvm) {
        //Eğer relation id'si sıfırdan küçük ise internal bir ilişkidir. Normalde buraya kadar gelmemeli
        if (rcvm.getRelation().getId() < 0) {
            return;
        }

        
        //Yeni kayıt mı yoksa güncelleme mi? Güncelleme ise orjinal kayıt bulunmalı
        RelatedContact relatedContact;
        if (rcvm.getId() == null || rcvm.getId() == 0) {
            relatedContact = new RelatedContact();
        } else {
            relatedContact = relatedContactRepository.findBy(rcvm.getId());
        }

        //ilişkinin düz/ters olmasına göre asıl definition bulunup ayarlanacak.
        if (rcvm.getRelation().getRevers()) {
            relatedContact.setTargetContact(rcvm.getSourceContact());
            relatedContact.setSourceContact(rcvm.getTargetContact());
        } else {
            relatedContact.setSourceContact(rcvm.getSourceContact());
            relatedContact.setTargetContact(rcvm.getTargetContact());
        }

        ContactRelation relation = contactRelationRepository.findBy(rcvm.getRelation().getId());
        relatedContact.setRelation(relation);
        relatedContact.setInfo(rcvm.getInfo());

        relatedContactRepository.save(relatedContact);
        
        //TODO: Feed vermeli mi?
    }

    /**
     * Verilen ilişkili contact silinir.
     *
     * @param rcvm
     */
    public void deleteRelatedContact(RelatedContactViewModel rcvm) {
        
        
        
        //Zaten kaydedilmemiş
        if (rcvm == null || rcvm.getId() == null || rcvm.getId() == 0) {
            return;
        }

        //Eğer relation id'si sıfırdan küçük ise internal bir ilişkidir. Normalde buraya kadar gelmemeli
        if (rcvm.getRelation().getId() < 0) {
            return;
        }

        //Asıl kaydı bulup silelim.
        relatedContactRepository.deleteById(rcvm.getId());

        //TODO: Feed vermeli mi?
    }

    private void populatePersonCorporation(Person person, List<RelatedContactViewModel> results) {
        if (person.getCorporation() != null) {
            RelatedContactViewModel rcvm = new RelatedContactViewModel(person, getWorkCorporationRelation());
            rcvm.setTargetContact(person.getCorporation());
            rcvm.setInfo(person.getCorporation().getOrganizastionName());
            results.add(rcvm);
        }
    }

    private void populateParentCorporation(Corporation corporation, List<RelatedContactViewModel> results) {
        if (corporation.getParentCorporation() != null) {
            RelatedContactViewModel rcvm = new RelatedContactViewModel(corporation, getParentCorporationRelation());
            rcvm.setTargetContact(corporation.getParentCorporation());
            rcvm.setInfo(corporation.getParentCorporation().getOrganizastionName());
            results.add(rcvm);
        }
    }

    /**
     * Alt Şirketleri bulup ilişki listesine ekler.
     *
     * @param corporation
     * @param results
     */
    private void populateSubCorporation(Corporation corporation, List<RelatedContactViewModel> results) {

        List<Contact> contacts = contactRepository.findByParentCorporation(corporation);

        for (Contact contact : contacts) {
            RelatedContactViewModel rcvm = new RelatedContactViewModel(corporation, getSubCorporationRelation());
            rcvm.setTargetContact(contact);
            rcvm.setInfo(((Corporation) contact).getOrganizastionName());
            results.add(rcvm);
        }

    }

    /**
     * Alt Şirketleri bulup ilişki listesine ekler.
     *
     * @param corporation
     * @param results
     */
    private void populateEmployees(Corporation corporation, List<RelatedContactViewModel> results) {

        List<Contact> contacts = contactRepository.findByCorporation(corporation);

        for (Contact contact : contacts) {
            RelatedContactViewModel rcvm = new RelatedContactViewModel(corporation, getEmployeeRelation());
            rcvm.setTargetContact(contact);
            rcvm.setInfo(((Person) contact).getJobTitle());
            results.add(rcvm);
        }

    }

    /**
     * Alt Şirketleri bulup ilişki listesine ekler.
     *
     * @param corporation
     * @param results
     */
    private void populateSourceRelations(List<RelatedContact> scs, List<RelatedContactViewModel> results) {

        
        for( RelatedContact rc : scs ){
            RelatedContactViewModel rcvm = new RelatedContactViewModel(rc.getSourceContact(), getRelation(rc.getRelation()));
            rcvm.setId(rc.getId());
            rcvm.setTargetContact(rc.getTargetContact());
            rcvm.setInfo(rc.getInfo());
            results.add(rcvm);
        }
        
    }
    
    
    /**
     * Alt Şirketleri bulup ilişki listesine ekler.
     *
     * @param corporation
     * @param results
     */
    private void populateTargetRelations(List<RelatedContact> scs, List<RelatedContactViewModel> results) {

        
        for( RelatedContact rc : scs ){
            RelatedContactViewModel rcvm = new RelatedContactViewModel(rc.getTargetContact(), getReversRelation(rc.getRelation()));
            rcvm.setId(rc.getId());
            rcvm.setTargetContact(rc.getSourceContact());
            rcvm.setInfo(rc.getInfo());
            results.add(rcvm);
        }
        
    }
    
    
    /**
     * Factory bir method.
     *
     * İnternal ilişki döndürecek
     *
     * @return
     */
    private ContactRelationViewModel getWorkCorporationRelation() {
        ContactRelationViewModel result = new ContactRelationViewModel();
        result.setId(-1l);
        result.setCode("INTERNAL");
        //FIXME: i18n
        result.setName("İş Yeri");
        result.setWeigth(-10);
        //Sadece kurumlar hedeflenebilir.
        result.setTargetRoles("CORPORATION");
        return result;
    }

    /**
     * Factory bir method.
     *
     * İnternal ilişki döndürecek
     *
     * @return
     */
    private ContactRelationViewModel getParentCorporationRelation() {
        ContactRelationViewModel result = new ContactRelationViewModel();
        result.setId(-2l);
        result.setCode("INTERNAL");
        //FIXME: i18n
        result.setName("Üst Şirket");
        result.setWeigth(-10);
        //Sadece kurumlar hedeflenebilir.
        result.setTargetRoles("CORPORATION");
        return result;
    }

    /**
     * Factory bir method.
     *
     * İnternal ilişki döndürecek
     *
     * @return
     */
    private ContactRelationViewModel getSubCorporationRelation() {
        ContactRelationViewModel result = new ContactRelationViewModel();
        result.setId(-3l);
        result.setCode("INTERNAL");
        //FIXME: i18n
        result.setName("Alt Şirket");
        result.setWeigth(-5);
        //Sadece kurumlar hedeflenebilir.
        result.setTargetRoles("CORPORATION");
        return result;
    }

    /**
     * Factory bir method.
     *
     * İnternal ilişki döndürecek
     *
     * @return
     */
    private ContactRelationViewModel getEmployeeRelation() {
        ContactRelationViewModel result = new ContactRelationViewModel();
        result.setId(-4l);
        result.setCode("INTERNAL");
        //FIXME: i18n
        result.setName("Çalışan");
        result.setWeigth(-1);
        //Sadece kurumlar hedeflenebilir.
        result.setTargetRoles("PERSON");
        return result;
    }
    
    private ContactRelationViewModel getRelation( ContactRelation contactRelation ) {
        ContactRelationViewModel result = new ContactRelationViewModel();
        result.setId(contactRelation.getId());
        result.setCode(contactRelation.getCode());
        result.setName(contactRelation.getVectorName());
        result.setWeigth(contactRelation.getWeigth());
        result.setTargetRoles(Joiner.on(',').join(contactRelation.getTargetRoles()));
        return result;
    }
    
    
    private ContactRelationViewModel getReversRelation( ContactRelation contactRelation ) {
        ContactRelationViewModel result = new ContactRelationViewModel();
        result.setId(contactRelation.getId());
        result.setCode(contactRelation.getCode());
        result.setName(contactRelation.getReversName());
        result.setRevers(Boolean.TRUE);
        result.setWeigth(contactRelation.getWeigth());
        result.setTargetRoles(Joiner.on(',').join(contactRelation.getSourceRoles()));
        return result;
    }
}
