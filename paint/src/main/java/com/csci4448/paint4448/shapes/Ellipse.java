package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.Entity;

@Entity
public class Ellipse extends Shape {
    private int rx;
    private int ry;
    private Point startPoint;

    public Ellipse() {
        setRx(10);
        setRy(10);
        setStartPoint(new Point());
    }

    public Ellipse(Element elem) {
        super(elem);
        setStartPoint(new Point());

        if (!elem.getAttribute("cx").equals(""))
            startPoint.setX(Integer.parseInt(elem.getAttribute("cx")));
        if (!elem.getAttribute("cy").equals(""))
            startPoint.setY(Integer.parseInt(elem.getAttribute("cy")));

        if (!elem.getAttribute("rx").equals(""))
            setRx(Integer.parseInt(elem.getAttribute("rx")));
        if (!elem.getAttribute("ry").equals(""))
            setRy(Integer.parseInt(elem.getAttribute("ry")));
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

    public int getRx() {
        return rx;
    }
    public void setRx(int rx) {
        this.rx = rx;
    }

    public int getRy() {
        return ry;
    }
    public void setRy(int ry) {
        this.ry = ry;
    }

    public Point getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
}
