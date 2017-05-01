package com.csci4448.paint4448.shapes;

import org.w3c.dom.Element;

import javax.persistence.Embeddable;

@Embeddable
public class Point {
    private int x, y;

    public Point(){}

    public Point(Element elem) {
        if (!elem.getAttribute("x").equals(""))
            setX(Integer.parseInt(elem.getAttribute("x")));
        if (!elem.getAttribute("y").equals(""))
            setY(Integer.parseInt(elem.getAttribute("y")));
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
