package com.ozguryazilim.tekir.einvoice;

import com.cs.csap.service.ConnectorService;
import com.cs.csap.service.UserService;
import com.ozguryazilim.tekir.entities.EinvoiceStatus;
import com.ozguryazilim.tekir.entities.SalesEinvoice;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.telve.messages.FacesMessages;

import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.TreeMap;

/**
 * File, satici VKN ve belgeNo degerlerini alir.
 * UserService ile login olur ve cookie alir. Ardindan bu cookieyi ConnectorService'e uygular.
 * Ardindan yollanacak dosyanin bytesArray'i ve hash'i alinir ve servise yollanir.
 *
 * @author soner.cirit
 * @return Efatura islemlerini takip etmek icin gerekli olan belgeOid'yi doner.
 */
public class EinvoiceSender {

    public String sendEinvoice(File file, String saticiVKN, SalesInvoice entity, String username, String password,
                               EinvoiceRepository einvoiceRepository) throws Exception {
        UserService service = new UserService();
        ConnectorService connectorService = new ConnectorService();

        tr.com.cs.csap.service.UserService client = service.getUserServicePort();
        tr.com.cs.uut.connector.service.ConnectorService connector = connectorService.getConnectorServicePort();

        client.wsLogin(username, password, "tr");

        BindingProvider bp = ((BindingProvider) client);
        BindingProvider bpc = ((BindingProvider) connector);

        bpc.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

        TreeMap<String, Collection> headers = (TreeMap<String, Collection>) bp.getResponseContext().get
                (MessageContext.HTTP_RESPONSE_HEADERS);

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
        SalesEinvoice einvoice = new SalesEinvoice();

        try {
            String belgeOid = connector.belgeGonder(saticiVKN, "FATURA", entity.getVoucherNo(), bytesArray,
                    hashString, "application/xml", "3.0");

            einvoice.setInvoice(entity);
            einvoice.setEinvoiceCode(belgeOid);
            einvoice.setEinvoiceStatus(EinvoiceStatus.SENT);
            einvoiceRepository.save(einvoice);
            return "SUCCESS";
        } catch (SOAPFaultException s) {
            einvoice.setInvoice(entity);
            einvoice.setReturnedMessage(String.valueOf(s));
            einvoice.setEinvoiceStatus(EinvoiceStatus.FAILED);
            einvoiceRepository.save(einvoice);
            return String.valueOf(s);
        }
    }
}