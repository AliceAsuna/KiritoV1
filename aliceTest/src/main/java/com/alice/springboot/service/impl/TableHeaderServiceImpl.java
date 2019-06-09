package com.alice.springboot.service.impl;

import com.alice.springboot.entity.FieldEntity;
import com.alice.springboot.mapper.test.TableHeaderMapper;
import com.alice.springboot.model.MultiLevelHeaderVO;
import com.alice.springboot.service.ITableHeaderService;
import com.alice.springboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        return tableHeaderMapper.getTableHeaderFieldByCategory(category);
    }

    @Override
    public List<MultiLevelHeaderVO> getExcelFieldByCategory(String category) {
        List<FieldEntity> fields = tableHeaderMapper.getExcelHeaderFieldByCategory(category);
        List<MultiLevelHeaderVO> result = new ArrayList<>();
        Map<String, MultiLevelHeaderVO> fieldNameToHeaders = new HashMap<>();
        for (FieldEntity field : fields)
        {
            MultiLevelHeaderVO temp = new MultiLevelHeaderVO();
            temp.setHeaderLevel(field.getHeaderLevel());
            temp.setParent(field.getParentField());
            temp.setFieldLabel(field.getFieldLabel());
            temp.setFieldName(field.getFieldName());
            if (temp.getChildrenHeader() == null)
            {
                temp.setChildrenHeader(new ArrayList<>());
            }

            if (CommonUtil.isEmpty(temp.getParent()))
            {
                result.add(temp);
                if (!fieldNameToHeaders.containsKey(field.getFieldName()))
                {
                    fieldNameToHeaders.put(field.getFieldName(), temp);
                }
                continue;
            }
            fieldNameToHeaders.get(temp.getParent()).getChildrenHeader().add(temp);
        }
        return result;
    }
}
