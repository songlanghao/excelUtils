package po;

import com.hbga.anno.Column;
import lombok.Data;

/**
 * @AUTHOR songlanghao qq:705352494
 * @DESCRIPTION
 * @DATE 2020/3/13 0013 14:27
 **/
@Data
public class Dog {
    @Column(1)
    private String name;
    @Column(2)
    private String agr;
}
