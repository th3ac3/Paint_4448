package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.Entity;

@Entity
public class Rectangle extends Shape {
    private double width;
    private double height;
    private Point startPoint;

    public Rectangle() {
        setStartPoint(new Point());
        setWidth(10);
        setHeight(10);
    }

    public Rectangle(Element elem) {
        super(elem);
        setStartPoint(new Point(elem));

        if (!elem.getAttribute("width").equals(""))
            setWidth(Double.parseDouble(elem.getAttribute("width")));
        if (!elem.getAttribute("height").equals(""))
            setHeight(Double.parseDouble(elem.getAttribute("height")));
    }

    @Override
    public String toXML() {
        Document doc = getDocument();

        Element rect = doc.createElement("rect");

        rect.setAttribute("x", startPoint.getX() + "");
        rect.setAttribute("y", startPoint.getY() + "");

        rect.setAttribute("width", getWidth() + "");
        rect.setAttribute("height",getHeight() + "");

        setGlobalAttributes(rect);

        doc.appendChild(rect);

        return docToString(doc);
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public Point getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
}
