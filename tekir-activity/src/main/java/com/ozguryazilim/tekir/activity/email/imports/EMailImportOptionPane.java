package com.ozguryazilim.tekir.activity.email.imports;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.mutfak.kahve.KahveEntry;
import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.telve.config.AbstractOptionPane;
import com.ozguryazilim.telve.config.OptionPane;
import com.ozguryazilim.telve.config.OptionPaneType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author oyas
 */
@OptionPane(permission = "EMailImportOptions", optionPage = ActivityPages.EMailImportOptionPane.class, type = OptionPaneType.System)
public class EMailImportOptionPane extends AbstractOptionPane {

    private static final String MAIL_PROTOCOL = "mail.protocol";
    private static final String MAIL_HOST = "mail.host";
    private static final String MAIL_PORT = "mail.port";
    private static final String MAIL_USER = "mail.user";
    private static final String MAIL_PASS = "mail.pass";
    private static final String MAIL_SSL = "mail.ssl";
    private static final String MAIL_INBOX_FOLDER = "mail.inbox.inboxFolder";
    private static final String MAIL_OUTBOX_FOLDER = "mail.outbox.inboxFolder";

    @Inject
    private Kahve kahve;

    private KahveEntry protocol;
    private KahveEntry host;
    private KahveEntry port;
    private KahveEntry user;
    private KahveEntry pass;
    private KahveEntry ssl;
    private KahveEntry inboxFolder;
    private KahveEntry outboxFolder;


    @PostConstruct
    public void init() {
        protocol = kahve.get(MAIL_PROTOCOL, "imap");
        host = kahve.get(MAIL_HOST, "");
        port = kahve.get(MAIL_PORT, "993");
        user = kahve.get(MAIL_USER, "");
        pass = kahve.get(MAIL_PASS, "");
        ssl = kahve.get(MAIL_SSL, "true");
        inboxFolder = kahve.get(MAIL_INBOX_FOLDER, "INBOX");
        outboxFolder = kahve.get(MAIL_OUTBOX_FOLDER, "SENT");
    }

    @Override
    public void save() {

        kahve.put(MAIL_PROTOCOL, protocol);
        kahve.put(MAIL_HOST, host);
        kahve.put(MAIL_PORT, port);
        kahve.put(MAIL_USER, user);
        kahve.put(MAIL_PASS, pass);
        kahve.put(MAIL_SSL, ssl);
        kahve.put(MAIL_INBOX_FOLDER, inboxFolder);
        kahve.put(MAIL_OUTBOX_FOLDER, outboxFolder);

    }

    public KahveEntry getProtocol() {
        return protocol;
    }

    public KahveEntry getHost() {
        return host;
    }

    public KahveEntry getPort() {
        return port;
    }

    public KahveEntry getUser() {
        return user;
    }

    public KahveEntry getPass() {
        return pass;
    }

    public KahveEntry getSsl() {
        return ssl;
    }

    public KahveEntry getInboxFolder() {
        return inboxFolder;
    }

    public KahveEntry getOutboxFolder() {
        return outboxFolder;
    }
}
