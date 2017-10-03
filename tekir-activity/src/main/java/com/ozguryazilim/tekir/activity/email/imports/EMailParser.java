/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports;

import com.ozguryazilim.tekir.activity.email.imports.model.EMailMessage;
import com.ozguryazilim.tekir.activity.email.imports.model.EMailAttacment;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parametre olarak alınan e-postayı parse eder.
 *
 * - Eposta'dan mümkünse Text metni ve attachmentları çıkarır. HTML ve Gömülü
 * resimleri safdışı eder.
 *
 * - E-posta header'larından bu e-postanın bir cevap olup olmadığını ve cevap
 * ise orjinal id'leri ayıklar
 *
 * - E-posta eğer bir Forward ise içinden orjinal olanı bulmaya çalışır.
 * Orjnalin ID'sini de yakalar
 *
 * - Reply e-postaların içinden reply kısmını bulup sadece onu döndürebilir?
 *
 * @author Hakan Uygun
 */
public class EMailParser {

    private static final Logger LOG = LoggerFactory.getLogger(EMailParser.class);

    private EMailMessage result;

    public EMailMessage parse(String messages) throws MessagingException, IOException {
        return parse(new ByteArrayInputStream(messages.getBytes()));
    }

    public EMailMessage parse(InputStream messageStream) throws MessagingException, IOException {

        result = new EMailMessage();

        LOG.info("Stream Parse Begin");

        Session s = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(s, messageStream);

        //if( LOG.isDebugEnabled() ){
        for (Enumeration<Header> e = message.getAllHeaders(); e.hasMoreElements();) {
            Header h = e.nextElement();
            LOG.info("{}:{}", h.getName(), h.getValue());
        }
        //}

        result.setMessageId(message.getMessageID());
        result.setSubject(message.getSubject());
        result.setDate(message.getReceivedDate());

        parseReplyId(message);
        parseForwardId(message);
        parseRefrences(message);

        parseFrom(message);
        parseTo(message);
        parseCC(message);
        parseBCC(message);

        parseContent(message);

        return result;
    }

    protected void parseFrom(MimeMessage message) throws MessagingException {
        //From bir tane mi olacak?
        Address[] from = message.getFrom();
        if (from != null && from.length > 0) {
            LOG.info("Address: {}", from[0]);
            result.setFrom(new InternetAddress(from[0].toString()));
        }
    }

    protected void parseTo(MimeMessage message) throws MessagingException {

        Address[] toa = message.getRecipients(Message.RecipientType.TO);
        if (toa != null) {
            for (Address adr : toa) {
                LOG.info("Address: {}", adr);
                result.getToList().add(new InternetAddress(adr.toString()));
            }
        }
    }

    protected void parseCC(MimeMessage message) throws MessagingException {

        Address[] toa = message.getRecipients(Message.RecipientType.CC);
        if (toa != null) {
            for (Address adr : toa) {
                LOG.info("Address: {}", adr);
                result.getCcList().add(new InternetAddress(adr.toString()));
            }
        }
    }

    protected void parseBCC(MimeMessage message) throws MessagingException {

        Address[] toa = message.getRecipients(Message.RecipientType.BCC);
        if (toa != null) {
            for (Address adr : toa) {
                LOG.info("Address: {}", adr);
                result.getBccList().add(new InternetAddress(adr.toString()));
            }
        }
    }

    protected void parseReplyId(MimeMessage message) throws MessagingException {
        //Eğer bir cevap e-postası ise kime cevap olduğunun id'si
        String[] headers = message.getHeader("In-Reply-To");
        if (headers != null && headers.length > 0) {
            result.setReplyId(headers[0]);
        }
    }

    protected void parseForwardId(MimeMessage message) throws MessagingException {
        //Eğer bir forward e-postası ise kime neyin forward edildiği id'si
        String[] headers = message.getHeader("X-Forwarded-Message-Id");
        if (headers != null && headers.length > 0) {
            result.setForwardId(headers[0]);
        }
    }

    protected void parseRefrences(MimeMessage message) throws MessagingException {
        //Eğer bir forward e-postası ise kime neyin forward edildiği id'si
        String[] headers = message.getHeader("References");
        if (headers != null && headers.length > 0) {
            String tmp = headers[0];
            tmp = tmp.replaceAll("\t", " ");
            tmp = tmp.replaceAll("\n", " ");
            tmp = tmp.replaceAll("\r", " ");
            List<String> ls = Splitter.on(' ').trimResults().omitEmptyStrings().splitToList(tmp);
            LOG.info("References: {}", ls);
            result.getReferences().addAll(ls);
        }
    }

    protected void parseContent(Part part) throws MessagingException, IOException {

        LOG.info("Content-Type: {}", part.getContentType());

        //Eğer Attachment değil ise normal parse, inline olanları almıyoruz. 
        //Bazen Dispostion denmeden filename ile attachment veriliyormuş.
        if (!Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) && Strings.isNullOrEmpty(part.getFileName())) {
            if (part.isMimeType("multipart/*")) {
                parseMultiPart((Multipart) part.getContent());
            } else if (part.isMimeType("text/plain")) {
                String content = (String) part.getContent();
                parsePlain(content);

            } else if (part.isMimeType("text/html")) {
                String html = (String) part.getContent();
                parseHtml(html);
            }
        } else {
            parseAttachment(part);
        }

    }

    protected void parseMultiPart(Multipart multipart) throws MessagingException, IOException {
        int count = multipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart part = multipart.getBodyPart(i);
            parseContent(part);
        }
    }

    protected void parsePlain(String content) throws MessagingException, IOException {
        LOG.debug("Content: {}", content);
        if( result.isReply()){
            LOG.info("Reply Content: {}", EMailReplyParser.parse(content));
        }
        result.setContent(content);
    }

    protected void parseHtml(String html) throws MessagingException, IOException {
        //Eğer daha önce Text hali alınmamış ise
        if (Strings.isNullOrEmpty(result.getContent())) {
            // get pretty printed html with preserved br and p tags
            //String prettyPrintedBodyFragment = Jsoup.clean(html, "", Whitelist.none().addTags("br", "p"), new OutputSettings().prettyPrint(true));
            // get plain text with preserved line breaks by disabled prettyPrint
            //String text = Jsoup.clean(prettyPrintedBodyFragment, "", Whitelist.none(), new OutputSettings().prettyPrint(false));

            String text = Jsoup.clean(html, Whitelist.basic());

            result.setContent(text);
        }
    }

    protected void parseAttachment(Part part) throws MessagingException, IOException {

        //Inline olanları istemiyoruz. Onlar HTML için
        if (!Part.INLINE.equalsIgnoreCase(part.getDisposition())) {
            EMailAttacment attachment = new EMailAttacment();
            //ContentType mime peşinden isim içeriyor
            String mimeType = part.getContentType().substring(0, part.getContentType().indexOf(";"));
            LOG.info("File : {}, MIME-Type: {}", part.getFileName(), mimeType);

            attachment.setName(part.getFileName());
            attachment.setMimeType(mimeType);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(part.getInputStream(), out);

            attachment.setContent(out.toByteArray());
            attachment.setSize(out.size());

            result.getAttachments().add(attachment);
        }

    }
}
