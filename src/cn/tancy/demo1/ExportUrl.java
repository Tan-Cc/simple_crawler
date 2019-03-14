package cn.tancy.demo1;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

public class ExportUrl {

    public static void exportweburl(HashSet<String> marked){

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("url信息");

        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("url");
        cell.setCellStyle(style);

        int i=0;
        for(String s1 : marked){
            row = sheet.createRow(++i);
            row.createCell(0).setCellValue(s1);
        }

        try {
            FileOutputStream fout = new FileOutputStream("F:\\url.xls");
            wb.write(fout);
            fout.close();
            System.out.println("Excel文件生成啦！！！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
