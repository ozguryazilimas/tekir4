/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports.commands;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import com.ozguryazilim.telve.messagebus.command.CommandSender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.inject.Inject;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bu Command ve Executor'u sadece e-posta bağlantısını yapıp okunmamış
 * e-postaları alarak ayrı bir kuyruğa ekler.
 *
 * @author Hakan Uygun
 */
@CommandExecutor(command = EMailFetchCommand.class)
public class EMailFetchCommandExecutor extends AbstractCommandExecuter<EMailFetchCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(EMailFetchCommandExecutor.class);


    private static final String MAIL_PROTOCOL = "mail.protocol";
    private static final String MAIL_HOST = "mail.host";
    private static final String MAIL_PORT = "mail.port";
    private static final String MAIL_USER = "mail.user";
    private static final String MAIL_PASS = "mail.pass";
    private static final String MAIL_SSL = "mail.ssl";
    private static final String MAIL_INBOX_FOLDER = "mail.inbox.folder";
    private static final String MAIL_OUTBOX_FOLDER = "mail.outbox.folder";

    @Inject
    private Kahve kahve;

    @Inject
    private CommandSender commandSender;

    @Override
    public void execute(EMailFetchCommand command) {

        if (kahve.get(MAIL_PROTOCOL) == null) {
            LOG.error("EMail Configuration Does Not Complete");
            return;
        }

        LOG.debug("EMail Import Process Begin");
        try {
            //Burada kahveden okuma yapılacak
            Store store = getSession().getStore("imaps");

            //FIXME: parola kahvede hashli saklanacak
            String host = kahve.get(MAIL_HOST, "mail.host").getAsString();
            String user = kahve.get(MAIL_USER, "user").getAsString();
            String pass = kahve.get(MAIL_PASS, "pass").getAsString();
            int port = kahve.get(MAIL_PORT, "993").getAsInteger();
            store.connect(host, port, user, pass);

            String inboxFolder = kahve.get(MAIL_INBOX_FOLDER, "INBOX").getAsString();
            String outboxFolder = kahve.get(MAIL_OUTBOX_FOLDER, "SENT").getAsString();

            processFolder(store, inboxFolder, EmailDirection.INBOX);
            processFolder(store, outboxFolder, EmailDirection.OUTBOX);

            store.close();
        } catch (MessagingException ex) {
            LOG.error("EMail Import Failed", ex);
        }
    }

    /**
     * @param store
     * @param folder
     * @param direction
     */
    protected void processFolder(Store store, String folder, EmailDirection direction) {
        try {
            Folder emailFolder = store.getFolder(folder);

            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            LOG.info("messages.length : {}", messages.length);

            for (Message message : messages) {

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                message.writeTo(out);
                String eml = out.toString();
                LOG.trace("Message : {}", eml);

                EMailImportCommand importCommand = new EMailImportCommand();
                importCommand.setEml(eml);
                importCommand.setDirection(direction);

                commandSender.sendCommand(importCommand);

                //FIXME: Burada IMAP ise bir arşiv folder'ına atılacak. POP3 ise silinecek. IMAP içinde bunu ayarlardan almak lazım
                //message.setFlag(Flags.Flag.DELETED, true);
                //folder.expunge();
            }

            // close the store and folder objects
            emailFolder.close(false);
        } catch (MessagingException | IOException ex) {
            LOG.error("EMail Import Failed", ex);
        }
    }

    /**
     * Kahve'ye girilmiş bilgiler üzeriden EMail sesssion açacak.
     * <p>
     * Burada aslında farklı doğrulama biçimleri v.b. için sunucudan mı alsak?
     * Ama o taktirde de e-posta ayarlarının kurulumu yapan sistem yöneticisi
     * tarafından yapılması gerekiyor.
     *
     * @return
     */
    protected Session getSession() {

        String protocol = kahve.get(MAIL_PROTOCOL, "imap").getAsString();

        Properties properties = new Properties();
        properties.put("mail.store.protocol", protocol);
        properties.put("mail." + protocol + ".host", kahve.get(MAIL_HOST, "mail.host").getAsString());
        properties.put("mail." + protocol + ".port", kahve.get(MAIL_PORT, "993").getAsString());
        properties.put("mail." + protocol + ".starttls.enable", kahve.get(MAIL_SSL, "true").getAsString());
        properties.put("mail.imap.connectiontimeout", "1000");

        Session emailSession = Session.getDefaultInstance(properties);
        // emailSession.setDebug(true);
        return emailSession;
    }
}
