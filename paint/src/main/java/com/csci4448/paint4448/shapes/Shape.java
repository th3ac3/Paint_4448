package com.csci4448.paint4448.shapes;

import com.csci4448.paint4448.Canvas;

public class Shape implements Drawable, XML {
    public String id;
    private Style style;

    public void rotate(float angle) {}
    public void fill(String rgbValue) {}

    @Override
    public String toXML() { return null; }

    @Override
    public void draw(Canvas canvas) { }

    @Override
    public void undraw(Canvas canvas) { }
}
