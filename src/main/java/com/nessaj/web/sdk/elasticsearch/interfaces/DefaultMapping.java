package com.nessaj.web.sdk.elasticsearch.interfaces;

import com.nessaj.web.sdk.elasticsearch.annotation.Type;
import com.nessaj.web.sdk.elasticsearch.constants.Constants;
import jdk.nashorn.internal.runtime.ConsString;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author keming
 * @Date 2022/03/23 22:15
 */
public class DefaultMapping implements IndexMapping<String> {

    private static final String DOUBLE_SPACE = "  ";

    @Override
    public String getMapping(Class clazz) {
        StringBuilder mapping = new StringBuilder();
        mapping.append("{"
                + Constants.DOUBLE_QUOTATION_MARK
                + "properties" + Constants.DOUBLE_QUOTATION_MARK
                + ":{");
        Field[] fields = clazz.getDeclaredFields();
        // set index properties
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Type.class)) {
                Type type = fields[i].getAnnotation(Type.class);
                mapping.append(Constants.DOUBLE_QUOTATION_MARK + fields[i].getName() + Constants.DOUBLE_QUOTATION_MARK
                        + ": {"
                        + Constants.DOUBLE_QUOTATION_MARK + "type" + Constants.DOUBLE_QUOTATION_MARK + ": "
                        + Constants.DOUBLE_QUOTATION_MARK + type.type().getName() + Constants.DOUBLE_QUOTATION_MARK
                        + "}" + ",");
            }
        }
        mapping.deleteCharAt(mapping.lastIndexOf(","));
        mapping.append("}" + "}");

        return mapping.toString();
    }
}
