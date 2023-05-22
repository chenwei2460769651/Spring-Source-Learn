package org.chenwei.Test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.chenwei.Bean.UserEntity;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyExpory {

    public static void main(String[] args) throws Exception {
        List<UserEntity> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName("李四" + i);
            userEntity.setAge(30 + i);
            userEntity.setTime(new Date(System.currentTimeMillis() + i));
            dataList.add(userEntity);
        }
        //生成excel文档
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户","用户信息"),
                UserEntity.class, dataList);
        FileOutputStream fos = new FileOutputStream("D:/easypoi-user1.xls");
        workbook.write(fos);
        fos.close();
    }

}
