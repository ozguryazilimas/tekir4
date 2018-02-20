package com.ozguryazilim.tekir.einvoice;

import com.cs.csap.service.ConnectorService;
import com.cs.csap.service.UserService;

import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.TreeMap;

/**
 * File, satici VKN ve belgeNo degerlerini alir.
 * UserService ile login olur ve cookie alir. Ardindan bu cookieyi ConnectorService'e uygular.
 * Ardindan yollanacak dosyanin bytesArray'i ve hash'i alinir ve servise yollanir.
 * @return Efatura islemlerini takip etmek icin gerekli olan belgeOid'yi doner.
 * @author soner.cirit
 */
public class EinvoiceSender {

    public String sendEinvoice(File file, String saticiVKN, String belgeNo) throws Exception{
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

        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytesArray);
        byte[] hash = messageDigest.digest();
        String hashString = DatatypeConverter.printHexBinary(hash).toUpperCase();

        return connector.belgeGonder(saticiVKN, "FATURA", belgeNo, bytesArray, hashString, "application/xml", "3.0");
    }
}
