package com.nessaj.sdk.elasticsearch.operator;

import com.alibaba.fastjson.JSON;
import com.nessaj.sdk.common.exception.IndexAnnotationNotFound;
import com.nessaj.sdk.elasticsearch.factory.RestHighLevelClientFactory;
import com.nessaj.sdk.elasticsearch.annotation.Index;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;


/**
 * @author keming
 * @Date 2022/03/22 20:29
 */
public class ClientOperator {

    private RestHighLevelClient client;

    public ClientOperator() {
        this(RestHighLevelClientFactory.getInstance().build());
    }

    public ClientOperator(RestHighLevelClient client) {
        this.client = client;
    }

    /**
     * 创建index (from a class)
     *
     * @param clazz
     * @return
     * @throws IndexAnnotationNotFound
     */
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
        return createIndex(indexAnnotation.name(), mapping, setting);

    }

    /**
     * 创建index
     *
     * @param indexName
     * @param mapping
     * @param setting
     * @return
     */
    public CreateIndexResponse createIndex(String indexName, Object mapping, Settings setting) {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        if (mapping instanceof String) {
            request.settings(setting).mapping((String) mapping, XContentType.JSON).setTimeout(TimeValue.timeValueMinutes(1));
        }
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

    /**
     * index是否存在
     *
     * @param indexName
     * @return
     */
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

    /**
     * 创建Doc
     *
     * @param index
     * @param id
     * @param object
     * @return
     * @throws IndexAnnotationNotFound
     */
    public IndexResponse createDocument(String index, String id, Object object) throws IndexAnnotationNotFound {
        IndexRequest request = new IndexRequest(index);
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

    /**
     * 查询doc
     *
     * @param index
     * @param id
     */
    public GetResponse getDocument(String index, String id, String[] includes, String[] excludes) {
        GetRequest request = new GetRequest(index, id);
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
        GetResponse response = null;
        try {
            response = client.get(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void closeClient() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
