package check_sort;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import java.io.IOException;

public class XmlValidator {

    public static boolean validateXmlAgainstXsd(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFilePath));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
