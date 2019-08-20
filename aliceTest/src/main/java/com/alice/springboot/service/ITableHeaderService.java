package com.alice.springboot.service;

import com.alice.springboot.entity.FieldEntity;
import com.alice.springboot.model.MultiLevelHeaderVO;
import com.alice.springboot.model.TableHeaderTestVO;

import java.util.List;

/**
 * 表头信息服务
 */
public interface ITableHeaderService {
    /**
     * 根据模块名获取对应模块的表头信息
     * @param category 模块
     * @return 表头信息
     */
    List<FieldEntity> getTableFieldByCategory(String category);

    /**
     * 根据模块获取导出的表头信息
     * @param category 模块
     * @return 导出表头信息
     */
    List<MultiLevelHeaderVO> getExcelFieldByCategory(String category);

    /**
     * 根据模块名获取对应模块的表头信息
     * @param category 模块
     * @return 表头信息
     */
    List<FieldEntity> getTableFieldByCategoryWithTwoDataBase(String category);

    List<TableHeaderTestVO> getTableHeaderTestVOs();
}
