package com.csci4448.paint4448;

import com.csci4448.paint4448.shapes.Drawable;
import com.csci4448.paint4448.shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private ArrayList<Drawable> drawables;
    private SVGFile svgFile;
    private int width;
    private int height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        Rectangle rectangle = new Rectangle();
        rectangle.width = 100;
        rectangle.height = 50;
        rectangle.style.fill = "#FF0000";

        System.out.println(rectangle.toXML());

        drawables = new ArrayList<>();
        drawables.add(rectangle);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void drawObjects(Graphics g){
        for (Drawable drawable : drawables)
            drawable.draw(g);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        drawObjects(g);
    }

    public void rotate(float angle){}
    public void resizeCanvas(int newWidth, int newHeight){}
    public void crop(int x1, int y1, int x2, int y2){}
}
