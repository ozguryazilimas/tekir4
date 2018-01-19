/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.merge;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.ContactViewModel;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 *
 * @author oyas
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ApplicationScoped
public class ContactDublicationValidatorTest {
    
    
    @Deployment
    public static WebArchive createDeployment() {
        
        /*
        File[] files = Maven
                .resolver()
                .loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile();
        
        System.out.println(files);
        */
        
        WebArchive war =  ShrinkWrap.create(WebArchive.class)
            .addClass(ContactViewModel.class)
            .addClass(ContactRepository.class)
            .addClass(ContactInformationRepository.class)
            .addClass(ContactDublicationValidator.class)
            .addPackage("com.ozguryazilim.telve.query")
            .addPackage("com.ozguryazilim.telve.query.filters")
            .addPackage("com.ozguryazilim.telve.query.columns")
            .addPackage("com.ozguryazilim.telve.data")
            .addAsWebInfResource("apache-deltaspike.properties",
                ArchivePaths.create("classes/META-INF/apache-deltaspike.properties"))
            .addAsWebInfResource("test-ds.xml",
                        ArchivePaths.create("test-ds.xml"))
            .addAsWebInfResource("test-persistence.xml",
                ArchivePaths.create("classes/META-INF/persistence.xml"))
            .addAsWebInfResource("test-beans.xml",
                ArchivePaths.create("beans.xml"))
            .addAsWebInfResource("test-orm.xml",
                ArchivePaths.create("classes/META-INF/orm.xml"));
            
        //Model paketini ekle
        war.addAsLibraries(
                Maven.resolver().loadPomFromFile("pom.xml").resolve(
                        "com.ozguryazilim.mutfak:tekir-contact-model:4.0.0-SNAPSHOT"
                )
                .withTransitivity()
                .asFile());

        //Bağımlılıkları ekle
        war.addAsLibraries(
                Maven.resolver().loadPomFromFile("pom.xml").resolve(
                        "org.apache.deltaspike.core:deltaspike-core-api",
                        "org.apache.deltaspike.core:deltaspike-core-impl",
                        "org.apache.deltaspike.modules:deltaspike-partial-bean-module-api",
                        "org.apache.deltaspike.modules:deltaspike-partial-bean-module-impl",
                        "org.apache.deltaspike.modules:deltaspike-jpa-module-api",
                        "org.apache.deltaspike.modules:deltaspike-jpa-module-impl",
                        "org.apache.deltaspike.modules:deltaspike-data-module-api",
                        "org.apache.deltaspike.modules:deltaspike-data-module-impl",
                        "com.google.guava:guava")
                .withTransitivity()
                .asFile());
        
        System.out.println(war.toString(true));
        
        return war;
        
    }

    @Produces
    @PersistenceContext(name = "tekir")
    @Default
    private EntityManager entityManager;
    
    @Inject
    private ContactDublicationValidator validator;
    
    @Inject
    private ContactRepository contactRepository;
    
    @Before
    public  void setUp(){
        
        
        
    }
    
    @Test
    public void test1() {
        //Test Verisi hazırlayalım.
        Person p = getTestPerson();
                
        contactRepository.save(p);
    }
    
    
    /**
     * Test of getSuspectedContacts method, of class ContactDublicationValidator.
     */
    @Test
    public void testGetSuspectedContacts() {
        System.out.println("getSuspectedContacts");
        Person contact = getTestPerson();
        
        List<Contact> result = validator.getSuspectedContacts(contact);
        
        System.out.println(result);
        
        //Şüpheli Kayıt olması lazım
        Assert.assertFalse( "Şüpheli Kayıt Var!", result.isEmpty());
        
        //Farklı şartlarla ( email, telefon v.s. ) aynı kaydı bulduğunda 1 kere yazacak
        Assert.assertEquals(1, result.size());
        
    }
    
    @Test
    public void testGetSuspectedContactsRevers() {
        System.out.println("com.ozguryazilim.tekir.contact.merge.ContactDublicationValidatorTest.testGetSuspectedContactsRevers()");
        
        Person contact = getTestPerson2();
        
        List<Contact> result = validator.getSuspectedContacts(contact);
        
        System.out.println(result);
        
        //Şüpheli Kayıt olmaması lazım
        Assert.assertTrue( "Şüpheli Kayıt Var!", result.isEmpty());
        
    }

    /**
     * Test of checkByEmail method, of class ContactDublicationValidator.
     */
    @Test
    public void testCheckByEmail() {
        System.out.println("checkByEmail");
        Contact contact = getTestPerson();
        List<Contact> suspecteds = new ArrayList<>();
        validator.checkByEmail(contact, suspecteds);
        
        System.out.println(suspecteds);
        Assert.assertFalse(suspecteds.isEmpty());
        
    }

