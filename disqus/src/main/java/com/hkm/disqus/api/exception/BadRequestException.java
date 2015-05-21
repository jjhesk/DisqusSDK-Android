package com.hkm.disqus.api.exception;

/**
 * Bad request
 */
public class BadRequestException extends ApiException {

    public BadRequestException() {
    }

    public BadRequestException(String detailMessage) {
        super(detailMessage);
    }

    public BadRequestException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BadRequestException(Throwable throwable) {
        super(throwable);
    }

}
