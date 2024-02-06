package com.practice.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchClientProvider {
    public ElasticsearchClient getClient() {
        RestClient restClient = RestClient
                .builder(HttpHost.create("http://192.168.100.97:9200"))
                .build();

        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

    public Boolean createIndex(String indexTitle) {
        ElasticsearchClient esClient = getClient();
        try {
            esClient.indices().create(c -> c
                    .index(indexTitle)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
