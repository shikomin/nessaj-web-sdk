package com.nessaj.sdk.common.exception;

/**
 * Created by 胥珂铭 on 2022/3/24.
 */
public class IndexAnnotationNotFound extends Exception {

    private String msg = "index annotation not found in this class: ";

    public IndexAnnotationNotFound(Class<?> clazz) {
        msg = msg + clazz.getName();
    }

}
