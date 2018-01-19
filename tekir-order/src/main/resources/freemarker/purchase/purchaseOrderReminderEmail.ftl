<#ftl encoding="UTF-8">
<#assign var_link = headers.linkDomain + "/order/purchase/purchaseOrderView.jsf?eid="+ headers.VoucherId?string["#########"]>
    
Aşağıda bilgileri verilen satın alım siparişinin gönderim tarihi dolmak üzeredir.

Sipariş No       : ${headers.VoucherNo}
Konusu           : ${headers.VoucherTopic} 
Müşteri          : ${headers.AccountName} 
Sipariş bağlantısı : ${var_link}


Bilgilerinize,
Tekir Hatırlatma Servisi
