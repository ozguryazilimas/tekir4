package com.ozguryazilim.tekir;

import com.cs.csap.service.ConnectorService;
import com.cs.csap.service.UserService;
import tr.com.cs.uut.connector.service.GidenBelgeDurum;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.util.Collection;
import java.util.TreeMap;

public class Sorgula {
    public static void main(String[] args) {
        UserService service = new UserService();
        ConnectorService connectorService = new ConnectorService();

        tr.com.cs.csap.service.UserService client = service.getUserServicePort();
        tr.com.cs.uut.connector.service.ConnectorService connector = connectorService.getConnectorServicePort();

        client.wsLogin("ozguryazilim.gonderici", "12345678aB", "tr");

        BindingProvider bp = ((BindingProvider) client);
        BindingProvider bpc = ((BindingProvider) connector);

        bpc.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

        TreeMap<String, Collection> headers = (TreeMap<String, Collection>) bp.getResponseContext()
                .get(MessageContext.HTTP_RESPONSE_HEADERS);

        Collection cookie = headers.get("Set-Cookie");
        headers.put("Cookie", cookie);
        bpc.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        String belgeOidOld = "0njbc3smzm11k8";
        GidenBelgeDurum status = connector.gidenBelgeDurumSorgula("6930329621", belgeOidOld);
        System.out.println(status.getDurum());
        System.out.println(status.getAciklama());
        System.out.println(status.getGonderimDurumu());
        System.out.println(status.getGonderimCevabiKodu());
        System.out.println(status.getGonderimCevabiDetayi());
        System.out.println(status.getYanitDurumu());
        System.out.println(status.getYanitDetayi());
        System.out.println(status.getYanitTarihi());
        System.out.println(status.getOlusturulmaTarihi());
        System.out.println(status.getAlimTarihi());
        System.out.println(status.getGonderimTarihi());
        System.out.println(status.getEttn());
        System.out.println(status.getBelgeNo());
    }
}
