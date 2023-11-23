package Parsers;

import Components.Beers;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

public class SAXParser extends DefaultHandler {

    private UniversalXMLHandler handler;

    public SAXParser(UniversalXMLHandler handler) {
        this.handler = handler;
    }

    public Beers parseBeerXml(String path) throws Exception {
        // Встановлюємо обробник для SAX парсера
        org.xml.sax.XMLReader reader = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
        reader.setContentHandler(this);

        // Починаємо парсинг
        reader.parse(new org.xml.sax.InputSource(path));

        // Отримуємо результат від обробника
        return handler.getBeers();
    }

    // Перевизначені методи DefaultHandler для обробки подій SAX парсера

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        handler.handleStartElement(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        handler.handleCharacters(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        handler.handleEndElement(qName);
    }
}
