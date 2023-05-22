package org.chenwei.Bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄")
    private int age;

    @Excel(name = "操作时间",format="yyyy-MM-dd HH:mm:ss", width = 20.0)
    private Date time;
}
