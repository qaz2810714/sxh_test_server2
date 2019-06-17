package com.yongqiang.wms.common.utils.ExcelHandle.ExcelImport;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yongqiang.wms.common.utils.ExcelHandle.FieldConvertModel;
import com.yongqiang.wms.common.utils.ExcelHandle.HandleExtIntf;
import com.yongqiang.wms.common.utils.ExcelHandle.ReflectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yantao.chen on 2018-12-14
 * excel导入操作类
 */
public class ExcelImportHandler {
    private final static Logger log = LoggerFactory.getLogger(ExcelImportHandler.class);

    private static String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 上传ECEL 只使用第一个Sheet
     * @param file 上传EXECEL文件
     * @param dicMap 处理字典转换的Map
     * @param bizClass 转换成的Model类
     * @param extHandle 额外处理方法，针对业务功能进行个性化处理
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> UploadReturn<T> uploadExcel(MultipartFile file, Map<String, Map<String, String>> dicMap, Class<T> bizClass, HandleExtIntf extHandle) throws Exception{
        FileInputStream fileIn = (FileInputStream)file.getInputStream();
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb = WorkbookFactory.create(fileIn);
        UploadReturn<T> result = handleUploadSheet(wb.getSheetAt(0),dicMap,bizClass,extHandle);
        fileIn.close();//关闭流
        return result;
    }

    /**
     * 上传ECEL,输入需要上传的Sheet页索引
     * @param file 上传EXECEL文件
     * @param dicMap 处理字典转换的Map
     * @param bizClass 转换成的Model类
     * @param extHandle 额外处理方法，针对业务功能进行个性化处理
     * @param sheetIndex 需要上传的Sheet页索引值
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> UploadReturn<T> uploadExcel(MultipartFile file, Map<String, Map<String, String>> dicMap, Class<T> bizClass, HandleExtIntf extHandle, int sheetIndex) throws Exception{
        FileInputStream fileIn = (FileInputStream)file.getInputStream();
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb = WorkbookFactory.create(fileIn);
        UploadReturn<T> result = handleUploadSheet(wb.getSheetAt(sheetIndex),dicMap,bizClass,extHandle);
        fileIn.close();//关闭流
        return result;
    }

    private static <T> UploadReturn<T> handleUploadSheet(Sheet sheet, Map<String, Map<String, String>> dicMap, Class<T> bizClass, HandleExtIntf extHandle) throws Exception{
        if (sheet == null)
            throw new Exception("Sheet页不存在");
        UploadReturn<T> uploadReturn = new UploadReturn<>();
        List<T> returnList = new ArrayList<>();
        List<UploadError> errorList = new ArrayList<>();
        Map<Integer, FieldConvertModel> titleFieldMap = new HashMap<>();
        for (Row row : sheet) {
            //第一行进行转换策略的定义
            if (row.getRowNum() == 0)
                titleFieldMap = getTitleFieldMap(row, bizClass);
                //第二行开始进行数据的遍历
            else {
                setBizData(row, titleFieldMap, bizClass, dicMap, extHandle, returnList, errorList);
            }
        }

        //封装返回对象
        uploadReturn.setResultList(returnList);
        uploadReturn.setErrorMsgList(errorList);
        return uploadReturn;
    }

    /**
     * 上传一个EXCEL的所有Sheet
     * @return 返回以SheetName => 结果集 的键值对
     * @throws Exception
     */
    public static <T> Map<String, UploadReturn<T>> uploadExcelWithAllSheet(MultipartFile file, Map<String, Map<String, String>> dicMap, Class<T> bizClass, HandleExtIntf extHandle) throws Exception {
        Map<String, UploadReturn<T>> result = new HashMap<>();
        FileInputStream fileIn = (FileInputStream)file.getInputStream();
        //根据指定的文件输入流导入Excel从而产生Workbook对象 使用工厂模式创建以兼容07和03版本EXCEL
        Workbook wb = WorkbookFactory.create(fileIn);
        //获取Excel文档中的所有SHEET
        for (int i=0;i<wb.getNumberOfSheets();i++) {
            Sheet sheet = wb.getSheetAt(i);
            UploadReturn<T> uploadReturn = handleUploadSheet(sheet,dicMap,bizClass,extHandle);
            result.put(sheet.getSheetName(),uploadReturn);
        }

        fileIn.close();//关闭流
        return result;
    }

