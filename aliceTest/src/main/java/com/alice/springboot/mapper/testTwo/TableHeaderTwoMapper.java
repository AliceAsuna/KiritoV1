package com.alice.springboot.mapper.testTwo;

import com.alice.springboot.entity.FieldEntity;

import java.util.List;

/**
 * 表头信息mapper
 */
public interface TableHeaderTwoMapper {
    /**
     * 根据模块获取表头信息
     * @param category 模块
     * @return 表头信息
     */
    List<FieldEntity> getTableHeaderFieldByCategory(String category);

    /**
     * 根据模块获取Excel表头信息
     * @param category 模块
     * @return 表头信息
     */
    List<FieldEntity> getExcelHeaderFieldByCategory(String category);
}
