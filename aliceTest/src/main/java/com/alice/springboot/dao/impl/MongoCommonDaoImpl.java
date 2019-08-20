package com.alice.springboot.dao.impl;

import com.alice.springboot.dao.IMongoCommonDao;
import com.mongodb.Mongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoCommonDaoImpl implements IMongoCommonDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    private MongoCollection<Document> getCollection(String collectionName)
    {
        return mongoTemplate.getCollection(collectionName);
    }

    @Override
    public void saveDataToMongoDB(String collectionName, Document document) {
        MongoCollection<Document> collection = getCollection(collectionName);
        collection.insertOne(new Document().append("test", "测试保存数据"));
    }
}
