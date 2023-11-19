package Parsers;

import Components.Beer;
import Components.BeerChar;
import Components.Beers;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BeerStAXHandler extends DefaultHandler {

    private Beers beers;
    private Beer currentBeer;
    private BeerChar currentBeerChar;
    private List<String> currentIngredients;
    private String currentText;

    public Beers parseXML(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    handleStartElement(reader.getLocalName());
                    break;
                case XMLStreamConstants.CHARACTERS:
                    handleCharacters(reader.getText());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    handleEndElement(reader.getLocalName());
                    break;
            }
        }

        return beers;
    }

    private void handleStartElement(String elementName) {
        switch (elementName) {
            case "Beers":
                beers = new Beers(new ArrayList<>());
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

    private void handleCharacters(String text) {
        currentText = text.trim();
    }

    private void handleEndElement(String endElementName) throws XMLStreamException {
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
                break;
        }
    }
}
