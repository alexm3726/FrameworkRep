package utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReading {

    static Workbook book;
    static Sheet sheet;

    public static void openExcel(String filepath){
        try {
            FileInputStream fis= new FileInputStream(filepath);
            book= new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSheet(String sheetName){
        sheet= book.getSheet(sheetName);
    }
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    public static int getColsCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }
    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    public static List<Map<String, String>> excelIntoListMap(String filePath, String sheetName){
        openExcel(filePath);
        getSheet(sheetName);


        List<Map<String, String>> listData= new ArrayList<>();

        //outer loop(rows)

        for (int i = 1; i < getRowCount(); i++) {

            //creating a map for every single row
            Map<String, String> map =new LinkedHashMap<>();

            //looping through the values of all cells(columns)
            for (int j = 0; j < getColsCount(i); j++) {
                map.put(getCellData(0,j),getCellData(i,j));
            }
            listData.add(map);
        }
        return listData;

    }
}
