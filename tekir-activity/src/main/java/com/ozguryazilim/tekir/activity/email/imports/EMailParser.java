/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parametre olarak alınan e-postayı parse eder.
 *
 * - Eposta'dan mümkünse Text metni ve attachmentları çıkarır. HTML ve Gömülü
 * resimleri safdışı eder. 
 * 
 * - E-posta header'larından bu e-postanın bir cevap
 * olup olmadığını ve cevap ise orjinal id'leri ayıklar 
 * 
 * - E-posta eğer bir Forward ise içinden orjinal olanı bulmaya çalışır. Orjnalin ID'sini de yakalar
 * 
 * - Reply e-postaların içinden reply kısmını bulup sadece onu döndürebilir?
 *
 * @author Hakan Uygun
 */
public class EMailParser {

    private static final Logger LOG = LoggerFactory.getLogger(EMailParser.class);

    public void parse(String messages) throws MessagingException, IOException {
        parse(new ByteArrayInputStream(messages.getBytes()));
    }

    public void parse(InputStream messageStream) throws MessagingException, IOException {

        LOG.info("Stream Parse Begin");

        Session s = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(s, messageStream);

        LOG.info("{}", message);

        message.getAllHeaderLines();
        for (Enumeration<Header> e = message.getAllHeaders(); e.hasMoreElements();) {
            Header h = e.nextElement();
            LOG.info("{}:{}", h.getName(), h.getValue());

        }

        LOG.info("Subject: {}", message.getSubject());

        LOG.info("Content-Type: {}", message.getContentType());

        Object content = message.getContent();
        LOG.info("Content-Type-Class: {}", content.getClass().getName());
        if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;

            parseMultiPart(multipart);
        } else if( content instanceof String ){
            parsePlain((String) content);
        }

    }

    protected void parseMultiPart(Multipart multipart) throws MessagingException, IOException {
        int count = multipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart part = multipart.getBodyPart(i);
            LOG.info("Content-Type: {}", part.getContentType());
            if (part.isMimeType("text/plain")) {
                String content = (String) part.getContent();
                parsePlain(content);

            } else if (part.isMimeType("text/html")) {
                String html = (String) part.getContent();
                parseHtml(html);
            } else if (part.isMimeType("multipart/*")) {
                LOG.info("Part-Type-Class: {}", part.getContent().getClass().getName());
                parseMultiPart((Multipart) part.getContent());
            }

            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                LOG.info("File : {}", part.getFileName());
            }

        }
    }

    protected void parsePlain(String content) throws MessagingException, IOException {
        LOG.info("Content: {}", content);
        LOG.info("Reply Content: {}", EMailReplyParser.parse(content));
    }

    protected void parseHtml(String html) throws MessagingException, IOException {
        // get pretty printed html with preserved br and p tags
        String prettyPrintedBodyFragment = Jsoup.clean(html, "", Whitelist.none().addTags("br", "p"), new OutputSettings().prettyPrint(true));
        // get plain text with preserved line breaks by disabled prettyPrint
        String result = Jsoup.clean(prettyPrintedBodyFragment, "", Whitelist.none(), new OutputSettings().prettyPrint(false));

        result = Jsoup.clean(html, Whitelist.basicWithImages());

        //LOG.info("Content: {}", result);
    }
}
