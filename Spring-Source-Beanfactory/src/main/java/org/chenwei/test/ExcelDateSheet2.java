package org.chenwei.test;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelDateSheet2 {

    @ExcelProperty(value = "名称")
    String pName;
    @ExcelProperty(value = "个人缴费")
    String pSelfTotalPrice;
    @ExcelProperty(value = "单位")
    String cSelfTotalPrice;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpSelfTotalPrice() {
        return pSelfTotalPrice;
    }

    public void setpSelfTotalPrice(String pSelfTotalPrice) {
        this.pSelfTotalPrice = pSelfTotalPrice;
    }

    public String getcSelfTotalPrice() {
        return cSelfTotalPrice;
    }
    public void setcSelfTotalPrice(String cSelfTotalPrice) {
        this.cSelfTotalPrice = cSelfTotalPrice;
    }

    @Override
    public String toString() {
        return "ExcelDateSheet2{" +
                "pName='" + pName + '\'' +
                ", pSelfTotalPrice='" + pSelfTotalPrice + '\'' +
                ", cSelfTotalPrice='" + cSelfTotalPrice + '\'' +
                '}';
    }
}
