package spock.demo

import org.grails.testing.GrailsUnitTest
import spock.lang.Specification
import spock.lang.IgnoreRest

/**
 * Write a program to list all Unique Major_Category(ies) present in the data sheet.
 * './gradlew test -Dtest.single='*demo/UniqueMajorCategory''
 */

class UniqueMajorCategorySpec extends Specification implements GrailsUnitTest {

    /* This test method will test UniqueMajorCategory.java program */
    def "Java-get all unique major category present in data sheet"(){
        setup:
            def uniqueMajorCategory = new UniqueMajorCategory()
        when:"read and pass the excel file.get the response as Map datatype and read it"
            File excelFile = new File(this.class.classLoader.getResource("CollegeMajor.xlsx")?.getFile())
            Map<String, List<String>> result = uniqueMajorCategory.getUniqueMajorCategories(excelFile)
            List<String> names = result.get('majorCategory')
        then:"verify the expected size & data"
            names.size() == 3
            names == ["Agriculture & Natural Resources", "Biology & Life Science", "Engineering"]
    }

}