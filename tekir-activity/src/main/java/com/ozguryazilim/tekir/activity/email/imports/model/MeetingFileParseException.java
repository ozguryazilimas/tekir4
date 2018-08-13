package com.ozguryazilim.tekir.activity.email.imports.model;

public class MeetingFileParseException extends Exception {

    public MeetingFileParseException() {
    }

    public MeetingFileParseException(String message) {
        super(message);
    }

    public MeetingFileParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeetingFileParseException(Throwable cause) {
        super(cause);
    }

    public MeetingFileParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
