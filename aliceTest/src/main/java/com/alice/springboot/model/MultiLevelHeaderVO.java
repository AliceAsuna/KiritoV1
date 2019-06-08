package com.alice.springboot.model;

import java.util.List;

/**
 * 多级表头模型
 */
public class MultiLevelHeaderVO {
    private String fieldName;

    private String fieldLabel;

    private String parent;

    private int headerLevel;

    private int rowspan;

    private int colspan;

    private List<MultiLevelHeaderVO> childrenHeader;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getHeaderLevel() {
        return headerLevel;
    }

    public void setHeaderLevel(int headerLevel) {
        this.headerLevel = headerLevel;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public List<MultiLevelHeaderVO> getChildrenHeader() {
        return childrenHeader;
    }

    public void setChildrenHeader(List<MultiLevelHeaderVO> childrenHeader) {
        this.childrenHeader = childrenHeader;
    }
}
