package spock.demo

import org.grails.testing.GrailsUnitTest
import spock.lang.Specification
import spock.lang.IgnoreRest

/**
 * Write a program to list all the Major(s) present in the data sheet.
 * './gradlew test -Dtest.single='*demo/CollegeMajor''
 */

class CollegeMajorSpec extends Specification implements GrailsUnitTest {

    def "Java-verify getMajorNames method"() {
        setup:"create object for class"
            def collegeMajor = new CollegeMajor()
        when: "read and pass the excel file.get the response as Map datatype and read it"
            File excelFile = new File(this.class.classLoader.getResource("CollegeMajor.xlsx")?.getFile())
            def names = collegeMajor.getMajorNames(excelFile)

        then:"verify the expected size and data"
            names.size() == 5
            names == ["GENERAL AGRICULTURE", "AGRICULTURE PRODUCTION AND MANAGEMENT", "ENVIRONMENTAL SCIENCE", "COMPUTER ENGINEERING", "MECHANICAL ENGINEERING"]
    }
}