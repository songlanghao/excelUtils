package po;

import com.hbga.anno.Column;
import lombok.Data;

/**
 * @AUTHOR songlanghao qq:705352494
 * @DESCRIPTION
 * @DATE 2020/3/13 0013 11:45
 **/

@Data
public class User {
    @Column(1)
    private String username;
    @Column(2)
    private String password;
}
