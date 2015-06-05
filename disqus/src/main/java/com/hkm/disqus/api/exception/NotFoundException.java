package com.hkm.disqus.api.exception;

/**
 * Created by hesk on 5/6/15.
 */
public class NotFoundException extends Exception {

    public NotFoundException() {
    }

    public NotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public NotFoundException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }

}
