<#ftl encoding="UTF-8">
<#assign var_link = headers.linkDomain + "/activities/activityView.jsf?eid="+ headers.ActivityId?string["#########"]>
    
Aşağıda bilgileri verilen aktivite kapatılmamıştır.

Kişi            : ${headers.PersonName}
Konusu          : ${headers.ActivitySubject} 
İçeriği         : ${headers.ActivityBody} 
Atanan Kişi     : ${headers.ActivityAssignee}
Aktivite Bağlantısı : ${var_link}


Bilgilerinize,
Tekir Hatırlatma Servisi
