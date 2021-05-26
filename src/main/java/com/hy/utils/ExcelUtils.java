package com.hy.utils;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.hy.pojo.Student;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shy Boy
 * @Date 2021/5/24 - 05 - 24 - 10:27
 * @Description: com.hy.utils
 * @version: 1.0
 */
public class ExcelUtils {

//    读取excel文件

    public static void readerExcel(String path){
        //获取文件对象
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            //获取Excel对象
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            // 获取当前页数
            XSSFSheet sheetAt = sheets.getSheetAt(0);
            //获取当页总行数
            int rows = sheetAt.getPhysicalNumberOfRows();
            for (int i = 0; i <rows ; i++) {
                //获取当前行对象
                XSSFRow row = sheetAt.getRow(i);
                //获取当前单元格总数
                System.out.print(row.getCell(0));
                System.out.print(row.getCell(1));
                System.out.print(row.getCell(2));
                System.out.print(row.getCell(3));
                System.out.println("");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //生成Excel文件
    public static void createExcel(List<Student> students){
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet("uesr");
        for (int i = 0; i < students.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(students.get(i).getName());
            XSSFCell cells = row.createCell(1);
            cells.setCellValue(students.get(i).getAge());
        }
        try {
            FileOutputStream fileOutputStreaml = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\test02.xlsx");
           xssfWorkbook.write(fileOutputStreaml);
            fileOutputStreaml.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //通过Hutool读取Excle

    public static void hutoolExcle(String path){
        //通过Hutool编号获取
        ExcelReader reader = ExcelUtil.getReader(path, 0);
        int rowCount = reader.getRowCount();
        for (int i = 0; i <rowCount ; i++) {
            System.out.println(reader.getCell(0,i));
            System.out.println(reader.getCell(1,i));
        }
    }

    //通过Hutool生成excel
    public static void create(List<Student> list){
        //通过工具创建write
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\Lenovo\\Desktop\\test03.xlsx");
        //自定义标题别名
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("age","年龄");
        //一次性写出所有内容 使用默认样式 强制输出标题
        writer.write(list,true);
        //关闭writer 释放内存
        writer.close();
    }






    public static void main(String[] args) {
//        readerExcel("C:\\Users\\Lenovo\\Desktop\\test.xlsx");

        List list = new ArrayList();
        list.add(new Student("杨冬勇","20"));
        list.add(new Student("杨过","30"));
        list.add(new Student("张三","18"));
        list.add(new Student("李四","24"));
        list.add(new Student("王五","17"));
        createExcel(list);

//        hutoolExcle("C:\\Users\\Lenovo\\Desktop\\test02.xlsx");
        create(list);
   }

}
