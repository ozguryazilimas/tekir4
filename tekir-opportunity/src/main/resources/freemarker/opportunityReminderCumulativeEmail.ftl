<#ftl encoding="UTF-8">

Aşağıda bilgileri verilen fırsatlar kapatılmamıştır.

<#list headers.Opportunities as opportunity>
<#assign var_link = headers.linkDomain + "/opportunity/opportunityView.jsf?eid="+ opportunity.id?string["#########"]>

Fırsat No       : ${opportunity.voucherNo} 
Konusu          : ${opportunity.topic} 
Müşteri         : ${opportunity.account.name} 
Fırsat bağlantısı : ${var_link}

</#list>

Bilgilerinize,
Tekir Hatırlatma Servisi
