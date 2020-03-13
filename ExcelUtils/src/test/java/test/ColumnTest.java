package test;

import com.hbga.ExcelUtils;
import com.hbga.anno.Column;
import org.junit.Test;
import po.Dog;
import po.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * @AUTHOR songlanghao qq:705352494
 * @DESCRIPTION
 * @DATE 2020/3/13 0013 11:47
 **/

public class ColumnTest {


    @Test
    public void test(){
        User user = new User();
        Field[] fields = user.getClass().getDeclaredFields();
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            int value = annotation.value();
            map.put(value,field.getName());
        }
        System.out.println(map);
    }

    @Test
    public void test2() throws Exception {
        FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\user.xlsx");
        List<Dog> list = ExcelUtils.getList(in, "user.xlsx", Dog.class);
        System.out.println(list);

    }
}
