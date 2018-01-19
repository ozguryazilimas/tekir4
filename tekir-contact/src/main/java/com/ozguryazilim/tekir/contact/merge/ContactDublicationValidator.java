/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.merge;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Bir contact'ın dublice olup olmadığını control eder.
 *
 * Kontrol için Configurasyondan alınan kuralları uygular.
 *
 * - e-posta ile kontrol - sadece primary/hepsi - telefon numarası ile kontrol -
 * sadece primary/hepsi - name üzerinden kontrol ( en güvensiz olanı )
 *
 * Eğer şüpheli kayıtlar varsa bunların listesini döndürür.
 *
 * Validasyon için kullanılacak olan Contact kayıtlı olmak zorunda değil.
 *
 * @author Hakan Uygun
 */
@Dependent
public class ContactDublicationValidator implements Serializable {

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private ContactInformationRepository informationRepository;

    public List<Contact> getSuspectedContacts(Contact contact) {

        List<Contact> suspecteds = new ArrayList<>();

        checkByEmail(contact, suspecteds);
        checkByMobile(contact, suspecteds);
        checkByPhone(contact, suspecteds);

        return suspecteds;
    }

    /**
     * Veritabanında verilen contact e-posta adresine sahip e-postaları olan
     * diğer contactları bulup döndürür.
     *
     * @param contact
     * @param suspecteds
     */
    protected void checkByEmail(Contact contact, List<Contact> suspecteds) {
        //Contact'ın primary e-postası yok çıkalım.
        if (contact.getPrimaryEmail() == null) {
            return;
        }

        List<ContactEMail> infos = informationRepository.findByEmail(contact.getPrimaryEmail().getEmailAddress());

        //Eğer contact kayıtlı ise kendisi ile karışmayalım
        //Kayıtlı değilse de contactId null olacağı için problem olmayacak.
        infos.stream()
                .filter((info) -> (!info.getContact().getId().equals(contact.getId()) && !suspecteds.contains(info.getContact())))
                .forEachOrdered((info) -> {
                    suspecteds.add(info.getContact());
                }); 

    }

    protected void checkByMobile(Contact contact, List<Contact> suspecteds) {
        //Contact'ın primary e-postası yok çıkalım.
        if (contact.getPrimaryMobile() == null) {
            return;
        }

        List<ContactPhone> infos = informationRepository.findByMobile(contact.getPrimaryMobile().getNumber());

        //Eğer contact kayıtlı ise kendisi ile karışmayalım
        //Kayıtlı değilse de contactId null olacağı için problem olmayacak.
        infos.stream()
                .filter((info) -> (!info.getContact().getId().equals(contact.getId()) && !suspecteds.contains(info.getContact())))
                .forEachOrdered((info) -> {
                    suspecteds.add(info.getContact());
                }); 

    }

    protected void checkByPhone(Contact contact, List<Contact> suspecteds) {
        //Contact'ın primary e-postası yok çıkalım.
        if (contact.getPrimaryPhone() == null) {
            return;
        }

        List<ContactPhone> infos = informationRepository.findByPhone(contact.getPrimaryPhone().getNumber());

        //Eğer contact kayıtlı ise kendisi ile karışmayalım
        //Kayıtlı değilse de contactId null olacağı için problem olmayacak.
        infos.stream()
                .filter((info) -> (!info.getContact().getId().equals(contact.getId()) && !suspecteds.contains(info.getContact())))
                .forEachOrdered((info) -> {
                    suspecteds.add(info.getContact());
                }); 
        
    }

}
