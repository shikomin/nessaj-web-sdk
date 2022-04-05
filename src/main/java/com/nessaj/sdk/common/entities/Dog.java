package com.nessaj.sdk.common.entities;

import com.nessaj.sdk.elasticsearch.annotation.ElasticType;
import com.nessaj.sdk.elasticsearch.annotation.Index;
import com.nessaj.sdk.elasticsearch.annotation.Type;

import java.util.Date;

/**
 * @author keming
 * @Date 2022/03/24 21:39
 */
@Index(name = "dog")
public class Dog {

    @Type
    private String name;

    @Type(type = ElasticType.LONG)
    private long age;

    @Type(type = ElasticType.DATE)
    private Date birthday;

}
