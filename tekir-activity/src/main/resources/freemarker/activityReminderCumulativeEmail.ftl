<#ftl encoding="UTF-8">

Aşağıda bilgileri verilen aktiviteler kapatılmamıştır.

<#list headers.Activities as activity>
<#assign var_link = headers.linkDomain + "/activities/activityView.jsf?eid="+ activity.id?string["#########"]>

Kişi            : ${activity.person.name}
Konusu          : ${activity.subject} 
İçeriği         : ${activity.body} 
Atanan Kişi     : ${activity.assignee}
Aktivite Bağlantısı : ${var_link}

</#list>

Bilgilerinize,
Tekir Hatırlatma Servisi
