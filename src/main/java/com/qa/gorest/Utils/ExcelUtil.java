package com.qa.gorest.Utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    private static final String PATH = "./src/test/resources/testData/testData.xls";
    private static Workbook book;
    private static Sheet sheet;

    @Test
    public static Object[][] getData(String sheetName){
        Object data[][] = null;
        try {
            FileInputStream ip = new FileInputStream(PATH);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);
            int rows = sheet.getLastRowNum();
            int col = sheet.getRow(0).getLastCellNum();
            System.out.println(rows+col);
            data = new Object[rows][col];

            for(int i=0;i<rows;i++){
                for(int j=0;j<col;j++){
                    data[i][j] = sheet.getRow(i+1).getCell(j).toString();
                     /*will read everything from Excel in String and will change the type in Java according to requirement
                    e.g 100 will read from String and in test class, will change from String to int*/
                }
            }
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
