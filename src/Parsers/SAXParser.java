package Parsers;

import Components.Beers;

import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;

public class SAXParser {

    public static Beers parseBeerXml(InputStream xmlInput) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

            BeerSAXHandler handler = new BeerSAXHandler();
            saxParser.parse(xmlInput, handler);

            return handler.getBeers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Beers parseBeerXml(String path) {
        try {
            InputStream xmlInput = new FileInputStream(path);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

            BeerSAXHandler handler = new BeerSAXHandler();
            saxParser.parse(xmlInput, handler);

            return handler.getBeers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
