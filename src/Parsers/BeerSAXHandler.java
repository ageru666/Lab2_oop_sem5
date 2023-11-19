package Parsers;

import Components.Beer;
import Components.BeerChar;
import Components.Beers;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class BeerSAXHandler extends DefaultHandler {
    private Beers beers;
    private Beer currentBeer;
    private BeerChar currentBeerChar;
    private StringBuilder data;

    public Beers getBeers() {
        return beers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();

        if ("Beers".equals(qName)) {
            beers = new Beers(new ArrayList<>());
        } else if ("Beer".equals(qName)) {
            currentBeer = new Beer();
        } else if ("Char".equals(qName)) {
            currentBeerChar = new BeerChar();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String value = data.toString().trim();

        switch (qName) {
            case "Name":
                currentBeer.setName(value);
                break;
            case "Type":
                currentBeer.setType(value);
                break;
            case "Alcoholic":
                currentBeer.setAlcoholic(Boolean.parseBoolean(value));
                break;
            case "Manufacturer":
                currentBeer.setManufacturer(value);
                break;
            case "Ingredient":
                currentBeer.getIngredients().add(value);
                break;
            case "Turnover":
                currentBeerChar.setTurnover(Integer.parseInt(value));
                break;
            case "Transparency":
                currentBeerChar.setTransparency(Integer.parseInt(value));
                break;
            case "Filtered":
                currentBeerChar.setFiltered(Boolean.parseBoolean(value));
                break;
            case "Calories":
                currentBeerChar.setCalories(Integer.parseInt(value));
                break;
            case "Volume":
                currentBeerChar.setDispensingVolume(Integer.parseInt(value));
                break;
            case "Material":
                currentBeerChar.setDispensingMaterial(value);
                break;
            case "Char":
                currentBeer.getChars().add(currentBeerChar);
                break;
            case "Beer":
                beers.getBeer().add(currentBeer);
                break;
        }
    }
}