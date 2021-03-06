package com.nessaj.sdk.elasticsearch;

import com.nessaj.sdk.elasticsearch.factory.RestHighLevelClientFactory;
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

        RestHighLevelClient client = RestHighLevelClientFactory.getInstance().build();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "griff");
        jsonMap.put("age", 23);
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");

        IndexRequest request = new IndexRequest("student-01")
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
