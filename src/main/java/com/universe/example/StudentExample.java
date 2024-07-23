package com.universe.example;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;

public class StudentExample {
    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "es").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("docker"), 9300));
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
        request.mapping("person","{\n" +
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

        // 关闭客户端
        client.close();
    }
}
