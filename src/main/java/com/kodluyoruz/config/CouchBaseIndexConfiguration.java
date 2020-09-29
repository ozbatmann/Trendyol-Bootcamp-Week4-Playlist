package com.kodluyoruz.config;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import com.couchbase.client.java.manager.query.CreateQueryIndexOptions;
import com.couchbase.client.java.manager.query.QueryIndexManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class CouchBaseIndexConfiguration {

    private final Cluster couchbaseCluster;
    private final CouchBaseProperties couchbaseProperties;

    public CouchBaseIndexConfiguration(Cluster couchbaseCluster, CouchBaseProperties couchbaseProperties) {
        this.couchbaseCluster = couchbaseCluster;
        this.couchbaseProperties = couchbaseProperties;
    }

    @Bean
    public void createIndexes() {
        QueryIndexManager indexManager = couchbaseCluster.queryIndexes();

            indexManager.createPrimaryIndex(couchbaseProperties.getBucketName(),
                    CreatePrimaryQueryIndexOptions.createPrimaryQueryIndexOptions().ignoreIfExists(true));
            indexManager.createIndex(couchbaseProperties.getBucketName(), "user_idx", Collections.singletonList("userId"),
                    CreateQueryIndexOptions.createQueryIndexOptions().ignoreIfExists(true));
        }

}
