package QuoteLogic

import net.pricefx.tdd4c.TestRun
import spock.lang.Specification

class InvoicePriceTest extends Specification {

    def LOGIC_DIR = "QuoteLogic+2021-01-01"
    def ELEMENT_NAME = "InvoicePrice"


    def "test case description"() {
        when:
        TestRun testRun = TestRun.builder()
                .withLogicTestDouble("api", [])
                .buildElementTest(LOGIC_DIR, ELEMENT_NAME)

        and:
        Script script = testRun.getElementScript()

        then:
        testRun.execute()
                .getElementTestResult() != null
    }
}
