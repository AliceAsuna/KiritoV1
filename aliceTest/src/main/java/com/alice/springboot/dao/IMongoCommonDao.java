package com.alice.springboot.dao;

import org.bson.Document;

/**
 * 操作mongodb通用dao
 */
public interface IMongoCommonDao {

    void saveDataToMongoDB(String collectionName, Document document);
}
