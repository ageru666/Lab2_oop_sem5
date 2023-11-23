package Parsers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import Components.Beers;
public class BeerStAXParser {

    private UniversalXMLHandler handler;

    public BeerStAXParser(UniversalXMLHandler handler) {
        this.handler = handler;
    }

    public Beers parse(InputStream xmlInput) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(xmlInput);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    handler.handleStAXStartElement(reader.getLocalName());
                    break;
                case XMLStreamConstants.CHARACTERS:
                    handler.handleStAXCharacters(reader.getText());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    handler.handleStAXEndElement(reader.getLocalName());
                    break;
            }
        }

        return handler.getBeers();
    }
}
