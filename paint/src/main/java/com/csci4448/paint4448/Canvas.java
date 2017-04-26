package com.csci4448.paint4448;

import com.csci4448.paint4448.shapes.Rectangle;
import com.csci4448.paint4448.shapes.Shape;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Canvas {
    private static final String SVG_HEADER = "<svg xmlns=\"http://www.w3.org/2000/svg\" " +
            "xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"%s\" height=\"%s\">\n";
    private static final String SVG_FOOTER = "</svg>";
    private ArrayList<Shape> shapes;
    private String svgData;
    private JSVGCanvas jsvgCanvas;
    private int width;
    private int height;

    Canvas(JPanel panel, int width, int height) {
        this.width = width;
        this.height = height;

        shapes = new ArrayList<>();

        jsvgCanvas = new JSVGCanvas();
        draw();
        panel.add(jsvgCanvas);
    }

    public void draw() {
        svgData = getSvgHeader();
        for (Shape shape : shapes)
            svgData += "\t" + shape.toXML();
        svgData += SVG_FOOTER;
        System.out.println(svgData);

        // From http://stackoverflow.com/questions/30824711/can-i-create-a-jsvgcanvas-without-an-svg-file
        try {
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(
                    XMLResourceDescriptor.getXMLParserClassName());
            SVGDocument document = factory.createSVGDocument("",
                    new ByteArrayInputStream(svgData.getBytes("UTF-8")));

            jsvgCanvas.setSVGDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSvgHeader() {
        return String.format(SVG_HEADER, width, height);
    }

    public void open(File file) {
        jsvgCanvas.setURI(String.valueOf(file.toURI()));
    }

    public void save(File file) {
        draw(); // Make sure svgData is up to date

        try (PrintWriter out = new PrintWriter(file)){
            out.println(svgData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void rotate(float angle){}
    public void resizeCanvas(int newWidth, int newHeight){
        width = newWidth;
        height = newHeight;
        draw();
    }
    public void crop(int x1, int y1, int x2, int y2){}
}
