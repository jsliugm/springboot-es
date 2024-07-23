package com.universe.example;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class StudentExampleTest {
    private TransportClient client;

    @Before
    public void before() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "es").build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("docker"), 9300));
        System.out.println("before...........................");
    }

    @Test
    public void create() {
        CreateIndexRequest request = new CreateIndexRequest("person");
        request.settings("{\n" +
                "    \"analysis\": {\n" +
                "      \"analyzer\": {\n" +
                "        \"ik_analyzer\": {\n" +
                "          \"type\": \"custom\",\n" +
                "          \"tokenizer\": \"ik_max_word\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }");
        request.mapping("person", "{\n" +
                "    \"person\": {\n" +
                "      \"properties\": {\n" +
                "        \"name\": {\n" +
                "          \"type\": \"text\"\n" +
                "        },\n" +
                "        \"address\": {\n" +
                "          \"type\": \"text\",\n" +
                "          \"analyzer\": \"ik_analyzer\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }");
        // 执行创建索引请求
        CreateIndexResponse createIndexResponse = client.admin().indices().create(request).actionGet();
        // 输出结果
        if (createIndexResponse.isAcknowledged()) {
            System.out.println("Index created successfully.");
        } else {
            System.out.println("Index creation failed.");
        }
    }

    @Test
    public void insert() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "Alice");
        jsonMap.put("address", "上海市浦东新区xxx");
        IndexResponse indexResponse = client.prepareIndex("person", "person", "1")
                .setSource(jsonMap)
                .get();

        System.out.println("Document added: " + indexResponse.getId());
    }

    @Test
    public void batchInsert() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "张三三");
        jsonMap.put("address", "北京市朝阳区xxxxx");
        IndexResponse indexResponse = client.prepareIndex("person", "person", "3")
                .setSource(jsonMap)
                .get();

        System.out.println("Document added: " + indexResponse.getId());
    }

    @Test
    public void get() {
        GetResponse getResponse = client.prepareGet("person", "person", "1").get();
        if (getResponse.isExists()) {
            System.out.println("Document retrieved: " + getResponse.getSourceAsString());
        } else {
            System.out.println("Document not found");
        }
    }

    @Test
    public void match() {
        // 创建搜索请求
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("person")
                .setTypes("person")
                //.setQuery(QueryBuilders.termQuery("name.keyword", "张三"));
                .setQuery(QueryBuilders.matchQuery("name", "张三"));
// 执行搜索请求
        SearchResponse searchResponse = searchRequestBuilder.get();
// 处理搜索结果
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println("Document found: " + hit.getSourceAsString());
        }
    }

    @Test
    public void update() {
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("address", "上海市浦东新区曹路镇xxxxx");
        UpdateRequest updateRequest = new UpdateRequest("person", "person", "1")
                .doc(updateMap);
        try {
            UpdateResponse updateResponse = client.update(updateRequest).get();
            System.out.println("Document updated: " + updateResponse.getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        DeleteResponse deleteResponse = client.prepareDelete("person", "person", "2").get();
        System.out.println("Document deleted: " + deleteResponse.getId());
    }

    @Test
    public void deleteIndex() {
        DeleteIndexRequest request = new DeleteIndexRequest("person");
        // 执行创建索引请求
        DeleteIndexResponse response = client.admin().indices().delete(request).actionGet();
        // 输出结果
        if (response.isAcknowledged()) {
            System.out.println("Index delete successfully.");
        } else {
            System.out.println("Index delete failed.");
        }
    }

    @After
    public void after() {
        client.close();
        System.out.println("after！！！！！！！！！！！！！！！！！！！！！！！！！！");
    }
}
