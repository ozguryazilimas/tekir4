<#ftl encoding="UTF-8">

Aşağıda bilgileri verilen satın alım siparişinin gönderim tarihi dolmak üzeredir.

<#list headers.PurchaseOrders as purchaseOrder>
<#assign var_link = headers.linkDomain + "/order/purchase/purchaseOrderBrowse.jsf?eid="+ purchaseOrder.id?string["#########"]>

Sipariş No       : ${purchaseOrder.voucherNo} 
Konusu           : ${purchaseOrder.topic} 
Müşteri          : ${purchaseOrder.account.name} 
Sipariş bağlantısı : ${var_link}

</#list>

Bilgilerinize,
Tekir Hatırlatma Servisi
