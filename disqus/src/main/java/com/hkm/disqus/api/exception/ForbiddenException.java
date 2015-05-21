package com.hkm.disqus.api.exception;

/**
 * Forbidden exception
 */
public class ForbiddenException extends ApiException {

    public ForbiddenException() {
    }

    public ForbiddenException(String detailMessage) {
        super(detailMessage);
    }

    public ForbiddenException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ForbiddenException(Throwable throwable) {
        super(throwable);
    }

}
