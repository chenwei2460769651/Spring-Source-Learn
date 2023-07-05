package org.chenwei.Bean;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookInfoExcelModel {
    @ExcelProperty(value = "书籍名称", index = 0) // 定义表头名称和位置,0代表第一列
    private String bookName;
    @ExcelProperty(value = "书籍价格", index = 1)
    private BigDecimal bookPrice;
    @ExcelProperty(value = "书籍作者", index = 2)
    private String bookAuther;
    @ExcelProperty(value = "书籍类别", index = 3)
    private String bookType;
    @ExcelProperty(value = "珍本标识", index = 4)
    private String rareFlag;
    @ExcelProperty(value = "出版社", index = 5)
    private String press;
    @ExcelProperty(value = "出版日期", index = 6)
    private Date pressDate;
    @ExcelProperty(value = "备注", index = 7)
    private String remark;
}

