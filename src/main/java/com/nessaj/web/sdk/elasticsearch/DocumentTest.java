package com.nessaj.web.sdk.elasticsearch;

import com.nessaj.web.sdk.elasticsearch.entities.Cat;
import com.nessaj.web.sdk.elasticsearch.exception.IndexAnnotationNotFound;
import com.nessaj.web.sdk.elasticsearch.operator.DocumentOperator;
import org.elasticsearch.action.index.IndexResponse;

import java.util.Date;

/**
 * @author keming
 * @Date 2022/03/24 23:06
 */
public class DocumentTest {

    public static void main(String[] args) {
        DocumentOperator operator = new DocumentOperator();
        Cat cat = new Cat();
        cat.setName("tom");
        cat.setGender("male");
        cat.setAge("5");
        cat.setBirthday(new Date(2132132));
        IndexResponse response = null;
        try {
            response = operator.createDocument("cat", "1", cat);
        } catch (IndexAnnotationNotFound e) {
            e.printStackTrace();
        }
        System.out.println(response.status() + "," + response.getResult());
    }

}
