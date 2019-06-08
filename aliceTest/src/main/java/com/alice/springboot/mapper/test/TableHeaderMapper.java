package com.alice.springboot.mapper.test;

import com.alice.springboot.entity.FieldEntity;

import java.util.List;

/**
 * 表头信息mapper
 */
public interface TableHeaderMapper {
    /**
     * 根据模块获取表头信息
     * @param category 模块
     * @return 表头信息
     */
    List<FieldEntity> getTableHeaderFieldByCategory(String category);
}
