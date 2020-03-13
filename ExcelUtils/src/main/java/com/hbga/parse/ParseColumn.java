package com.hbga.parse;

import com.hbga.anno.Column;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR songlanghao qq:705352494
 * @DESCRIPTION
 * @DATE 2020/3/13 0013 12:14
 **/
public class ParseColumn {

    public static Map<Integer,String> parseColume(Class clazz){
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Column annotation = declaredField.getAnnotation(Column.class);
            int value = annotation.value();
            map.put(value,declaredField.getName());
        }
        return map;
    }
}
