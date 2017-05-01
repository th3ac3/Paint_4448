package com.csci4448.paint4448.shapes;

import org.w3c.dom.Element;

import javax.persistence.Embeddable;

@Embeddable
public class Point {
    private double x, y;

    public Point()
    {
        x = 0;
        y = 0;
    }

    public Point(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
    public Point(Element elem) {
        if (!elem.getAttribute("x").equals(""))
            setX(Integer.parseInt(elem.getAttribute("x")));
        if (!elem.getAttribute("y").equals(""))
            setY(Integer.parseInt(elem.getAttribute("y")));
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
}
