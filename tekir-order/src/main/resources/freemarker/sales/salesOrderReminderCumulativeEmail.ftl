<#ftl encoding="UTF-8">

Aşağıda bilgileri verilen satış siparişinin gönderim tarihi dolmak üzeredir.

<#list headers.SalesOrders as salesOrder>
<#assign var_link = headers.linkDomain + "/order/sales/salesOrderBrowse.jsf?eid="+ salesOrder.id?string["#########"]>

Sipariş No       : ${salesOrder.voucherNo} 
Konusu           : ${salesOrder.topic} 
Müşteri          : ${salesOrder.account.name} 
Sipariş bağlantısı : ${var_link}

</#list>

Bilgilerinize,
Tekir Hatırlatma Servisi
