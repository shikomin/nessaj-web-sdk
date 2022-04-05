package com.nessaj.sdk.httpclient.common.exception;

/**
 * @author keming
 * @Date 2022/03/10 16:03
 */
public class NullFileNameException extends Exception {

    /**
     * Constructs a {@code NullPointerException} with no detail message.
     */
    public NullFileNameException() {
        super();
    }

    /**
     * Constructs a {@code NullPointerException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public NullFileNameException(String s) {
        super(s);
    }
}
