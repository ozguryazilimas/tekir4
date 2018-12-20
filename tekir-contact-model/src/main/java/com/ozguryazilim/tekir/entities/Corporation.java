package com.ozguryazilim.tekir.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Tüzel Kişi 
 * 
 * Şirket, Organizasyon, Kurum v.s.
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("CORPORATION")
public class Corporation extends Contact{
    
    //Kurumsal contactlar için kurum tam ünvanı
    @Column(name = "ORG_NAME")
    private String organizastionName;
    
    /**
     * Şirketler için ana bağlantı. İletişim varsayılan olarak bununla yapılır.
     * Kişiler için şirket bağlantısı. ( Olmayanlar için yukarıdaki companyName kullanılabilir.
     */
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID", foreignKey = @ForeignKey(name = "FK_CORP_PER"))
    private Person primaryContact;
    
    /**
     * Eğer bir birine bağlı kurumsal hesaplarsa
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", foreignKey = @ForeignKey(name = "FK_CORP_PARENT"))
    private Corporation parentCorporation;
    
    /**
     * Kurum tipi : 
     * 
     * Özel, Kamu, STK, Bilişim, Entegrator? Bunları bir tanım Tablosundan mı alsak?
     */
    @ManyToOne
    @JoinColumn(name = "TYPE_ID", foreignKey = @ForeignKey(name = "FK_CORP_TYPE"))
    private CorporationType corporationType;

    public String getOrganizastionName() {
        return organizastionName;
    }

    public void setOrganizastionName(String organizastionName) {
        this.organizastionName = organizastionName;
    }

    public Person getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(Person primaryContact) {
        this.primaryContact = primaryContact;
    }

    public Corporation getParentCorporation() {
        return parentCorporation;
    }

    public void setParentCorporation(Corporation parentCorporation) {
        this.parentCorporation = parentCorporation;
    }

    public CorporationType getCorporationType() {
        return corporationType;
    }

    public void setCorporationType(CorporationType corporationType) {
        this.corporationType = corporationType;
    }
    
    
            
}
