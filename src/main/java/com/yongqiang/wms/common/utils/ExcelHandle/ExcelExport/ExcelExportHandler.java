package com.yongqiang.wms.common.utils.ExcelHandle.ExcelExport;


import com.yongqiang.wms.common.utils.ExcelHandle.FieldConvertModel;
import com.yongqiang.wms.common.utils.ExcelHandle.HandleExtIntf;
import com.yongqiang.wms.common.utils.ExcelHandle.ReflectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 导出成EXCEL处理类
 *
 * @author yang.shang
 * @create 2018-12-27 11:25
 **/
public class ExcelExportHandler {


    private static final Logger log = LoggerFactory.getLogger(ExcelExportHandler.class);

    private static final Map<Class,Integer> fieldCellTypeMap = new HashMap<>();

    static {
        fieldCellTypeMap.put(Integer.class,Cell.CELL_TYPE_NUMERIC);
        fieldCellTypeMap.put(Double.class,Cell.CELL_TYPE_NUMERIC);
        fieldCellTypeMap.put(BigDecimal.class,Cell.CELL_TYPE_NUMERIC);
        fieldCellTypeMap.put(String.class,Cell.CELL_TYPE_STRING);
        fieldCellTypeMap.put(Boolean.class,Cell.CELL_TYPE_BOOLEAN);
    }

    public static <T> XSSFWorkbook exportExcel(List<T> bizModelList, Map<String,Map<String,String>> dicMap){
        return exportExcel(bizModelList,dicMap,null,null);
    }
    public static <T> XSSFWorkbook exportExcel(List<T> bizModelList, Map<String,Map<String,String>> dicMap,Map<Integer,FieldConvertModel> convertModelMap){
        return exportExcel(bizModelList,dicMap,convertModelMap,null);
    }
    public static <T> XSSFWorkbook exportExcel(List<T> bizModelList, Map<String,Map<String,String>> dicMap, HandleExtIntf extHandle){
        return exportExcel(bizModelList,dicMap,null,extHandle);
    }

    /**
     * 导出方法 会生成一个EXCEL文件
     * @param bizModelList 业务单据List
     * @param <T>
     * @param dicMap 字典map
     * @return
     */
    public static <T> XSSFWorkbook exportExcel(List<T> bizModelList, Map<String,Map<String,String>> dicMap,Map<Integer,FieldConvertModel> convertModelMap,HandleExtIntf extHandle){
        XSSFWorkbook book = new XSSFWorkbook();
        try {
            XSSFSheet sheet = book.createSheet("Sheet1");
            writeExcel(sheet,bizModelList,dicMap,convertModelMap,extHandle);
        }
        catch (Exception e){
            log.error("创建EXCEL失败！",e);
        }
        return book;
    }

