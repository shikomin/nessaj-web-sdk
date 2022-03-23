package com.nessaj.web.sdk.elasticsearch.operator;

import com.nessaj.web.sdk.elasticsearch.factory.RestHighLevelClientFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;


/**
 * @author keming
 * @Date 2022/03/22 20:29
 */
public class IndexOperator {

    private RestHighLevelClient client;

    public IndexOperator() {
        this(RestHighLevelClientFactory.getInstance().build());
    }

    public IndexOperator(RestHighLevelClient client) {
        this.client = client;
    }

    public void createIndex(String indexName, String jsonStr) {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1)
        );
        request.mapping(jsonStr, XContentType.JSON);
        request.setTimeout(TimeValue.timeValueMinutes(1));
        request.setMasterTimeout(TimeValue.timeValueMinutes(1));
        CreateIndexResponse response = null;
        try {
            response = client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("isAcknowledged: " + response.isAcknowledged());
        System.out.println("isShardsAcknowledged: " + response.isShardsAcknowledged());
    }

}
