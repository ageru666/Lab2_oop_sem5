package Parsers;

import Components.Beers;

import java.io.FileInputStream;
import java.io.InputStream;

public class BeerDOMParser {

    public static Beers parseXML(InputStream xmlInput) throws Exception {
        DOMParser domParser = new DOMParser();
        return domParser.parse(xmlInput);
    }

    public static Beers parseXML(String path) throws Exception {
        InputStream xmlInput = new FileInputStream(path);

        DOMParser domParser = new DOMParser();
        return domParser.parse(xmlInput);
    }
}

