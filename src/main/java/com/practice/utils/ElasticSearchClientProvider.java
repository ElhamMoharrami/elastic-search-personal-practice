package com.practice.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchClientProvider {
    @Value("${app.elastic.url}")
    private static String serverUrl;

    public ElasticsearchClient getClient() {
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .build();

        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        ElasticsearchClient esClient = new ElasticsearchClient(transport);
        return esClient;
    }
}
