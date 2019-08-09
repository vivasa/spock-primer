package spock.demo

import org.grails.testing.GrailsUnitTest
import spock.lang.Specification
import spock.lang.IgnoreRest

/**
 * Write a program to list all Unique Major_Category(ies) present in the data sheet.
 * './gradlew test -Dtest.single='*demo/UniqueMajorCategory''
 */

class UniqueMajorCategorySpec extends Specification implements GrailsUnitTest {

    @IgnoreRest
    def "get all unique major category present in data sheet"(){
        setup:
            def uniqueMajorCategory = new UniqueMajorCategory()
        when:"read and pass the excel file.get the response as Map datatype and read it"
            File excelFile = new File(this.class.classLoader.getResource("CollegeMajor.xlsx")?.getFile())
            Map<String, List<String>> result = uniqueMajorCategory.getUniqueMajorCategories(excelFile)
            List<String> names = result.get('majorCategory')
        then:"verify the expected size"
            names.size() == 3
            assert name == ["Agriculture & Natural Resources", "Biology & Life Science", "Engineering"]
            println "--- "+result
    }


    def "get all unique major category present in data sheet"(){
        setup:
            def uniqueMajorCategory = new UniqMajorCategory()
        when:"read and pass the excel file.get the response as Map datatype and read it"
            File excelFile = new File(this.class.classLoader.getResource("CollegeMajor.xlsx")?.getFile())
            Map<String, List<String>> result = uniqueMajorCategory.getUniqueMajorCategories(excelFile)
            List<String> names = result.get('majorCategory')
        then:"verify the expected size"
            names.size() == 3
            println "-- "+result
    }
}