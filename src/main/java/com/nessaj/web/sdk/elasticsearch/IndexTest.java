package com.nessaj.web.sdk.elasticsearch;

import com.nessaj.web.sdk.elasticsearch.entities.Cat;
import com.nessaj.web.sdk.elasticsearch.entities.Dog;
import com.nessaj.web.sdk.elasticsearch.exception.IndexAnnotationNotFound;
import com.nessaj.web.sdk.elasticsearch.operator.IndexOperator;

/**
 * @author keming
 * @Date 2022/03/23 21:35
 */
public class IndexTest {

    public static void main(String[] args) {
        IndexOperator operator = new IndexOperator();
        String mapping1 = "{\n" +
                "  \"properties\": {\n" +
                "    \"name\": {\n" +
                "      \"type\": \"text\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"sid\": {\n" +
                "      \"type\": \"long\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String mapping2 = "{\n" +
                "  \"properties\": {\n" +
                "    \"name\": {\n" +
                "      \"type\": \"text\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"tid\": {\n" +
                "      \"type\": \"long\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        try {
//            operator.createIndex(Cat.class);
            operator.createIndex(Dog.class);
        } catch (IndexAnnotationNotFound e) {
            e.printStackTrace();
        }

    }

}
