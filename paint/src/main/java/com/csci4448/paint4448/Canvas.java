package com.csci4448.paint4448;

import com.csci4448.paint4448.shapes.Ellipse;
import com.csci4448.paint4448.shapes.Rectangle;
import com.csci4448.paint4448.shapes.Shape;
import com.csci4448.paint4448.tools.Tool;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.AbstractJSVGComponent;
import org.apache.batik.util.XMLResourceDescriptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

public class Canvas {
    private static final String SVG_HEADER = "<svg xmlns=\"http://www.w3.org/2000/svg\" " +
            "xmlns:xlink=\"http://www.w3.org/1999/xlink\"%s>\n";
    private static final String SVG_FOOTER = "</svg>";
    private SVGFile svgFile;
    private JSVGCanvas jsvgCanvas;
    private JPanel mPanel;
    private ArrayList<Shape> shapes;
    private int width;
    private int height;
    private Tool currentTool = null;

    Canvas(JPanel panel, int width, int height) {
        this.width = width;
        this.height = height;

        shapes = new ArrayList<>();

        jsvgCanvas = new JSVGCanvas();
        draw();
        panel.add(jsvgCanvas);
        mPanel = panel;
    }

    public void draw() {
        String svgData = getSvg();
        System.out.println(svgData);

        // From http://stackoverflow.com/questions/30824711/can-i-create-a-jsvgcanvas-without-an-svg-file
        try {
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(
                    XMLResourceDescriptor.getXMLParserClassName());
            SVGDocument document = factory.createSVGDocument(SVGDOMImplementation.SVG_NAMESPACE_URI,
                    new ByteArrayInputStream(svgData.getBytes("UTF-8")));

            jsvgCanvas.setDocumentState(AbstractJSVGComponent.ALWAYS_DYNAMIC);
            jsvgCanvas.setSVGDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTool(Tool tool)
    {
        if(currentTool != null)
        {
            jsvgCanvas.getInteractors().remove(currentTool);
        }
        currentTool = tool;
        tool.setCanvas(jsvgCanvas);
        tool.setShapesContainer(shapes);
        jsvgCanvas.getInteractors().add(tool);
    }

    private String getSvg() {
        String svgData = getSvgHeader();
        for (Shape shape : shapes)
            svgData += "\t" + shape.toXML();

        svgData += SVG_FOOTER;

        return svgData;
    }

    private String getSvgHeader() {
        String headerAttr = "";

        if (width != -1)
            headerAttr += " width=\"" + width + "\"";
        if (height != -1)
            headerAttr += " height=\"" + height + "\"";

        return String.format(SVG_HEADER, headerAttr);
    }

    public void open(File file) {
        width = -1;
        height = -1;

        svgFile = new SVGFile(this, file);
        svgFile.open();

        draw();
    }

    public void save(File file) {
        draw(); // Make sure our svg is up to date

        svgFile = new SVGFile(this, file);
        svgFile.save(getSvg());
    }

    public void saveDB() {
        Paint paint = Paint.getInstance();
        SessionFactory factory = paint.getFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        for (Shape shape : shapes) {
            if (shape instanceof Rectangle || shape instanceof Ellipse) session.save(shape);
        }

        tx.commit();
        session.close();
    }

    void parse(Element root) {
        if (root.getNodeName().equals("svg")) {
            if (!root.getAttribute("width").equals(""))
                width = Integer.parseInt(root.getAttribute("width"));

            if (!root.getAttribute("height").equals(""))
                height = Integer.parseInt(root.getAttribute("height"));
        }
    }

    public void rotate(int angle){
        if (angle != 0 && angle != 90 && angle != 180 && angle != 270) return;
        if (angle == 0) return;

        // Need to swap width and height if rotating 90 or 270
        if (angle == 90 || angle == 270) {
            int temp = width;
            width = height;
            height = temp;
        }

        for (Shape shape : shapes) {
            if (width != -1 && height != -1) shape.rotate(angle, width, height);
            else shape.rotate(angle);
        }

        draw();
    }

    public void resizeCanvas(int newWidth, int newHeight){
        width = newWidth;
        height = newHeight;
        draw();
    }

    public void crop(int x1, int y1, int x2, int y2){}

    public void setShapes(ArrayList<Shape> shapes) {
        if(this.currentTool!=null)
            currentTool.setShapesContainer(shapes);
        this.shapes = shapes;
    }
}