    /**
     * 计算row的最后一个非空cell计数
     * @param row
     * @param count
     * @return
     */
    private static int calCulateCount(Row row,int count) throws Exception{
        if (count < 0)
            return -1;
        Cell cell = row.getCell(count);
        if (cell != null){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        if (cell == null || StringUtils.isEmpty(cell.getStringCellValue())){
            count = calCulateCount(row,count-1);
        }
        return count;
    }

    /**
     * 对象根据字段类型进行属性赋值
     * @param field
     * @param bizInfo
     * @param cellValue
     * @param <T>
     */
    private static <T> void setFieldValue(Field field, T bizInfo, String cellValue, UploadError error, String titleName, HandleExtIntf extHandle) throws Exception{
        Class fieldType = field.getType();
        if(fieldType == Integer.class && StringUtils.isNotEmpty(cellValue)) {
            try {
                field.set(bizInfo, Integer.valueOf(cellValue));
            }catch (Exception e){
                error.setErrorMsg(titleName + "列格式应为整数");
                return;
            }
        }else if(fieldType == Long.class && StringUtils.isNotEmpty(cellValue)) {
            try {
                field.set(bizInfo, Long.valueOf(cellValue));
            }catch (Exception e){
                error.setErrorMsg(titleName + "列格式应为数字");
                return;
            }
        }else if(fieldType == Double.class && StringUtils.isNotEmpty(cellValue)) {
            try {
                field.set(bizInfo, Double.valueOf(cellValue));
            }catch (Exception e){
                error.setErrorMsg(titleName + "列格式应为数字");
                return;
            }
        }else if(fieldType == Date.class && StringUtils.isNotEmpty(cellValue)) {
            try {
                field.set(bizInfo,parseDateStr(cellValue));
            }catch (Exception e){
                error.setErrorMsg(titleName + "列格式应为日期");
                return;
            }
        }else if(fieldType == BigDecimal.class && StringUtils.isNotEmpty(cellValue)) {
            try {
                field.set(bizInfo, new BigDecimal(cellValue));
            }catch (Exception e){
                error.setErrorMsg(titleName + "列格式应为数字");
                return;
            }
        }else {
            if(cellValue == null || cellValue.equals("")){
                if (extHandle != null){
                    extHandle.valid(field,cellValue,error);
                }
                return;
            }
            try {
                field.set(bizInfo, cellValue);
            }catch (Exception e){
                error.setErrorMsg(titleName + "列格式不正确");
                return;
            }
        }
        //如果格式校验通过，则进行业务校验
        if (extHandle != null){
            extHandle.valid(field,cellValue,error);
        }
    }

    /**
     * 通过标题行获取转换关系
     * @param titleRow EXCEL 标题行
     * @param bizClass 业务Model Class
     * @param <T>
     * @return 列索引=>转换策略 的映射关系
     */
    private static <T> Map<Integer, FieldConvertModel> getTitleFieldMap(Row titleRow, Class<T> bizClass) {
        Map<Integer, FieldConvertModel> titleFieldMap = new HashMap<>();
        Field[] fields = ReflectUtils.getAllFields(bizClass);
        for (Field field : fields) {
            ExcelTitleField excelTitleAnn = field.getAnnotation(ExcelTitleField.class);//获取需要进行excel导入的注解字段
            if(excelTitleAnn == null){
                continue;//如果没有excel操作注解直接跳过
            }
            for (int i = 0; i < titleRow.getLastCellNum(); i++) {
                String fieldTitle = excelTitleAnn.title();//获取excel中的列名对应值
                Cell cell = titleRow.getCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String cellTitle = cell.getStringCellValue();
                if(cellTitle.equals(fieldTitle)){
                    FieldConvertModel fieldConvertModel = new FieldConvertModel();
                    fieldConvertModel.setFieldName(field.getName());//设置字段名对应的列名
                    fieldConvertModel.setDicName(excelTitleAnn.dicName());//设置字段名对应的字典名
                    fieldConvertModel.setTitleName(fieldTitle);
                    //如果该字段由特殊方法处理，则进行标记
                    if (excelTitleAnn.extHandle()){
                        fieldConvertModel.setExtHandle(true);
                    }
                    titleFieldMap.put(i,fieldConvertModel);
                    break;
                }
            }
        }
        return titleFieldMap;
    }

    /**
     * 通过EXCEL数据返回业务List
     * @param dataRow 当前EXCEL数据列
     * @param bizClass 业务Class
     * @param titleFieldMap 转换策略
     * @param dicMap 字典转换
     * @param dataList 业务List
     * @param errList 报错List
     * @param <T>
     */
    private static<T> void setBizData(Row dataRow, Map<Integer, FieldConvertModel> titleFieldMap, Class<T> bizClass, Map<String, Map<String, String>> dicMap, HandleExtIntf extHandle, List<T> dataList, List<UploadError> errList) throws Exception {
        //计算该行的单元格最大列所在索引值
        int cellCount = calCulateCount(dataRow,dataRow.getLastCellNum());
        if (cellCount < 0)
            return;
        //该行对应的业务Model
        T bizInfo = bizClass.newInstance();
        //遍历列索引
        for (int i=0;i<=cellCount;i++){
            //该列对应的转换策略
            FieldConvertModel convertModel =titleFieldMap.get(i);
            //如果该列没有转换策略则跳过
            if (convertModel == null)
                continue;
            //根据转换策略定义的字段名递归查找字段，查找深度为3
            Field field = ReflectUtils.getField(bizClass, convertModel.getFieldName(), 3);
            field.setAccessible(true);
            //计算需要设置到业务Model上的Value值
            String settingValue = getSettingValue(dataRow.getCell(i),field,convertModel,dicMap,extHandle);
            //创建报错的返回对象
            UploadError error = new UploadError("");
            error.setRow(dataRow.getRowNum()-1);
            //把值设置到字段上
            setFieldValue(field,bizInfo,settingValue,error,convertModel.getTitleName(),extHandle);//
            error.setColumn(i);//设置报错信息列
            error.setRow(dataRow.getRowNum()-1);//设置报错信息行
            if(StringUtils.isNotEmpty(error.getErrorMsg())) {
                errList.add(error);//如果该单元格有报错 则将报错信息加入到报错集合
            }
        }
        dataList.add(bizInfo);
    }

    /**
     * 计算需要设置在model上的值
     * @param cell
     * @param field
     * @param dicMap
     * @return
     */
    private static String getSettingValue(Cell cell, Field field, FieldConvertModel convertModel, Map<String, Map<String, String>> dicMap, HandleExtIntf extHandle){
        String result = null;
        if (cell != null){
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue();
            result = cellValue;
            //如果是由额外方法处理，则交由外部函数自行处理
            if (convertModel.isExtHandle()) {
                result = extHandle.handle(field, cellValue);
            }
            //如果不由额外方法处理，且字典名不为空，则进行字典转换
            if (!convertModel.isExtHandle() && StringUtils.isNotEmpty(convertModel.getDicName())) {//有字典项注解的字段转化为字典项
                if (StringUtils.isEmpty(cellValue) || dicMap.get(convertModel.getDicName()) == null || dicMap.get(convertModel.getDicName()).get(cellValue) == null) {
                    result = null;
                } else {
                    result = dicMap.get(convertModel.getDicName()).get(cellValue);
                }
            }
        }
        return result;
    }
    /**
     * 日期类型转化
     * @return
     */
    private static Date parseDateStr(String strDate) {
        if(strDate == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将报错集合转换为以行号为key的报错信息map
     * @param errorList
     * @return
     */
    public static Map<Integer,List<UploadError>> getRowErrorMap(List<UploadError> errorList){
        Map<Integer,List<UploadError>> errorMap = new TreeMap<>();
        if(CollectionUtils.isNotEmpty(errorList)) {
            for (UploadError uploadError : errorList) {
                if (errorMap.containsKey(uploadError.getRow())) {
                    errorMap.get(uploadError.getRow()).add(uploadError);
                } else {
                    List<UploadError> tmpErrorList = new ArrayList<>();
                    tmpErrorList.add(uploadError);
                    errorMap.put(uploadError.getRow(), tmpErrorList);
                }
            }
        }
        return errorMap;
    }
}
