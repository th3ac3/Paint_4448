package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.Entity;

@Entity
public class Ellipse extends Shape {
    private float rx;
    private float ry;
    private Point startPoint;

    public Ellipse() {
        setRx(10);
        setRy(10);
        setStartPoint(new Point());
    }

    public Ellipse(Element elem) {
        super(elem);
        setStartPoint(new Point());

        if (!elem.getAttribute("rx").equals(""))
            setRx(Float.parseFloat(elem.getAttribute("rx")));
        if (!elem.getAttribute("ry").equals(""))
            setRy(Float.parseFloat(elem.getAttribute("ry")));

        if (!elem.getAttribute("cx").equals(""))
            startPoint.setX(Float.parseFloat(elem.getAttribute("cx")));
        if (!elem.getAttribute("cy").equals(""))
            startPoint.setY(Float.parseFloat(elem.getAttribute("cy")));
    }

    @Override
    public String toXML() {
        Document doc = getDocument();

        Element rect = doc.createElement("ellipse");

        rect.setAttribute("cx", startPoint.getX() + "");
        rect.setAttribute("cy", startPoint.getY() + "");

        rect.setAttribute("rx", getRx() + "");
        rect.setAttribute("ry",getRy() + "");

        setGlobalAttributes(rect);

        doc.appendChild(rect);

        return docToString(doc);
    }

    public float getRx() {
        return rx;
    }
    public void setRx(float rx) {
        this.rx = rx;
    }

    public float getRy() {
        return ry;
    }
    public void setRy(float ry) {
        this.ry = ry;
    }

    public Point getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
}
