package com.nessaj.web.sdk.elasticsearch.interfaces;

/**
 * @author keming
 * @Date 2022/03/23 22:04
 */
public interface IndexMapping<T> {

    String MAPPINGS_PATH = "/mappings/";
    String MAPPINGS_SUFFIX = ".json";

    T getMapping(Class<?> clazz);

}
