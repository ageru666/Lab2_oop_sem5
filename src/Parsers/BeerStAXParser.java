package Parsers;

import Components.Beers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BeerStAXParser {

    public Beers parse(InputStream xmlInput) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(xmlInput);

        BeerStAXHandler staxHandler = new BeerStAXHandler();
        return staxHandler.parseXML(reader);
    }

    public Beers parse(String path) throws XMLStreamException, FileNotFoundException {
        InputStream xmlInput = new FileInputStream(path);

        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(xmlInput);

        BeerStAXHandler staxHandler = new BeerStAXHandler();
        return staxHandler.parseXML(reader);
    }
}
