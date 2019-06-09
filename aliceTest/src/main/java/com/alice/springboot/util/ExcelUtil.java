package com.alice.springboot.util;

import com.alice.springboot.constants.Constants;
import com.alice.springboot.model.KeyValueVO;
import com.alice.springboot.model.MultiLevelHeaderVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel工具类
 */
public class ExcelUtil {

    private static int getNodeMaxDeep(MultiLevelHeaderVO tableHeader)
    {
        int maxDeep = 0;
        if (null == tableHeader)
        {
            return 0;
        }

        if (null == tableHeader.getChildrenHeader())
        {
            return 1;
        }
        else
        {
            for (int i = 0; i < tableHeader.getChildrenHeader().size(); i++)
            {
                maxDeep = Math.max(maxDeep, getNodeMaxDeep(tableHeader.getChildrenHeader().get(i)));
            }
            return maxDeep + 1;
        }
    }

    private static int getMaxDeep(List<MultiLevelHeaderVO> tableHeaders)
    {
        if (CommonUtil.isEmpty(tableHeaders))
        {
            return 0;
        }
        int maxDeep = 0;
        Optional<Integer> deepCount = tableHeaders.parallelStream().map(
                tableHeader -> getNodeMaxDeep(tableHeader)).collect(Collectors.toList()).stream().max(Integer::compareTo);

        if (deepCount.isPresent())
        {
            maxDeep = deepCount.get();
        }
        return maxDeep;
    }

    private static int getSuitableCellWidth(String cellValue)
    {
        int width = Constants.EIGHT;

        if (cellValue.length() <= Constants.EIGHT)
        {
            return width * Constants.TWO_HUNDRED_FIFTY_SIX * Constants.THREE / Constants.TWO;
        }

        if (cellValue.length() <= Constants.TWENTY)
        {
            width = Constants.SIX * Constants.TWO_HUNDRED_FIFTY_SIX  + Constants.NINE * Constants.TWO_HUNDRED_FIFTY_SIX / Constants.TEN;
            return width * Constants.THREE / Constants.TWO;
        }

        if (cellValue.length() <= Constants.FOUR * Constants.TEN)
        {
            width = Constants.TWENTY * Constants.TWO_HUNDRED_FIFTY_SIX + Constants.NINE * Constants.TWO_HUNDRED_FIFTY_SIX / Constants.TEN;
            return width * Constants.THREE / Constants.TWO;
        }

        width = Constants.TWO_HUNDRED_FIFTY_SIX * Constants.FOUR * Constants.SEVEN + Constants.NINE * Constants.TWO_HUNDRED_FIFTY_SIX / Constants.TEN;
        return width * Constants.THREE / Constants.TWO;
    }

    private static int getChildrenMaxCount(MultiLevelHeaderVO tableHeader)
    {
        if (tableHeader == null)
        {
            return 0;
        }

        if (CommonUtil.isEmpty(tableHeader.getChildrenHeader()))
        {
            return 1;
        }

        int maxCount = tableHeader.getChildrenHeader().size();

        for (MultiLevelHeaderVO tableHeaderChildren : tableHeader.getChildrenHeader())
        {
            int childrenCount = getChildrenMaxCount(tableHeaderChildren);

            if (maxCount <= childrenCount)
            {
                maxCount = childrenCount;
            }
        }
        return maxCount;
    }

    private static int getTableHeaderColspan(MultiLevelHeaderVO tableHeader)
    {
        if (tableHeader.getColspan() > 0)
        {
            return tableHeader.getColspan();
        }
        return getChildrenMaxCount(tableHeader);
    }

    private static int getTableHeaderRowspan(MultiLevelHeaderVO tableHeader)
    {
        if (tableHeader.getRowspan() > 0)
        {
            return tableHeader.getRowspan();
        }
        return 1;
    }

    private static void setPerLayerNodes(List<MultiLevelHeaderVO> tableHeaders, Map<Integer, List<MultiLevelHeaderVO>> perLayer, int deep)
    {
        if (null == tableHeaders)
        {
            return;
        }

        List<MultiLevelHeaderVO> thisDeepNodes = new LinkedList<>();
        List<MultiLevelHeaderVO> nextDeepNodes = new LinkedList<>();

        for (MultiLevelHeaderVO tableHeader : tableHeaders)
        {
            thisDeepNodes.add(tableHeader);
            if (null == tableHeader.getChildrenHeader() || tableHeader.getChildrenHeader().isEmpty())
            {
                continue;
            }
            nextDeepNodes.addAll(tableHeader.getChildrenHeader());
        }

        perLayer.put(deep, thisDeepNodes);

        if (!nextDeepNodes.isEmpty())
        {
            deep++;
            setPerLayerNodes(nextDeepNodes, perLayer, deep);
        }
    }

    private static void writeExcelHeader(HSSFWorkbook excel, HSSFSheet sheet, List<MultiLevelHeaderVO> tableHeaders, int maxDeep)
    {
        HSSFCellStyle cellStyle = excel.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);

        Font font = excel.createFont();
        //font.setColor();
        font.setBold(true);
        font.setFontName("宋体");
        cellStyle.setFont(font);

        Map<Integer, List<MultiLevelHeaderVO>> perLayer = new HashMap<>();

        setPerLayerNodes(tableHeaders, perLayer, 0);

