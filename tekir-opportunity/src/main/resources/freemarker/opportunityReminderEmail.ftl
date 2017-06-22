<#ftl encoding="UTF-8">
<#assign var_link = headers.linkDomain + "/opportunity/opportunityView.jsf?eid="+ headers.VoucherId?string["#########"]>
    
Aşağıda bilgileri verilen fırsat kapatılmamıştır.

Fırsat No       : ${headers.VoucherNo} 
Konusu          : ${headers.VoucherTopic} 
Müşteri         : ${headers.AccountName} 
Fırsat Bağlantısı : ${var_link}


Bilgilerinize,
Tekir Hatırlatma Servisi
