package com.nessaj.web.sdk.elasticsearch.entities;

import com.nessaj.web.sdk.elasticsearch.annotation.ElasticType;
import com.nessaj.web.sdk.elasticsearch.annotation.Index;
import com.nessaj.web.sdk.elasticsearch.annotation.Type;

import java.util.Date;

/**
 * this is a example for you to learn how to create a elasticsearch-index-entity. XD
 *
 * @author keming
 * @Date 2022/03/23 22:12
 */
@Index(name = "cat")
public class Cat {

    @Type
    private String name;

    @Type(type = ElasticType.LONG)
    private String age;

    @Type
    private String gender;

    @Type(type = ElasticType.DATE)
    private Date birthday;

}
