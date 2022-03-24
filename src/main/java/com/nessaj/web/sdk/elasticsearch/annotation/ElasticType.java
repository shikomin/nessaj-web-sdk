package com.nessaj.web.sdk.elasticsearch.annotation;

/**
 * @author keming
 * @Date 2022/03/23 22:20
 */
public enum ElasticType {

    TEXT("text"), LONG("long"), DATE("date");

    private String name;

    ElasticType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
