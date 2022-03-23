package com.nessaj.web.sdk.elasticsearch.annotation;

import com.nessaj.web.sdk.elasticsearch.interfaces.DefaultMapping;
import com.nessaj.web.sdk.elasticsearch.interfaces.DefaultSetting;
import com.nessaj.web.sdk.elasticsearch.interfaces.IndexMapping;
import com.nessaj.web.sdk.elasticsearch.interfaces.IndexSetting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author keming
 * @Date 2022/03/23 21:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Index {

    String name() default "default_index";

    Class<? extends IndexMapping> mappingGenerator() default DefaultMapping.class;

    Class<? extends IndexSetting> settingGenerator() default DefaultSetting.class;

}
