package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@MappedSuperclass
public class Shape implements XML {
    @Id
    private long id;
    private Style style;
    private Transform transform;

    public Shape() {
        setStyle(new Style());
        setTransform(new Transform());
    }

    public Shape(Element elem) {
        if (!elem.getAttribute("style").equals(""))
            setStyle(new Style(elem.getAttribute("style")));
        else
            setStyle(new Style());

        if (!elem.getAttribute("transform").equals(""))
            setTransform(new Transform(elem.getAttribute("transform")));
        else
            setTransform(new Transform());
    }

    public void rotate(int angle) {
        transform.setRotate(angle + "");
    }

    public void rotate(int angle, int canvasWidth, int canvasHeight) {
        transform.setRotate(angle + " " + canvasWidth / 2 + " " + canvasHeight / 2);
    }

    public void fill(String rgbValue) {
        style.setFill(rgbValue);
    }

    @Override
    public String toXML() { return null; }

    Document getDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error parsing to xml", e);
        }
    }

    // From http://stackoverflow.com/questions/2567416/xml-document-to-string
    static String docToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (TransformerException e) {
            throw new RuntimeException("Error converting to string", e);
        }
    }

    void setGlobalAttributes(Element elem) {
        if (!style.toString().equals(""))
            elem.setAttribute("style", style.toString());
        if (!transform.toString().equals(""))
            elem.setAttribute("transform", transform.toString());
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Style getStyle() {
        return style;
    }
    public void setStyle(Style style) {
        this.style = style;
    }

    public Transform getTransform() {
        return transform;
    }
    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
