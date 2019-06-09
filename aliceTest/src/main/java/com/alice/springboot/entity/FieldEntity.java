package com.alice.springboot.entity;

/**
 * 表头实体
 */
public class FieldEntity {
    private String fieldName;

    private String fieldLabel;

    private String type;

    private int isExportField;

    private int labelIndex;

    private int exportIndex;

    private String parentField;

    private int headerLevel;

    private String category;

    public int getHeaderLevel() {
        return headerLevel;
    }

    public void setHeaderLevel(int headerLevel) {
        this.headerLevel = headerLevel;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsExportField() {
        return isExportField;
    }

    public void setIsExportField(int isExportField) {
        this.isExportField = isExportField;
    }

    public int getLabelIndex() {
        return labelIndex;
    }

    public void setLabelIndex(int labelIndex) {
        this.labelIndex = labelIndex;
    }

    public int getExportIndex() {
        return exportIndex;
    }

    public void setExportIndex(int exportIndex) {
        this.exportIndex = exportIndex;
    }

    public String getParentField() {
        return parentField;
    }

    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
