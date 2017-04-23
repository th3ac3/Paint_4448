package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.StringWriter;

public class Shape implements Drawable, XML {
    public String id;
    public Style style;

    public Shape() {
        style = new Style();
    }

    public void rotate(float angle) {}
    public void fill(String rgbValue) {}

    @Override
    public String toXML() { return null; }

    @Override
    public void draw(Graphics g) { }

    @Override
    public void undraw(Graphics g) { }

    protected Document getDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error parsing to xml", e);
        }
    }

    // From http://stackoverflow.com/questions/2567416/xml-document-to-string
    protected static String docToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (TransformerException e) {
            throw new RuntimeException("Error converting to string", e);
        }
    }
}
