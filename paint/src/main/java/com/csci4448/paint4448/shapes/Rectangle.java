package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Rectangle extends Shape {
    public int width;
    public int height;
    public Point startPoint;

    public Rectangle() {
        startPoint = new Point();
        width = 10;
        height = 10;
    }

    @Override
    public String toXML() {
        Document doc = getDocument();

        Element rect = doc.createElement("rect");

        rect.setAttribute("x", startPoint.x + "");
        rect.setAttribute("y", startPoint.y + "");

        rect.setAttribute("width", width + "");
        rect.setAttribute("height",height + "");

        setGlobalAttributes(doc);

        doc.appendChild(rect);

        return docToString(doc);
    }

    @Override
    public void draw(Graphics g) {
        if (style.fill != null)
            g.setColor(style.getColor(style.fill));
        else
            g.setColor(Color.BLACK);

        g.fillRect(startPoint.x, startPoint.y,
                startPoint.x + width, startPoint.y + height);
    }

    @Override
    public void undraw(Graphics g) { }
}
