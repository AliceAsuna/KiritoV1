package com.alice.springboot.controller;

import com.alice.springboot.constants.ResponseStatusEnum;
import com.alice.springboot.entity.FieldEntity;
import com.alice.springboot.model.ResponseResult;
import com.alice.springboot.service.ITableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 表头信息接口
 */
@RequestMapping(value = "/alice/tableHeader")
@RestController
public class TableHeaderController {
    @Autowired
    private ITableHeaderService tableHeaderService;

    @RequestMapping(value = "/tableHeaderInfos", method = RequestMethod.GET)
    public ResponseResult<List<FieldEntity>> exportExcel(String category)
    {
        ResponseResult<List<FieldEntity>> result = new ResponseResult<>();

        try {
            result.setData(tableHeaderService.getTableFieldByCategory(category));
            result.setCode("200");
            result.setMessage("查询" + category + "表头信息成功");
            result.setStatus(ResponseStatusEnum.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