    /**
     * 绘制EXCEL模块
     * @param sheet
     * @param bizModelList
     * @param dicMap
     */
    private static void writeExcel(XSSFSheet sheet,List bizModelList,Map<String,Map<String,String>> dicMap,Map<Integer,FieldConvertModel> convertModelMap,HandleExtIntf extHandle){
        if (CollectionUtils.isEmpty(bizModelList))
            return;
        //获取List的第一个元素 进行转换策略获取及表头绘制
        Object first = bizModelList.get(0);
        Map<Integer, FieldConvertModel> convertMap = getConvertMap(first.getClass(),convertModelMap);
        //先写第一行标题栏
        writeTitle(sheet,convertMap);
        //第2行到第n行写数据
        try {
            for (int i = 0; i <bizModelList.size() ; i++) {
                Object data = bizModelList.get(i);
                writeRowData(sheet,i+2,data,dicMap,convertMap,extHandle);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //数据设置完以后设置自动列宽
        setAutoSize(sheet,first.getClass());
    }

    /**
     * 设置自适应宽度
     * @param sheet
     * @param clazz
     */
    private static void setAutoSize(XSSFSheet sheet, Class<?> clazz) {
        //列同样从0开始
        int columnIndex = 0;
        for (Field field : ReflectUtils.getAllFields(clazz)){
            ExcelExportTitle ann = field.getAnnotation(ExcelExportTitle.class);
            //包含导出注解则写入
            if (ann != null) {
                sheet.autoSizeColumn(columnIndex);
                columnIndex++;
            }
        }
        //由于对中文不生效，额外对中文进行设置列宽;
        chineseAutoSize(sheet,columnIndex+1);
    }

    /**
     * 中文自适应列宽
     * @param sheet
     * @param size
     */
    private static void chineseAutoSize(XSSFSheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }


    /**
     * 绘制表头
     * @param sheet 表格
     * @param convertMap 数据转换关系
     */
    private static void writeTitle(Sheet sheet,Map<Integer,FieldConvertModel> convertMap){
        //行索引从0开始
        Row row = sheet.createRow(0);
        //列同样从0开始
        for (Map.Entry<Integer,FieldConvertModel> entry:convertMap.entrySet()){
            row.createCell(entry.getKey()).setCellValue(entry.getValue().getTitleName());
        }
    }

    /**
     * 绘制数据
     * @param sheet 表格
     * @param rowIndex 第几行
     * @param data 绘制的数据
     */
    private static void writeRowData(Sheet sheet, int rowIndex, Object data, Map<String,Map<String,String>> dicMap, Map<Integer,FieldConvertModel> convertMap, HandleExtIntf extHandle) throws IllegalAccessException {
        //行索引从0开始
        Row row = sheet.createRow(rowIndex-1);
        for (Map.Entry<Integer,FieldConvertModel> entry:convertMap.entrySet()){
            Cell newCell = row.createCell(entry.getKey());
            Field field = ReflectUtils.getField(data.getClass(),entry.getValue().getFieldName(),5);
            Object setValue = null;
            if (field != null) {
                field.setAccessible(true);
                setValue = field.get(data);
            }
            //如果该字段由额外方法处理且额外方法不为空 则进行处理转换逻辑
            if (entry.getValue().isExtHandle() && extHandle != null ){
                extHandle.handle(field,null);
            }
            //如果有字典标注，则通过字典进行转换
            else if (StringUtils.isNotEmpty(entry.getValue().getDicName())){
                setValue = dicConvertValue(entry.getValue().getDicName(),setValue,dicMap);
            }
            cellSetValue(newCell,setValue);
        }
    }


    /**
     * 获取列索引index => 转换策略的转换关系
     * 请不要发生实体上的INDEX与传入的INDEX重复的情况，否则转换可能出现异常
     * @param clazz 实体对象类
     * @param convertMap 转换策略，可以由外部传入
     * @return
     */
    private static Map<Integer, FieldConvertModel> getConvertMap(Class clazz,Map<Integer,FieldConvertModel> convertMap){
        Map<Integer, FieldConvertModel> result = new HashMap<>(convertMap);
        //定义一个索引被使用的索引堆来标记
        Set<Integer> indexSet = convertMap.keySet();
        int cur =0;
        for (Field field : ReflectUtils.getAllFields(clazz)){
            ExcelExportTitle ann = field.getAnnotation(ExcelExportTitle.class);
            if (ann != null){
                FieldConvertModel model = new FieldConvertModel();
                model.setDicName(ann.dicName());
                model.setFieldName(field.getName());
                model.setTitleName(ann.title());
                model.setExtHandle(ann.extHandle());
                //获取设置到哪个索引上
                cur = getUnusedIndex(indexSet,cur);
                result.put(cur,model);
                indexSet.add(cur);
                cur++;
            }
        }
        return result;
    }
    //在一个索引堆中寻找没有被使用的索引
    private static int getUnusedIndex(Set<Integer> indexSet,int currIndex){
        if (CollectionUtils.isEmpty(indexSet)) return currIndex;
        if (!indexSet.contains(currIndex)) return currIndex;
        return getUnusedIndex(indexSet,currIndex+1);
    }
    /**
     * 单元格设值
     * @param cell
     * @param fieldValue
     */
    private static void cellSetValue(Cell cell,Object fieldValue){
        //如果字段值为空则直接跳出
        if (fieldValue == null)
            return;
        int cellType = fieldCellTypeMap.get(fieldValue.getClass());
        cell.setCellType(cellType);
        if (fieldValue instanceof Integer)
            cell.setCellValue((Integer)(fieldValue));
        if (fieldValue instanceof Long)
            cell.setCellValue((Long)fieldValue);
        if (fieldValue instanceof Date)
            cell.setCellValue((Date) fieldValue);
        if (fieldValue instanceof Double)
            cell.setCellValue((Double) fieldValue);
        if (fieldValue instanceof BigDecimal)
            cell.setCellValue(((BigDecimal) fieldValue).doubleValue());
        if (fieldValue instanceof String)
            cell.setCellValue(fieldValue.toString());

    }

    /**
     * 字典值转换方法
     * @param dicCatName
     * @param setValue
     * @param dicMap
     * @return
     */
    private static String dicConvertValue(String dicCatName,Object setValue,Map<String,Map<String,String>> dicMap){
        if (dicMap == null || dicMap.get(dicCatName) == null)
            return setValue.toString();
        return dicMap.get(dicCatName).get(setValue.toString());
    }

}
