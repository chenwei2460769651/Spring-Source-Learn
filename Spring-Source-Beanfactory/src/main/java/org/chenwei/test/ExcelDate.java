package org.chenwei.test;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelDate {

    @ExcelProperty(value = "公司名称")
    String companyName;
    @ExcelProperty(value = "人员姓名")
    String pName;
    @ExcelProperty(value = "人员状态")
    String pStatus;
    @ExcelProperty(value = "险种类型")
    String pType;
    @ExcelProperty(value = "个人缴纳合计")
    String pSelfTotalPrice;
    @ExcelProperty(value = "单位缴纳合计")
    String cSelfTotalPrice;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
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
        return "ExcelDate{" +
                "companyName='" + companyName + '\'' +
                ", pName='" + pName + '\'' +
                ", pStatus='" + pStatus + '\'' +
                ", pType='" + pType + '\'' +
                ", pSelfTotalPrice='" + pSelfTotalPrice + '\'' +
                ", cSelfTotalPrice='" + cSelfTotalPrice + '\'' +
                '}';
    }
}
