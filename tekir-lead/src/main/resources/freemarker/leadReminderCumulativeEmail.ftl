<#ftl encoding="UTF-8">

Aşağıda bilgileri verilen ipuçları kapatılmamıştır.

<#list headers.Leads as lead>
<#assign var_link = headers.linkDomain + "/lead/leadView.jsf?eid="+ lead.id?string["#########"]>

İpucu No         : ${lead.voucherNo} 
Konusu           : ${lead.topic} 
Kişi Adı         : ${lead.relatedPersonName}
Kişi Soyadı      : ${lead.relatedPersonSurname} 
İpucu bağlantısı : ${var_link}

</#list>

Bilgilerinize,
Tekir Hatırlatma Servisi
