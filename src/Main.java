import Components.Beer;
import Components.Beers;
import Parsers.BeerDOMParser;
import check_sort.XmlValidator;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        try {

            String pathToFile = "src\\beers.xml";


                // SAX Parser
               System.out.println("SAX Parser:");
                Beers saxBeers = Parsers.SAXParser.parseBeerXml(pathToFile);
                printBeers(saxBeers);

                 // StAX Parser
                System.out.println("\nStAX Parser:");
                Parsers.BeerStAXParser staxParser = new Parsers.BeerStAXParser();
                Beers staxBeers = staxParser.parse(pathToFile);
                printBeers(staxBeers);

                // DOM Parser
                System.out.println("\nDOM Parser:");
                Beers domBeers = BeerDOMParser.parseXML(pathToFile);
                printBeers(domBeers);

                // Validate XML against XSD
                String xmlFilePath = "src\\beers.xml";
                String xsdFilePath = "beers.xsd";

                if (XmlValidator.validateXmlAgainstXsd(xmlFilePath, xsdFilePath)) {
                    System.out.println("\nXML is valid against XSD.");
                } else {
                    System.out.println("\nXML is not valid against XSD.");
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printBeers(Beers beers) {
        if (beers != null) {
            for (Beer beer : beers.getBeer()) {
                System.out.println(beer);
            }
        }
    }
}