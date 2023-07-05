package org.chenwei.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.extern.slf4j.Slf4j;
import org.chenwei.Bean.BookInfoExcelModel;
import org.chenwei.Bean.ServerResponse;
import org.chenwei.listener.BookInfoExcelListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/import")
public class ImportExcelController {


    @RequestMapping("importExcel")
    public ServerResponse importExcel(MultipartFile file) {
        try {
            log.info("------------importExcel start------------");
            //实例化监听
            ExcelReader excelReader = EasyExcel.read(file.getInputStream(), BookInfoExcelModel.class, new BookInfoExcelListener(new BookInfoExcelModel())).build();
            //读取excel第一页内容
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            //读取Excel
            excelReader.read(readSheet);
            //这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
            log.info("------------importExcel end------------");
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("导入表异常：" + e);
            return ServerResponse.createByError(e.getMessage());
        }
    }
}
