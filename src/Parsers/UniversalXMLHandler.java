package Parsers;

import Components.Beer;
import Components.BeerChar;
import Components.Beers;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLStreamConstants;
import java.util.ArrayList;
import java.util.List;

public class UniversalXMLHandler extends DefaultHandler {

    private Beers beers;
    private Beer currentBeer;
    private BeerChar currentBeerChar;
    private List<String> currentIngredients;
    private String currentText;

    private boolean insideIngredients = false;

    // Constructors

    public UniversalXMLHandler() {
        reset();
    }

    // Public methods

    public Beers getBeers() {
        return beers;
    }

    // Overridden methods for SAX parser

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        handleStartElement(qName);

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        handleCharacters(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        handleEndElement(qName);
    }

    // Methods for StAX parser

    public void handleStAXStartElement(String elementName) {
        handleStartElement(elementName);
    }

    public void handleStAXCharacters(String text) {
        handleCharacters(text);
    }

    public void handleStAXEndElement(String endElementName) {
        handleEndElement(endElementName);
    }

    // Methods for DOM parser

    public void handleDOMStartElement(String elementName) {
        handleStartElement(elementName);
    }

    public void handleDOMCharacters(String text) {
        handleCharacters(text);
    }

    public void handleDOMEndElement(String endElementName) {
        handleEndElement(endElementName);
    }

    // Private methods

    void reset() {
        beers = new Beers(new ArrayList<>());
        currentBeer = null;
        currentIngredients = null;
    }

    void handleStartElement(String elementName) {
        switch (elementName) {
            case "Beers":
                reset();
                break;
            case "Beer":
                currentBeer = new Beer();
                currentIngredients = new ArrayList<>();
                break;
            case "Char":
                currentBeerChar = new BeerChar();
                break;
        }
    }

    void handleCharacters(String text) {
        currentText = text.trim();
    }

    void handleEndElement(String endElementName) {
        switch (endElementName) {
            case "Name":
                currentBeer.setName(currentText);
                break;
            case "Type":
                currentBeer.setType(currentText);
                break;
            case "Alcoholic":
                currentBeer.setAlcoholic(Boolean.parseBoolean(currentText));
                break;
            case "Manufacturer":
                currentBeer.setManufacturer(currentText);
                break;
            case "Ingredient":
                currentIngredients.add(currentText);
                break;
            case "Turnover":
                currentBeerChar.setTurnover(Integer.parseInt(currentText));
                break;
            case "Transparency":
                currentBeerChar.setTransparency(Integer.parseInt(currentText));
                break;
            case "Filtered":
                currentBeerChar.setFiltered(Boolean.parseBoolean(currentText));
                break;
            case "Calories":
                currentBeerChar.setCalories(Integer.parseInt(currentText));
                break;
            case "Volume":
                currentBeerChar.setDispensingVolume(Integer.parseInt(currentText));
                break;
            case "Material":
                currentBeerChar.setDispensingMaterial(currentText);
                break;
            case "Char":
                currentBeer.getChars().add(currentBeerChar);
                break;
            case "Beer":
                currentBeer.setIngredients(currentIngredients);
                beers.getBeer().add(currentBeer);
                currentBeer = null;
                currentIngredients = null;
                break;
        }
    }

}
