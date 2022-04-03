package com.nessaj.web.sdk.common.exception;

/**
 * @author keming
 * @Date 2022/04/03 22:11
 */
public class UnzipFileException extends Exception {

    private String message;

    public UnzipFileException() {
        this("fail to unzip file");
    }

    public UnzipFileException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
