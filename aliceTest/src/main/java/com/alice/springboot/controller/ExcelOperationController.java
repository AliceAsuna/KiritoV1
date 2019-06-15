package com.alice.springboot.controller;

import com.alice.springboot.entity.FieldEntity;
import com.alice.springboot.model.MultiLevelHeaderVO;
import com.alice.springboot.model.ResponseResult;
import com.alice.springboot.service.ITableHeaderService;
import com.alice.springboot.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel操作接口
 */
@RequestMapping(value = "/alice/excel")
@RestController
public class ExcelOperationController {
    @Autowired
    private ITableHeaderService tableHeaderService;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResponseResult<List<FieldEntity>> exportExcel(String category, HttpServletResponse response)
    {
        ResponseResult<List<FieldEntity>> result = new ResponseResult<>();

        try {
            List<MultiLevelHeaderVO> tableHeaders = tableHeaderService.getExcelFieldByCategory(category);
            HSSFWorkbook excel = ExcelUtil.getExcel2003(tableHeaders, null, null);

            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "atachment;filename=" + "测试" + ".xls");
            response.setCharacterEncoding("utf-8");

            OutputStream output = response.getOutputStream();
            excel.write(output);
            output.flush();
            output.close();
            excel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
