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
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * Write a program to list all the Major(s) present in the data sheet.
 * './gradlew test -Dtest.single='*demo/CollegeMajor''
 */

public class CollegeMajor {

    public static Map getMajorNames(File file){
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        List<String> majorNames = new ArrayList<String>();
        try {
            InputStream excelFileToRead = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(excelFileToRead);
            XSSFSheet wbsheet = wb.getSheetAt(0);
            XSSFRow row = null;
            Iterator rowIterator = wbsheet.rowIterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                try{
                    if (row.getRowNum() != 0 && row.getCell(1) != null) {
                        majorNames.add(row.getCell(1).getStringCellValue().toUpperCase());
                    }
                }catch(Exception e) {
                    System.out.println ("Exception:- "+e);
                }
            }
        }catch(Exception ie) {
            ie.printStackTrace(System.out);
        }

        result.put("majorName",majorNames);
        return result;
    }

}