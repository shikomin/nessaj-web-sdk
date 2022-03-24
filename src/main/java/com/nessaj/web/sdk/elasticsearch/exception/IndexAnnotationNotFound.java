package com.nessaj.web.sdk.elasticsearch.exception;

/**
 * Created by 胥珂铭 on 2022/3/24.
 */
public class IndexAnnotationNotFound extends Exception {

    private Class<?> clazz;

    private StringBuilder msg = new StringBuilder("index annotation not found in this class: ");

    public IndexAnnotationNotFound(Class<?> clazz) {
        this.clazz = clazz;
        msg = msg.append(clazz.getName());
    }

}
