package tests;
import check_sort.XmlValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    void testXmlValidation() {
        String xmlFilePath = "src/beers.xml";
        String xsdFilePath = "beers.xsd";

        Assertions.assertTrue(XmlValidator.validateXmlAgainstXsd(xmlFilePath, xsdFilePath),
                "XML is not valid against XSD.");
    }
}
