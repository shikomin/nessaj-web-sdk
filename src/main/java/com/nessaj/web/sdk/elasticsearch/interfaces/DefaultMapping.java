package com.nessaj.web.sdk.elasticsearch.interfaces;

import com.nessaj.web.sdk.elasticsearch.annotation.Type;
import com.nessaj.web.sdk.common.constants.SymbolSet;

import java.lang.reflect.Field;

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
                + SymbolSet.DOUBLE_QUOTATION_MARK
                + "properties" + SymbolSet.DOUBLE_QUOTATION_MARK
                + ":{");
        Field[] fields = clazz.getDeclaredFields();
        // set index properties
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Type.class)) {
                Type type = fields[i].getAnnotation(Type.class);
                mapping.append(SymbolSet.DOUBLE_QUOTATION_MARK + fields[i].getName() + SymbolSet.DOUBLE_QUOTATION_MARK
                        + ": {" + SymbolSet.DOUBLE_QUOTATION_MARK + "type" + SymbolSet.DOUBLE_QUOTATION_MARK + ": "
                        + SymbolSet.DOUBLE_QUOTATION_MARK + type.type().getName() + SymbolSet.DOUBLE_QUOTATION_MARK
                        + "}" + ",");
            }
        }
        mapping.deleteCharAt(mapping.lastIndexOf(","));
        mapping.append("}" + "}");

        return mapping.toString();
    }
}
