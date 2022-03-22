package com.nessaj.web.sdk.elasticsearch;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author keming
 * @Date 2022/03/21 19:34
 */
public class ElasticsearchTest {

    public static void main(String[] args) {

        RestHighLevelClient client = RestHighLevelClientBuilder.getInstance().build();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user2", "kimchy2");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch2");

        IndexRequest request = new IndexRequest("posts")
                .id("1").source(jsonMap);
        request.routing("routing");
        request.timeout(TimeValue.timeValueSeconds(5));
        IndexResponse response = null;
        try {
            response = client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String index = response.getIndex();
        String id = response.getId();
        if (response.getResult() == DocWriteResponse.Result.CREATED) {
            System.out.println("index: " + index + "; id: " + id + "; created");
        } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
            System.out.println("index: " + index + "; id: " + id + "; updated");
        }
        ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            System.out.println("total: " + shardInfo.getTotal() + "; success: " + shardInfo.getSuccessful());
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                System.out.println(reason);
            }
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
