package com.nessaj.web.sdk.elasticsearch;

import com.nessaj.web.sdk.elasticsearch.entities.Cat;
import com.nessaj.web.sdk.elasticsearch.entities.Dog;
import com.nessaj.web.sdk.elasticsearch.exception.IndexAnnotationNotFound;
import com.nessaj.web.sdk.elasticsearch.operator.IndexOperator;
import org.elasticsearch.client.indices.CreateIndexResponse;

/**
 * @author keming
 * @Date 2022/03/23 21:35
 */
public class IndexTest {

    public static void main(String[] args) {
        IndexOperator operator = new IndexOperator();
        try {
//            operator.createIndex(Dog.class);
            System.out.println("index cat exists:" + operator.isExist("cat"));
            System.out.println("delete index cat:" + operator.deleteIndex("cat").isAcknowledged());
            CreateIndexResponse response = operator.createIndex(Cat.class);
            System.out.println("isAcknowledged: " + response.isAcknowledged());
            System.out.println("isShardsAcknowledged: " + response.isShardsAcknowledged());
            operator.closeClient();
        } catch (IndexAnnotationNotFound e) {
            e.printStackTrace();
        }

    }

}
