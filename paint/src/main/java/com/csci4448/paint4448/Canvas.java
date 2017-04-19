package com.csci4448.paint4448;

import com.csci4448.paint4448.shapes.Drawable;

import java.util.ArrayList;

public class Canvas {
    private ArrayList<Drawable> drawables;
    private SVGFile svgFile;

    public void drawObjects(){
        for (Drawable drawable : drawables)
            drawable.draw(this);
    }

    public void rotate(float angle){}
    public void resize(int newWidth, int newHeight){}
    public void crop(int x1, int y1, int x2, int y2){}
}
