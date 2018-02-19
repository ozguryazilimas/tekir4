/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * E-Posta gönderim activitesi
 *
 * TODO: Aslında burada karar verilmesi gereken bazı şeyler var. Örneğin : -
 * E-posta istemcisi ile entegrasyon. - Bu verilerin cc/bcc üzerinden gelecek
 * bir adresi dinleyip otomatik eklenmesi - Uygulama içinden e-posta göndermek?
 *
 * FIXME: Şimdilik tablo kısmını pass mı geçsek?
 *
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("EMAIL")
public class EMailActivity extends Activity {

//    /**
//     * İletişim için kullanılan/kullanılacak olan e-posta adresi
//     */
//    @Column(name = "EMAIL")
//    private String email;
//    
    //E-posta üzerindeki adres bilgileri
    @Column(name = "EM_FROM")
    private String from;
    @Column(name = "EM_TO")
    private String to;
    @Column(name = "EM_CC")
    private String cc;
    @Column(name = "EM_BCC")
    private String bcc;

    @Column(name = "EM_RID")
    private String replyId;
    
    @Column(name = "EM_FID")
    private String forwardId;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getForwardId() {
        return forwardId;
    }

    public void setForwardId(String forwardId) {
        this.forwardId = forwardId;
    }
}
