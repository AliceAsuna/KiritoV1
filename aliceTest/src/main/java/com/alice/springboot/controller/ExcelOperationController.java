package com.alice.springboot.controller;

import com.alice.springboot.constants.ResponseStatusEnum;
import com.alice.springboot.model.MultiLevelHeaderVO;
import com.alice.springboot.model.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Excel操作接口
 */
@RequestMapping(value = "/alice/excel")
@RestController
public class ExcelOperationController {

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResponseResult<String> exportExcel(HttpServletResponse response)
    {
        ResponseResult<String> result = new ResponseResult<>();

        try {
            //List<MultiLevelHeaderVO> tableHeaders = new LinkedList<>();
            //MultiLevelHeaderVO temp1 = new MultiLevelHeaderVO();
            //temp1.setFieldName("first");
            //temp1.setFieldLabel("一级表头");
            //MultiLevelHeaderVO temp2 = new MultiLevelHeaderVO();
            //temp2.setFieldName("first");
            //temp2.setFieldLabel("一级表头2");
            //MultiLevelHeaderVO temp3 = new MultiLevelHeaderVO();
            //temp3.setFieldName("first");
            //temp3.setFieldLabel("一级表头3");
            //MultiLevelHeaderVO temp4 = new MultiLevelHeaderVO();
            //temp4.setFieldName("first");
            //temp4.setFieldLabel("一级表头4");
            //MultiLevelHeaderVO temp5 = new MultiLevelHeaderVO();
            //temp5.setFieldName("first");
            //temp5.setFieldLabel("一级表头5");
            //tableHeaders.add(temp1);
            //tableHeaders.add(temp2);
            //tableHeaders.add(temp3);
            //tableHeaders.add(temp4);
            //tableHeaders.add(temp5);

            //response.setContenType("application/vnd.ms-excel;charset=UTF-8");
            //response.setHeader("Content-disposition", "atachment;filename=" + excelName + ".xls");
            //response.setCharacterEncoding("utf-8");

            result.setCode("200");
            result.setMessage("导出成功");
            result.setStatus(ResponseStatusEnum.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
