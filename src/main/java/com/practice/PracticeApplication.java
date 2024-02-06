package com.practice;


import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.practice.model.Product;
import com.practice.utils.ElasticSearchClientProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) throws IOException {
        ElasticSearchClientProvider esClient = new ElasticSearchClientProvider();
        //create index
        Boolean result = esClient.createIndex("products");
        System.out.println(result);

        //index a record
        Product product = new Product("1", "bk-1", "City bike", 123.0);
        try {
            IndexResponse response = esClient.getClient().index(i -> i.index("products").id(product.getId()).document(product));
            System.out.println("Indexed with version " + response.version());
        } catch (Exception e) {
            System.out.println("sth went wrong");
        }

        //get a record
        GetResponse<Product> response = esClient.getClient().get(g -> g
                        .index("products")
                        .id("1"),
                Product.class
        );

        if (response.found()) {
            Product product1 = response.source();
            System.out.println("Product name " + product1.getTitle());
        } else {
            System.out.println("Product not found");
        }

        //search in an index
        String searchText = "City";
        SearchResponse<Product> response1 = esClient.getClient().search(s -> s
                        .index("products")
                        .query(q -> q
                                .match(t -> t
                                        .field("type")
                                        .query(searchText)
                                )
                        ),
                Product.class
        );

        System.out.println(response1.hits().hits().get(0).toString());

        //update a record in an index
        Product product2 = new Product("1", "bk-1", "Mountain bike", 123.0);
        esClient.getClient().update(u -> u
                        .index("products")
                        .id("1")
                        .doc(product2),
                Product.class);
    }

}
