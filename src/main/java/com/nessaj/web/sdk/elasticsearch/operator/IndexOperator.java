package com.nessaj.web.sdk.elasticsearch.operator;

import com.nessaj.web.sdk.elasticsearch.annotation.Index;
import com.nessaj.web.sdk.elasticsearch.exception.IndexAnnotationNotFound;
import com.nessaj.web.sdk.elasticsearch.factory.RestHighLevelClientFactory;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
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

    public CreateIndexResponse createIndex(Class<?> clazz) throws IndexAnnotationNotFound {
        if (!clazz.isAnnotationPresent(Index.class)) {
            throw new IndexAnnotationNotFound(clazz);
        }
        Index indexAnnotation = clazz.getAnnotation(Index.class);
        Object mapping = null;
        Settings setting = null;
        try {
            mapping = indexAnnotation.mappingGenerator().newInstance().getMapping(clazz);
            setting = indexAnnotation.settingGenerator().newInstance().getSetting();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (mapping instanceof String) {
            return createIndex(indexAnnotation.name(), (String) mapping, setting);
        }
        return null;
    }

    public CreateIndexResponse createIndex(String indexName, String mapping, Settings setting) {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(setting).mapping(mapping, XContentType.JSON).setTimeout(TimeValue.timeValueMinutes(1));
        request.setMasterTimeout(TimeValue.timeValueMinutes(1));
        CreateIndexResponse response = null;
        try {
            response = client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public AcknowledgedResponse deleteIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        request.timeout(TimeValue.timeValueMinutes(2));
        AcknowledgedResponse response = null;
        try {
            response = client.indices().delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean isExist(String indexName) {
        GetIndexRequest request = new GetIndexRequest(indexName);
        request.local(false).humanReadable(true).includeDefaults(false);
        boolean exists = false;
        try {
            exists = client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void closeClient() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