        int rowStart = 0;
        int colStart = 0;
        int rowEnd = 0;
        int colEnd = 0;
        int rowspan = 0;
        int colspan = 0;

        HSSFRow headerRow = sheet.createRow(rowStart);
        HSSFCell headerCell = null;
        int lastHeaderRowIndex = 0;

        for (int deep = 0; deep < maxDeep; deep++)
        {
            List<MultiLevelHeaderVO> thisDeepDatas = perLayer.get(deep);
            colStart = 0;

            for (MultiLevelHeaderVO tableHeader : thisDeepDatas)
            {
                rowStart = tableHeader.getHeaderLevel() - 1;
                rowspan = getTableHeaderRowspan(tableHeader);
                rowEnd = rowStart + rowspan - 1;
                colspan = getTableHeaderColspan(tableHeader);
                colEnd = colspan + colStart - 1;

                if (rowStart != rowEnd || colStart != colEnd)
                {
                    CellRangeAddress headerRangeCell = new CellRangeAddress(rowStart, rowEnd, colStart, colEnd);

                    sheet.addMergedRegion(headerRangeCell);
                }

                if (rowStart != lastHeaderRowIndex)
                {
                    lastHeaderRowIndex = rowStart;
                    headerRow = sheet.createRow(rowStart);
                }

                headerCell = headerRow.createCell(colStart);

                sheet.setColumnWidth(colStart, getSuitableCellWidth(tableHeader.getFieldLabel()));
                headerCell.setCellStyle(cellStyle);
                headerCell.setCellValue(tableHeader.getFieldLabel());

                colStart = colStart + colspan;
            }

        }
    }

    private static List<String> getActualFields(List<MultiLevelHeaderVO> tableHeaders)
    {
        List<String> actualFields = new LinkedList<>();

        for (MultiLevelHeaderVO tableHeader : tableHeaders)
        {
            if (CommonUtil.isEmpty(tableHeader.getChildrenHeader()))
            {
                actualFields.add(tableHeader.getFieldName());
            }
            else
            {
                actualFields.addAll(getActualFields(tableHeader.getChildrenHeader()));
            }
        }

        return actualFields;
    }

    private static KeyValueVO needToSpecialDeal(List<KeyValueVO> specialFields, String field)
    {
        for (KeyValueVO specialField : specialFields)
        {
            if (field.equals(specialField.getKey()))
            {
                KeyValueVO temp = new KeyValueVO();
                temp.setKey("hyperlink");
                temp.setValue(specialField.getValue());
                return temp;
            }
        }
        return null;
    }

    private static <T> void setCellValue(HSSFSheet sheet, HSSFCell cell, Method getMethod,
                                         String actualField, int cellSubScript, T data, List<KeyValueVO> specialFields)
    {
        if (null == specialFields)
        {
            specialFields = new ArrayList<>();
        }

        try
        {
            if (null == getMethod.invoke(data))
            {
                cell.setCellValue("");
            }
            else
            {
                KeyValueVO temp = needToSpecialDeal(specialFields, actualField);

                if (null != temp)
                {
                    cell.setCellFormula("HYPERLINK(\"" + temp.getValue() + "\",\"" + getMethod.invoke(data).toString() + "\")");
                }
                else
                {
                    cell.setCellValue(getMethod.invoke(data).toString());
                }
                sheet.setColumnWidth(cellSubScript, getSuitableCellWidth(getMethod.invoke(data).toString()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static <T> void writeExcelCell(HSSFWorkbook excel, HSSFSheet sheet, List<String> actualFields,
                                           int startRow, List<T> datas, List<KeyValueVO> specialFields) throws Exception
    {
        HSSFCellStyle cellStyle = excel.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);

        Class<?> clazz = datas.get(0).getClass();
        HSSFRow row = null;

        for (T data : datas)
        {
            int cellSubScript = 0;
            row = sheet.createRow(startRow);

            for (String actualField : actualFields)
            {
                HSSFCell cell = row.createCell(cellSubScript);
                cell.setCellStyle(cellStyle);

                String methodName = "get" + StringUtil.getInitialsUp(actualField);
                Method getMethod = clazz.getMethod(methodName);
                setCellValue(sheet, cell, getMethod, actualField, cellSubScript, data, specialFields);
                cellSubScript++;
            }
            startRow++;
        }

    }

    /**
     * 获取2003版Excel
     * @param tableHeaders 表头信息
     * @param datas 数据
     * @param specialFields 需要特殊处理的数据，如某一列需要加超链接
     * @param <T> 泛型
     * @return Excel
     * @throws Exception 异常
     */
    public static <T> HSSFWorkbook getExcel2003(List<MultiLevelHeaderVO> tableHeaders, List<T> datas,
                                                List<KeyValueVO> specialFields) throws Exception
    {
        if (CommonUtil.isEmpty(tableHeaders))
        {
            return null;
        }

        int maxDeep = getMaxDeep(tableHeaders);
        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet = excel.createSheet("sheet1");

        writeExcelHeader(excel, sheet, tableHeaders, maxDeep);

        if (CommonUtil.isEmpty(datas))
        {
            return excel;
        }

        List<String> actualFields = getActualFields(tableHeaders);

        writeExcelCell(excel, sheet, actualFields, maxDeep, datas, specialFields);

        return excel;
    }
}
