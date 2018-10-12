package com.ozguryazilim.tekir.activity.email.imports.model;

import net.fortuna.ical4j.data.CalendarBuilder;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import java.io.IOException;
import java.io.StringReader;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

/**
 * *.ics dosyasını temsil eder. EmailAttacment ile temsil edilen dosyanın charset'e göre parse edilmiş halidir
 */
public class MeetingFile {

    private EMailAttacment attacment;
    private Calendar calendar;

    public MeetingFile(EMailAttacment attacment) {
        this.attacment = attacment;
    }

    public EMailAttacment getAttacment() {
        return attacment;
    }

    /**
     * Dosya içeriğini parse ederek Calendar nesnesi döndürür.
     *
     * @return Calendar
     */
    public Calendar getCalendar() throws MeetingFileParseException {
        if (calendar == null) {
            try {
                MimeType mimeType = new MimeType(attacment.getMimeType());
                String charset = mimeType.getParameter("charset");
                if (charset == null) {
                    charset = "utf-8";
                }

                String content = new String(attacment.getContent(), charset);

                CalendarBuilder builder = new CalendarBuilder();
                calendar = builder.build(new StringReader(content));
            } catch (MimeTypeParseException | IOException | ParserException ex) {
                throw new MeetingFileParseException(ex);
            }
        }
        return calendar;
    }
}
