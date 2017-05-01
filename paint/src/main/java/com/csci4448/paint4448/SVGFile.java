package com.csci4448.paint4448;

import com.csci4448.paint4448.shapes.Ellipse;
import com.csci4448.paint4448.shapes.Rectangle;
import com.csci4448.paint4448.shapes.Shape;
import com.csci4448.paint4448.shapes.Transform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SVGFile {
    private File file;
    private Canvas canvas;

    public SVGFile(Canvas canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

    void save(String svgData){
        try (PrintWriter out = new PrintWriter(file)){
            out.println(svgData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void open(){
        try {
            // From https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(file);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            canvas.parse(root);

            NodeList nodes = root.getChildNodes();
            ArrayList<Shape> shapes = new ArrayList<>();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node item = nodes.item(i);
                if (item.getNodeType() != Node.ELEMENT_NODE) continue;

                Element shape = (Element) item;
                switch (shape.getTagName()) {
                    case "rect":
                        shapes.add(new Rectangle(shape));
                        break;
                    case "ellipse":
                        shapes.add(new Ellipse(shape));
                        break;
                }
            }

            canvas.setShapes(shapes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
