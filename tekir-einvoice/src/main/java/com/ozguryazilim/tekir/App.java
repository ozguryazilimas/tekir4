package com.ozguryazilim.tekir;

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

public class App {
    public static void main(String[] args) throws Exception {

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

        File file = new File("tekir-einvoice/src/main/resources/calisanTaslakTest.xml");
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();

        String bytesArrayString = new String(bytesArray);
        System.out.println(bytesArrayString);

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytesArray);
        byte[] hash = messageDigest.digest();
        String hashString = DatatypeConverter.printHexBinary(hash).toUpperCase();

        String belgeOid = connector.belgeGonder("6930329621", "FATURA", "9", bytesArray, hashString, "application/xml", "3.0");
        System.out.println(belgeOid);

    }
}
