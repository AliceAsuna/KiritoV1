package com.alice.springboot.service.impl;

import com.alice.springboot.entity.FieldEntity;
import com.alice.springboot.mapper.test.TableHeaderMapper;
import com.alice.springboot.service.ITableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表头信息服务实现
 */
@Service
public class TableHeaderServiceImpl implements ITableHeaderService {
    @Autowired
    private TableHeaderMapper tableHeaderMapper;

    @Override
    public List<FieldEntity> getTableFieldByCategory(String category) {
        if (null == tableHeaderMapper)
        {
            System.out.println("没有注入mapper");
        }
        else
        {
            System.out.println("注入mapper");
        }
        return tableHeaderMapper.getTableHeaderFieldByCategory(category);
    }
}
