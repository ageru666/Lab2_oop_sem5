package Parsers;

import Components.Beers;
import Parsers.UniversalXMLHandler;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParser {

    private UniversalXMLHandler handler;

    public DOMParser(UniversalXMLHandler handler) {
        this.handler = handler;
    }

    public Beers parse(InputStream xmlInput) throws Exception {
        handler.reset();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlInput);

        NodeList beerNodes = document.getElementsByTagName("Beer");
        for (int i = 0; i < beerNodes.getLength(); i++) {
            Node beerNode = beerNodes.item(i);
            if (beerNode.getNodeType() == Node.ELEMENT_NODE) {
                Element beerElement = (Element) beerNode;
                handler.handleDOMStartElement("Beer");
                parseBeerElement(beerElement);
                handler.handleDOMEndElement("Beer");
            }
        }

        return handler.getBeers();
    }

    private void parseBeerElement(Element beerElement) {

        NodeList nameNodes = beerElement.getElementsByTagName("Name");
        if (nameNodes.getLength() > 0) {
            handler.handleDOMStartElement("Name");
            handler.handleDOMCharacters(getElementText(beerElement, "Name"));
            handler.handleDOMEndElement("Name");
        }

        handler.handleDOMStartElement("Type");
        handler.handleDOMCharacters(getElementText(beerElement, "Type"));
        handler.handleDOMEndElement("Type");

        handler.handleDOMStartElement("Alcoholic");
        handler.handleDOMCharacters(getElementText(beerElement, "Alcoholic"));
        handler.handleDOMEndElement("Alcoholic");

        handler.handleDOMStartElement("Manufacturer");
        handler.handleDOMCharacters(getElementText(beerElement, "Manufacturer"));
        handler.handleDOMEndElement("Manufacturer");

        parseIngredients(beerElement);

        handler.handleDOMStartElement("Char");
        parseCharElement(beerElement);
        handler.handleDOMEndElement("Char");
    }


    private void parseCharElement(Element beerElement) {
        handler.handleDOMStartElement("Turnover");
        handler.handleDOMCharacters(getElementText(beerElement, "Turnover"));
        handler.handleDOMEndElement("Turnover");

        handler.handleDOMStartElement("Transparency");
        handler.handleDOMCharacters(getElementText(beerElement, "Transparency"));
        handler.handleDOMEndElement("Transparency");

        handler.handleDOMStartElement("Filtered");
        handler.handleDOMCharacters(getElementText(beerElement, "Filtered"));
        handler.handleDOMEndElement("Filtered");

        handler.handleDOMStartElement("Calories");
        handler.handleDOMCharacters(getElementText(beerElement, "Calories"));
        handler.handleDOMEndElement("Calories");

        handler.handleDOMStartElement("Volume");
        handler.handleDOMCharacters(getElementText(beerElement, "Volume"));
        handler.handleDOMEndElement("Volume");

        handler.handleDOMStartElement("Material");
        handler.handleDOMCharacters(getElementText(beerElement, "Material"));
        handler.handleDOMEndElement("Material");
    }

    private void parseIngredients(Element beerElement) {
        NodeList ingredientNodes = beerElement.getElementsByTagName("Ingredient");

        for (int i = 0; i < ingredientNodes.getLength(); i++) {
            Node ingredientNode = ingredientNodes.item(i);
            if (ingredientNode.getNodeType() == Node.ELEMENT_NODE) {
                Element ingredientElement = (Element) ingredientNode;
                NodeList textNodes = ingredientElement.getChildNodes();
                StringBuilder text = new StringBuilder();
                for (int j = 0; j < textNodes.getLength(); j++) {
                    Node node = textNodes.item(j);
                    if (node.getNodeType() == Node.TEXT_NODE) {
                        text.append(node.getNodeValue().trim());
                    }
                }
                handler.handleDOMStartElement("Ingredient");
                handler.handleDOMCharacters(text.toString());
                handler.handleDOMEndElement("Ingredient");
            }
        }
    }




    private String getElementText(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            NodeList textNodes = nodeList.item(0).getChildNodes();
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < textNodes.getLength(); i++) {
                Node node = textNodes.item(i);
                if (node.getNodeType() == Node.TEXT_NODE) {
                    text.append(node.getNodeValue().trim());
                }
            }
            return text.toString();
        }
        return "";
    }

}