    /**
     * Test of checkByMobile method, of class ContactDublicationValidator.
     */
    @Test
    public void testCheckByMobile() {
        System.out.println("checkByMobile");
        Contact contact = getTestPerson();
        List<Contact> suspecteds = new ArrayList<>();
        validator.checkByMobile(contact, suspecteds);
        
        System.out.println(suspecteds);
        Assert.assertFalse(suspecteds.isEmpty());
    }

    /**
     * Test of checkByPhone method, of class ContactDublicationValidator.
     */
    @Test
    public void testCheckByPhone() {
        System.out.println("checkByPhone");
        Contact contact = getTestPerson();
        List<Contact> suspecteds = new ArrayList<>();
        validator.checkByPhone(contact, suspecteds);
        
        System.out.println(suspecteds);
        Assert.assertFalse(suspecteds.isEmpty());
    }
    
    @Test
    public void testCheckByPhone2() {
        System.out.println("com.ozguryazilim.tekir.contact.merge.ContactDublicationValidatorTest.testCheckByPhone2()");
        Contact contact = getTestPerson2();
        List<Contact> suspecteds = new ArrayList<>();
        validator.checkByPhone(contact, suspecteds);
        
        
        Assert.assertTrue(suspecteds.isEmpty());
    }
    
    /**
     * Aynı verinin boşluk v.s. ile farklı formatlı hali
     * Şimdilik kapalı. Bu durum henüz desteklenmiyor.
     */
    //@Test
    public void testCheckByPhone3() {
        System.out.println("com.ozguryazilim.tekir.contact.merge.ContactDublicationValidatorTest.testCheckByPhone3()");
        Contact contact = getTestPerson3();
        List<Contact> suspecteds = new ArrayList<>();
        validator.checkByPhone(contact, suspecteds);
        
        
        Assert.assertFalse(suspecteds.isEmpty());
    }
    
    protected Person getTestPerson(){
        Person contact = new Person();
        contact.setCode("TestP1");
        contact.setFirstName("Hakan");
        contact.setFirstName("Uygun");
        contact.setName("Hakan Uygun");
        
        ContactEMail email = new ContactEMail();
        email.setEmailAddress("hakan.uygun@ozgur.com.tr");
        email.setContact(contact);
        
        contact.setPrimaryEmail(email);
        
        ContactPhone mobile = new ContactPhone();
        mobile.setNumber("0588 898 22 44");
        mobile.setContact(contact);
        
        contact.setPrimaryMobile(mobile);
        
        ContactPhone phone = new ContactPhone();
        phone.setNumber("0588 898 22 44");
        phone.setContact(contact);
        
        contact.setPrimaryPhone(phone);
        
        return contact;
    }
    
    protected Person getTestPerson2(){
        Person contact = new Person();
        contact.setFirstName("Doruk");
        contact.setFirstName("Fişek");
        
        ContactEMail email = new ContactEMail();
        email.setEmailAddress("doruk.fisek@ozgur.com.tr");
        email.setContact(contact);
        
        contact.setPrimaryEmail(email);
        
        ContactPhone mobile = new ContactPhone();
        mobile.setNumber("0588 898 22 55");
        mobile.setContact(contact);
        
        contact.setPrimaryMobile(mobile);
        
        ContactPhone phone = new ContactPhone();
        phone.setNumber("0588 898 22 55");
        phone.setContact(contact);
        
        contact.setPrimaryPhone(phone);
        
        return contact;
    }
    
    /**
     * Yazım formatları biraz değişti ama Person1 ile aynı veri.
     * 
     * Bu case'i çözmenin tek yolu format kontrolü yapmaktan geçiyor sanırım.
     * 
     * UI ya da nereden geliyor ise ContactInfo alanlarının mutlaka formatlanması lazım. Ya da bütün veriyi her seferinde elden geçirmek gerekir.
     * Korkunç bir performans sorunu...
     * 
     * @return 
     */
    protected Person getTestPerson3(){
        Person contact = new Person();
        contact.setCode("TestP3");
        contact.setFirstName("Hakan");
        contact.setFirstName("Uygun");
        contact.setName("Hakan Uygun");
        
        ContactEMail email = new ContactEMail();
        email.setEmailAddress("Hakan Uygun <hakan.uygun@ozgur.com.tr>");
        email.setContact(contact);
        
        contact.setPrimaryEmail(email);
        
        ContactPhone mobile = new ContactPhone();
        mobile.setNumber("0588 8982244");
        mobile.setContact(contact);
        
        contact.setPrimaryMobile(mobile);
        
        ContactPhone phone = new ContactPhone();
        phone.setNumber("0588 8982244");
        phone.setContact(contact);
        
        contact.setPrimaryPhone(phone);
        
        return contact;
    }
}
