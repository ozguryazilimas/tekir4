<#ftl encoding="UTF-8">
<#assign var_link = headers.linkDomain + "/lead/leadView.jsf?eid="+ headers.VoucherId?string["#########"]>
    
Aşağıda bilgileri verilen ipucu kapatılmamıştır.

İpucu No            : ${headers.VoucherNo} 
Konusu              : ${headers.VoucherTopic} 
Kişi Adı            : ${headers.RelatedPersonName}
Kişi Soyadı         : ${headers.RelatedPersonSurname}
İpucunun bağlantısı : ${var_link}


Bilgilerinize,
Tekir Hatırlatma Servisi
