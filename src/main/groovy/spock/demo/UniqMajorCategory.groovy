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
 * Write a program to list all Unique Major_Category(ies) present in the data sheet.
 * './gradlew test -Dtest.single='*demo/UniqueMajorCategory''
 */

public class UniqMajorCategory {

    public static Map getUniqueMajorCategories(File file){
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        List<String> uniqMajorCategory = new ArrayList<String>();
        try {
            InputStream excelFileToRead = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(excelFileToRead);
            XSSFSheet wbsheet = wb.getSheetAt(0);
            wbsheet.each{
               if (it.getRowNum() != 0 && it.getCell(2) != null) {
                        if(!uniqMajorCategory.contains(it.getCell(2).getStringCellValue())){
                            uniqMajorCategory.add(it.getCell(2).getStringCellValue());
                        }
                    }
            }
        }catch (Exception ie) {
            ie.printStackTrace(System.out);
        }

        result.put("majorCategory",uniqMajorCategory);
        return result;
    }

}