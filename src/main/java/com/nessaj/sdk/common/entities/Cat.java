package com.nessaj.sdk.common.entities;

import com.nessaj.sdk.elasticsearch.annotation.Index;
import com.nessaj.sdk.elasticsearch.annotation.Type;
import com.nessaj.sdk.elasticsearch.annotation.ElasticType;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
