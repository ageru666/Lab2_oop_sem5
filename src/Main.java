import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import Components.Beer;
import Components.Beers;
import Parsers.DOMParser;
import check_sort.XmlValidator;
import check_sort.BeerComparator;
import Parsers.UniversalXMLHandler;
import Parsers.SAXParser;
public class Main {

    public static void main(String[] args) {
        try {
            String pathToFile = "src\\beers.xml";
            UniversalXMLHandler universalHandler = new UniversalXMLHandler();
            // SAX Parser
            System.out.println("SAX Parser:");
            SAXParser saxParser = new SAXParser(universalHandler);
            Beers saxBeers = saxParser.parseBeerXml(pathToFile);
            printBeers(saxBeers);

            System.out.println("\nStAX Parser:");
            Parsers.BeerStAXParser staxParser = new Parsers.BeerStAXParser(universalHandler);
            InputStream xmlInput = new FileInputStream(pathToFile);
            Beers staxBeers = staxParser.parse(xmlInput);
            printBeers(staxBeers);

            System.out.println("\nDOM Parser:");
            DOMParser domParser = new DOMParser(universalHandler);
            Beers domBeers = domParser.parse(new FileInputStream(pathToFile));
            printBeers(domBeers);


            // Validate XML against XSD
            String xmlFilePath = "src\\beers.xml";
            String xsdFilePath = "beers.xsd";

            if (XmlValidator.validateXmlAgainstXsd(xmlFilePath, xsdFilePath)) {
                System.out.println("\nXML is valid against XSD.");
            } else {
                System.out.println("\nXML is not valid against XSD.");
            }

            // Call comparator
            System.out.println("\nSorting beers by name:");
            List<Beer> beerList = saxBeers.getBeer(); // You can use staxBeers or domBeers as well
            Collections.sort(beerList, new BeerComparator("name"));
            printBeers(new Beers(beerList));

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
