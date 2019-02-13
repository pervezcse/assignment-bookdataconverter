/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdataconverter.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pervez
 */
public class XMLConverter extends Converter {

    private void parseNode(String parentName, Node node) {
        if (!node.hasChildNodes() && !node.getNodeValue().contains("\n")) {
            if (getBookDataProperties().containsKey(parentName)) {
                String currentValue = getBookDataProperties().getProperty(parentName)
                        + ", " + node.getNodeValue();
                getBookDataProperties().put(parentName, currentValue);
            } else {
                getBookDataProperties().put(parentName, node.getNodeValue());
            }
        } else {
            NodeList childNodeList = node.getChildNodes();
            for (int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);
                String fullName = parentName.isEmpty() ? node.getNodeName()
                        : parentName + "." + node.getNodeName();
                parseNode(fullName, childNode);
            }
        }
    }

    @Override
    public boolean load(InputStream is) {
        this.getBookDataProperties().clear();
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setErrorHandler(null);
            Document doc = builder.parse(is);
            NodeList nodeList = doc.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node childNode = nodeList.item(i);
                parseNode("", childNode);
            }
            return true;
        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException ex) {
//            Logger.getLogger(XMLConverter.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public void convert(PrintStream ps) {
        if (!this.getBookDataProperties().isEmpty() && ps != null) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("book");
                doc.appendChild(rootElement);
                Enumeration<?> enumeration = this.getBookDataProperties().propertyNames();
                while (enumeration.hasMoreElements()) {
                    String key = (String) enumeration.nextElement();
                    String value = this.getBookDataProperties().getProperty(key);
                    
                    Element element = doc.createElement(key);
                    if(key.contains("authors") && value.contains(",")) {
                        String[] authors = value.split(",");
                        for(String author: authors) {
                            if(!author.isEmpty()) {
                                Element authorElement = doc.createElement("author");
                                authorElement.appendChild(doc.createTextNode(author.trim()));
                                element.appendChild(authorElement);
                            }
                        }
                    } else {
                        element.appendChild(doc.createTextNode(value.trim()));
                    }
                    rootElement.appendChild(element);
                }
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(ps);
                transformer.transform(source, result);
            } catch (ParserConfigurationException | TransformerException ex) {
                Logger.getLogger(XMLConverter.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

}
