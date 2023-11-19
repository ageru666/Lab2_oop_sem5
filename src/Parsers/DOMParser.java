package Parsers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import Components.Beer;
import Components.BeerChar;
import Components.Beers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

class DOMParser {
    public Beers parse(InputStream xmlInput) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlInput);

        Beers beers = new Beers(new ArrayList<>());

        NodeList beerNodes = document.getElementsByTagName("Beer");
        for (int i = 0; i < beerNodes.getLength(); i++) {
            Node beerNode = beerNodes.item(i);
            if (beerNode.getNodeType() == Node.ELEMENT_NODE) {
                Element beerElement = (Element) beerNode;
                Beer beer = parseBeerElement(beerElement);
                beers.getBeer().add(beer);
            }
        }

        return beers;
    }

    private Beer parseBeerElement(Element beerElement) {
        Beer beer = new Beer();

        beer.setName(getElementText(beerElement, "Name"));
        beer.setType(getElementText(beerElement, "Type"));
        beer.setAlcoholic(Boolean.parseBoolean(getElementText(beerElement, "Alcoholic")));
        beer.setManufacturer(getElementText(beerElement, "Manufacturer"));

        List<String> ingredients = new ArrayList<>();
        NodeList ingredientNodes = beerElement.getElementsByTagName("Ingredient");
        for (int i = 0; i < ingredientNodes.getLength(); i++) {
            ingredients.add(ingredientNodes.item(i).getTextContent());
        }
        beer.setIngredients(ingredients);

        BeerChar beerChar = new BeerChar();
        beerChar.setTurnover(Integer.parseInt(getElementText(beerElement, "Turnover")));
        beerChar.setTransparency(Integer.parseInt(getElementText(beerElement, "Transparency")));
        beerChar.setFiltered(Boolean.parseBoolean(getElementText(beerElement, "Filtered")));
        beerChar.setCalories(Integer.parseInt(getElementText(beerElement, "Calories")));
        beerChar.setDispensingVolume(Integer.parseInt(getElementText(beerElement, "Volume")));
        beerChar.setDispensingMaterial(getElementText(beerElement, "Material"));

        beer.getChars().add(beerChar);

        return beer;
    }

    private String getElementText(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return "";
    }
}