package com.nessaj.web.sdk.elasticsearch;

import com.nessaj.web.sdk.common.entities.Cat;
import com.nessaj.web.sdk.common.exception.IndexAnnotationNotFound;
import com.nessaj.web.sdk.elasticsearch.operator.ClientOperator;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.Strings;

import java.util.Date;

/**
 * @author keming
 * @Date 2022/03/23 21:35
 */
public class IndexTest {

    public static void main(String[] args) {
        ClientOperator operator = new ClientOperator();
        Cat cat = new Cat();
        cat.setName("tom");
        cat.setGender("male");
        cat.setAge("6");
        cat.setBirthday(new Date(42343));
        IndexResponse response2 = null;
        try {
//            operator.createIndex(Dog.class);
            System.out.println("index cat exists:" + operator.isExist("cat"));
            System.out.println("delete index cat:" + operator.deleteIndex("cat").isAcknowledged());
            CreateIndexResponse response1 = operator.createIndex(Cat.class);
            System.out.println("isAcknowledged: " + response1.isAcknowledged());
            System.out.println("isShardsAcknowledged: " + response1.isShardsAcknowledged());
            response2 = operator.createDocument("cat", "2", cat);
            System.out.println(response2.status() + "," + response2.getResult());

            String[] includes = {"name", "gender", "age"};
            GetResponse response3 = operator.getDocument("cat", "2", includes, Strings.EMPTY_ARRAY);
            System.out.println(response3.getSource());
            operator.closeClient();
        } catch (IndexAnnotationNotFound e) {
            e.printStackTrace();
        }

    }

}
