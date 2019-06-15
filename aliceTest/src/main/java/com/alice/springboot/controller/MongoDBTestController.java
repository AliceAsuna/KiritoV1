package com.alice.springboot.controller;

import com.alice.springboot.constants.ResponseStatusEnum;
import com.alice.springboot.dao.IMongoCommonDao;
import com.alice.springboot.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MongoDBTestController {
    @Autowired
    private IMongoCommonDao mongoCommonDao;

    @RequestMapping(value = "/test/mongo/person", method = RequestMethod.GET)
    public ResponseResult<String> testMongo()
    {
        ResponseResult<String> result = new ResponseResult<>();
        try
        {
            mongoCommonDao.saveDataToMongoDB("just_for_test", null);
            result.setMessage("成功测试链接mongodb");
            result.setStatus(ResponseStatusEnum.SUCCESS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
