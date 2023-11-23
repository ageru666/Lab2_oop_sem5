package tests;

import Parsers.SAXParser;
import Components.Beer;
import Components.Beers;
import Components.BeerChar;
import Parsers.UniversalXMLHandler;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeerParserTest {

    @Test
    public void parseBeerXmlTest() throws Exception {
        UniversalXMLHandler universalHandler = new UniversalXMLHandler();
        SAXParser saxParser = new SAXParser(universalHandler);
        Beers beers = saxParser.parseBeerXml("src//beers.xml");
        List<Beer> beerList = beers.getBeer();

        assertEquals(3, beerList.size());

        Beer beer1 = new Beer();
        beer1.setName("Stout");
        beer1.setType("Dark");
        beer1.setAlcoholic(true);
        beer1.setManufacturer("Example Brewery");
        beer1.setIngredients(List.of("Water", "Malt", "Hops"));
        beer1.setChars(List.of(new BeerChar(3000, 80, true, 200, 500, "Glass")));

        Beer beer2 = new Beer();
        beer2.setName("IPA");
        beer2.setType("India Pale Ale");
        beer2.setAlcoholic(true);
        beer2.setManufacturer("Another Brewery");
        beer2.setIngredients(List.of("Water", "Malt", "Hops"));
        beer2.setChars(List.of(new BeerChar(4000, 75, true, 220, 330, "Bottle")));

        Beer beer3 = new Beer();
        beer3.setName("Pilsner");
        beer3.setType("Light");
        beer3.setAlcoholic(true);
        beer3.setManufacturer("Yet Another Brewery");
        beer3.setIngredients(List.of("Water", "Malt", "Hops"));
        beer3.setChars(List.of(new BeerChar(3500, 85, true, 180, 500, "Can")));

        assertEquals(beer1, beerList.get(0));
        assertEquals(beer2, beerList.get(1));
        assertEquals(beer3, beerList.get(2));
    }
}
