package spock.demo;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Write a program to list all the Major(s) present in the data sheet.
 * './gradlew test -Dtest.single='*demo/CollegeMajor''
 */

public class CollegeMajorNames {

    public static Map getMajorNames(File file){
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        def majorNames = []
        try {
            InputStream excelFileToRead = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(excelFileToRead);
            XSSFSheet wbsheet = wb.getSheetAt(0);
            wbsheet.each{
               if (it.getRowNum() != 0 && it.getCell(1) != null) {
                        if(!majorNames.contains(it.getCell(1).getStringCellValue())){
                            majorNames.add(it.getCell(1).getStringCellValue());
                        }
                    }
            }
            }catch(Exception ie) {
                ie.printStackTrace(System.out);
            }
            result.put("majorName",majorNames);
            return result;
    }

}