package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Ellipse extends Shape {
    public float rx;
    public float ry;
    public Point startPoint;

    @Override
    public String toXML() {
        Document doc = getDocument();

        Element rect = doc.createElement("ellipse");

        rect.setAttribute("cx", startPoint.x + "");
        rect.setAttribute("cy", startPoint.y + "");

        rect.setAttribute("rx", rx + "");
        rect.setAttribute("ry",ry + "");

        setGlobalAttributes(doc);

        doc.appendChild(rect);

        return docToString(doc);
    }

    @Override
    public void draw(Graphics g) { }

    @Override
    public void undraw(Graphics g) { }
}
