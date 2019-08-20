package com.alice.springboot.util;

import com.alice.springboot.model.KeyValueVO;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 导入Excel工具类
 */
public class ImportExcelUtil {

    private static void setMappingOfCellAndModel(int colIndex, String fieldValue, Map<Integer, String> mappingResult)
    {
        mappingResult.put(colIndex, fieldValue);
    }

    private static String getModelField(String tableName, List<KeyValueVO> tableHeaders)
    {
        for (KeyValueVO tableHeader : tableHeaders)
        {
            if (tableHeader.getValue().equals(tableName))
            {
                return tableHeader.getKey();
            }
        }

        return "";
    }

    private static <T> void setDataByRow(Sheet oneSheet, int rowIndex, List<KeyValueVO> tableHeaders,
                                         Map<Integer, String> colAndField, Class<?> clazz, List<T> resultVO)
    {
        T modelVO = null;

        try
        {
            modelVO = (T) clazz.newInstance();
        }
        catch (Exception e2)
        {
            System.out.println("创建泛型实例失败" + e2.toString());
        }

        Method setMethod = null;
        Row oneRow = oneSheet.getRow(rowIndex);
        int totalColNumber = oneRow.getPhysicalNumberOfCells();

        for (int colIndex = 0; colIndex < totalColNumber; colIndex++)
        {
            Cell oneCell = oneRow.getCell(colIndex);
            if (null == oneCell)
            {
                continue;
            }

            oneCell.setCellType(CellType.STRING);
            if (rowIndex == 0)
            {
                String fieldValue = getModelField(oneCell.getStringCellValue(), tableHeaders);

                setMappingOfCellAndModel(colIndex, fieldValue, colAndField);
            }
            else
            {
                if (CommonUtil.isEmpty(colAndField.get(colIndex)))
                {
                    continue;
                }

                try
                {
                    setMethod = clazz.getMethod("set" + StringUtil.getInitialsUp(colAndField.get(colIndex)), String.class);
                }
                catch (Exception e1)
                {
                    System.out.println("获取set方法出错" + e1.toString());
                }

                try
                {
                    setMethod.invoke(modelVO, oneCell.getStringCellValue());
                }
                catch (Exception e)
                {
                    System.out.println("设置模型值出错" + e.toString());
                }

                if (rowIndex != 0)
                {
                    resultVO.add(modelVO);
                }
            }

        }
    }

    private static <T> List<T> setDataBySheet(Workbook excel, int sheetIndex, List<KeyValueVO> tableHeaders,
                                              Map<Integer, String> colAndField, Class<?> clazz, Integer startRow)
    {
        Sheet oneSheet = excel.getSheetAt(sheetIndex);
        int totalRowNumber = oneSheet.getPhysicalNumberOfRows();

        List<T> resultVO = new LinkedList<>();

        for (int rowIndex = startRow; rowIndex < totalRowNumber; rowIndex++)
        {
            setDataByRow(oneSheet, rowIndex, tableHeaders, colAndField, clazz, resultVO);
        }

        return resultVO;
    }

    public static <T> List<T> getModels(Class<?> clazz, Integer startRow, Workbook excel, List<KeyValueVO> tableHeaders)
    {
        if (CommonUtil.isEmpty(tableHeaders))
        {
            return null;
        }

        int sheetsNumber = excel.getNumberOfSheets();

        Map<Integer, String> colAndField = new HashMap<>();
        List<T> resultVO = null;

        for (int sheetIndex = 0; sheetIndex < sheetsNumber; sheetIndex++)
        {
            resultVO = setDataBySheet(excel, sheetIndex, tableHeaders, colAndField, clazz, startRow);
        }

        return resultVO;
    }
}
