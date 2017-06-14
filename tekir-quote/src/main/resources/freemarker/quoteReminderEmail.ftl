<#ftl encoding="UTF-8">
<#assign var_link = headers.linkDomain + "//quote/quoteView.jsf?eid="+ headers.VoucherId?string["#########"]>
    
Aşağıda bilgileri verilen teklifin geçerlilik tarihi dolmak üzeredir.

Teklif No       : ${headers.VoucherNo} 
Konusu          : ${headers.VoucherTopic} 
Müşteri         : ${headers.AccountName} 
Tekilif bağlantısı : ${var_link}


Bilgilerinize,
Tekir Hatırlatma Servisi
