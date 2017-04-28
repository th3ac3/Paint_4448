package com.csci4448.paint4448;

import com.csci4448.paint4448.shapes.Rectangle;
import com.csci4448.paint4448.shapes.Shape;
import com.csci4448.paint4448.shapes.Transform;
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
            "xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"%s\" height=\"%s\" transform=\"%s\">\n";
    private static final String SVG_FOOTER = "</svg>";
    private ArrayList<Shape> shapes;
    private String svgData;
    private JSVGCanvas jsvgCanvas;
    private int width;
    private int height;
    private Transform transform;

    Canvas(JPanel panel, int width, int height) {
        this.width = width;
        this.height = height;
        transform = new Transform();

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
        return String.format(SVG_HEADER, width, height, transform);
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

    public void rotate(int angle){
        angle %= 360;

        if (angle != 0 && angle != 90 && angle != 180 && angle != 270) return;
        if (angle == 0) return;

        // Need to swap width and height if rotating 90 or 270
        if (angle == 90 || angle == 270) {
            int temp = width;
            width = height;
            height = temp;
        }

        transform.rotate = angle + " " + width / 2 + " " + height / 2;

        draw();
    }

    public void resizeCanvas(int newWidth, int newHeight){
        width = newWidth;
        height = newHeight;
        draw();
    }

    public void crop(int x1, int y1, int x2, int y2){}
}
