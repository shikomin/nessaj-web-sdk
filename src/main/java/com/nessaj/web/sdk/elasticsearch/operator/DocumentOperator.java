package com.nessaj.web.sdk.elasticsearch.operator;

import com.alibaba.fastjson.JSON;
import com.nessaj.web.sdk.elasticsearch.annotation.Index;
import com.nessaj.web.sdk.elasticsearch.exception.IndexAnnotationNotFound;
import com.nessaj.web.sdk.elasticsearch.factory.RestHighLevelClientFactory;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author keming
 * @Date 2022/03/24 22:38
 */
public class DocumentOperator {
    private RestHighLevelClient client;

    public DocumentOperator() {
        this(RestHighLevelClientFactory.getInstance().build());
    }

    public DocumentOperator(RestHighLevelClient client) {
        this.client = client;
    }

    public IndexResponse createDocument(String indexName, String id, Object object) throws IndexAnnotationNotFound {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        if (!object.getClass().isAnnotationPresent(Index.class)) {
            throw new IndexAnnotationNotFound(object.getClass());
        }
        request.source(JSON.toJSONString(object), XContentType.JSON).routing("routing").timeout(TimeValue.timeValueSeconds(1));
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexResponse;
    }

}
