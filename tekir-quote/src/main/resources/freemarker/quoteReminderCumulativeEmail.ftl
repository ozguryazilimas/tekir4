<#ftl encoding="UTF-8">

Aşağıda bilgileri verilen tekliflerin geçerlilik tarihi dolmak üzeredir.

<#list headers.Quotes as quote>
<#assign var_link = headers.linkDomain + "/quote/quoteView.jsf?eid="+ quote.id?string["#########"]>

Teklif No       : ${quote.voucherNo} 
Konusu          : ${quote.topic} 
Müşteri         : ${quote.account.name} 
Tekilif bağlantısı : ${var_link}

</#list>

Bilgilerinize,
Tekir Hatırlatma Servisi
