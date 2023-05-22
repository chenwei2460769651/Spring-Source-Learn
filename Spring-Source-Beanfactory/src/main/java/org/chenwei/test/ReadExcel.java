package org.chenwei.test;

import com.alibaba.excel.EasyExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {


    public static void main(String[] args) {
        Map<String, Double> stringDoubleMap = read01();
        Map<String, Double> stringDoubleMap1 = read02();
        Map<String, Double> resMap=new HashMap<>();
        for (String key : stringDoubleMap.keySet()) {
            Double aDouble = stringDoubleMap.get(key);
            Double aDouble02 = stringDoubleMap1.get(key);
            double difference=0;
            double max = Math.max(aDouble, aDouble02);
            double min = Math.min(aDouble, aDouble02);
            resMap.put(key,max-min);
        }
        System.out.println("resMap="+resMap);
    }


    public static Map<String, Double> read01() {
        try {
            // 创建一个输入流，将 Excel 文件读取出来
            InputStream inputStream = new FileInputStream("D:\\new.xlsx");
            List<ExcelDate> tmpList = EasyExcel.read(inputStream)
                    // 设置与Excel表映射的类
                    .head(ExcelDate.class)
                    // 设置sheet,默认读取第一个
                    .sheet()
                    // 设置标题所在行数
                    .headRowNumber(1)
                    // 异步读取
                    .doReadSync();
            Map<String, Double> map = new HashMap<>();
            double totalNum = (double) 0;
            for (ExcelDate tmpDate : tmpList) {
                double total = (double) 0;
                String name = tmpDate.getpName();
                BigDecimal maxlng1 = new BigDecimal(tmpDate.getcSelfTotalPrice());
                BigDecimal maxlng2 = new BigDecimal(tmpDate.getpSelfTotalPrice());
                double v = maxlng1.doubleValue();
                double v1 = maxlng2.doubleValue();
                total += v;
                total += v1;
                if (map.containsKey(name)) {
                    Double aDouble = map.get(name);
                    aDouble += total;
                    map.put(name, aDouble);
                } else {
                    map.put(name, total);
                }
                totalNum += total;
            }
            System.out.println("totalNum=" + totalNum);
            System.out.println("map=" + map);
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Double> read02() {
        try {
            // 创建一个输入流，将 Excel 文件读取出来
            InputStream inputStream = new FileInputStream("D:\\ss.xlsx");
            List<ExcelDateSheet2> tmpList = EasyExcel.read(inputStream)
                    // 设置与Excel表映射的类
                    .head(ExcelDateSheet2.class)
                    // 设置sheet,默认读取第一个
                    .sheet()
                    // 设置标题所在行数
                    .headRowNumber(1)
                    // 异步读取
                    .doReadSync();
            Map<String, Double> map = new HashMap<>();
            double totalNum = (double) 0;
            for (ExcelDateSheet2 tmpDate : tmpList) {
                double total = (double) 0;
                String name = tmpDate.getpName();
                BigDecimal maxlng1 = new BigDecimal(tmpDate.getcSelfTotalPrice());
                BigDecimal maxlng2 = new BigDecimal(tmpDate.getpSelfTotalPrice());
                double v = maxlng1.doubleValue();
                double v1 = maxlng2.doubleValue();
                total += v;
                total += v1;
                if (map.containsKey(name)) {
                    Double aDouble = map.get(name);
                    aDouble += total;
                    map.put(name, aDouble);
                } else {
                    map.put(name, total);
                }
                totalNum += total;
            }
            System.out.println("totalNum=" + totalNum);
            System.out.println("map=" + map);
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
