package com.alice.springboot.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {
    @Value("${spring.datasource.mongodb.host}")
    private String host;

    @Value("${spring.datasource.mongodb.port}")
    private int port;

    @Value("${spring.datasource.mongodb.database}")
    private String database;

    @Bean(name = "mongoDbFactory")
    public MongoDbFactory mongoDbFactory() {

        // 客户端配置（连接数，副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();

        //数据库参数，如最大连接数，等待时间，超时信息等
        //builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
        //builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
        //if (properties.getReplicaSet() != null) {
        //    builder.requiredReplicaSetName(properties.getReplicaSet());
        //}
        //builder.threadsAllowedToBlockForConnectionMultiplier(
        //        properties.getThreadsAllowedToBlockForConnectionMultiplier());
        //builder.serverSelectionTimeout(properties.getServerSelectionTimeout());
        builder.maxWaitTime(120000);
        //builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
        //builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
        builder.connectTimeout(10000);
        //builder.socketTimeout(properties.getSocketTimeout());
        //// builder.socketKeepAlive(properties.getSocketKeepAlive());
        //builder.sslEnabled(properties.getSslEnabled());
        //builder.sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed());
        //builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
        //builder.heartbeatFrequency(properties.getHeartbeatFrequency());
        //builder.minHeartbeatFrequency(properties.getMinHeartbeatFrequency());
        //builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
        //builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
        //builder.localThreshold(properties.getLocalThreshold());

        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress(host, port);
        serverAddresses.add(serverAddress);

        // 连接认证
        // MongoCredential mongoCredential = null;
        // if (properties.getUsername() != null) {
        // 	mongoCredential = MongoCredential.createScramSha1Credential(
        // 			properties.getUsername(), properties.getAuthenticationDatabase() != null
        // 					? properties.getAuthenticationDatabase() : properties.getDatabase(),
        // 			properties.getPassword().toCharArray());
        // }

        // 创建认证客户端
        // MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredential, mongoClientOptions);

        // 创建非认证客户端
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoClientOptions);

        // 创建MongoDbFactory
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, database);
        return mongoDbFactory;
    }

    @Bean(name = "mongoTemplate")
    @Autowired
    public MongoTemplate getMongoTemplate(MongoDbFactory mongoDbFactory)
    {
        return new MongoTemplate(mongoDbFactory);
    }
}
