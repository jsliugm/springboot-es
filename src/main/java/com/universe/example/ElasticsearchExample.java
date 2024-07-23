package com.universe.example;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import java.net.InetAddress;
import java.util.concurrent.ExecutionException;

public class ElasticsearchExample {
    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "es").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("docker"), 9300));

        // 添加文档
        IndexResponse indexResponse = client.prepareIndex("my_index", "my_type", "1")
                .setSource("name", "Alice", "age", 30)
                .get();
        System.out.println("Document added: " + indexResponse.getId());

        // 查询文档
        SearchResponse searchResponse = client.prepareSearch("my_index")
                .setQuery(QueryBuilders.matchQuery("name", "Alice"))
                .get();
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        // 更新文档
        UpdateRequest updateRequest = new UpdateRequest("my_index", "my_type", "1")
                .doc("age", 31);
        try {
            client.update(updateRequest).get();
            System.out.println("Document updated");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 删除文档
        DeleteResponse deleteResponse = client.prepareDelete("my_index", "my_type", "1").get();
        System.out.println("Document deleted: " + deleteResponse.getId());

        // 根据字段条件删除文档
        DeleteByQueryRequestBuilder deleteByQueryRequestBuilder = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .source("my_index")
                .filter(QueryBuilders.matchQuery("name", "Bob"));
        deleteByQueryRequestBuilder.get();
        System.out.println("Documents with name 'Bob' deleted");

        // 关闭客户端
        client.close();
    }
}
