package com.hbga;

import com.hbga.parse.ParseColumn;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.InputStream;
import java.lang.reflect.Field;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR songlanghao qq:705352494
 * @DESCRIPTION
 * @DATE 2020/3/13 0013 12:20
 **/
public class ExcelUtils {
    private final static String excel2003L = ".xls"; // 2003- 版本的excel
    private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

    public  static  <T> List<T> getList(InputStream in, String filename, Class<T> clazz) throws Exception {
        //1.根据in和filename创建Excel对象
        Map<Integer, String> integerStringMap = ParseColumn.parseColume(clazz);
        Workbook workbook = getWorkbook(in, filename);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<T> list = new ArrayList<T>(sheet.getLastRowNum()+2);
        for (int i = 1; i <=sheet.getLastRowNum() ; i++) {
            T o = clazz.newInstance();
            Row row = sheet.getRow(i);
            if (row==null){
                return list;
            }
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell==null){
                    break;
                }
                Object cellValue = getCellValue(cell);
                if (cellValue.equals("")){
                    break;
                }
                Field filed = clazz.getDeclaredField(integerStringMap.get(j+1));
                filed.setAccessible(true);
                filed.set(o,getCellValue(cell));

            }
            list.add(o);

        }
        return list;
    }


    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr); // 2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr); // 2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    private static Object getCellValue(Cell cell) {
        if(cell == null) {
            return null;
        }
        DataFormatter formatter = new DataFormatter();
        return  formatter.formatCellValue(cell);
    }

}
