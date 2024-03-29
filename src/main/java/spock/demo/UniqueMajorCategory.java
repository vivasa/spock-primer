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
 * Write a program to list all Unique Major_Category(ies) present in the data sheet.
 * './gradlew test -Dtest.single='*demo/UniqueMajorCategory''
 */

public class UniqueMajorCategory {

    public static Map getUniqueMajorCategories(File file){

        Map<String, List<String>> result = new HashMap<String, List<String>>();
        List<String> uniqMajorCategory = new ArrayList<String>();
        try {
            InputStream ExcelFileToRead = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
            XSSFSheet wbsheet = wb.getSheetAt(0);
            XSSFRow row = null;
            Iterator rowIterator = wbsheet.rowIterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                try{
                    if (row.getRowNum() != 0 && row.getCell(2) != null) {
                        if(!uniqMajorCategory.contains(row.getCell(2).getStringCellValue())){
                            uniqMajorCategory.add(row.getCell(2).getStringCellValue());
                        }
                    }
                }catch(Exception e) {
                    System.out.println ("Exception:- "+e);
                }
            }
        }catch (Exception ie) {
            ie.printStackTrace(System.out);
        }

        result.put("majorCategory",uniqMajorCategory);
        return result;
    }

}