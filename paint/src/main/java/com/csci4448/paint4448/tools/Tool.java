package com.csci4448.paint4448.tools;

import com.csci4448.paint4448.shapes.Shape;
import com.csci4448.paint4448.shapes.Style;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.Interactor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import java.util.ArrayList;

/**
 * Created by Bi on 4/30/2017.
 */
abstract public class Tool implements Interactor{

    protected JSVGCanvas jsvgCanvas;
    protected ArrayList<Shape> shapesContainer;
    protected Style currentStyle;
    protected boolean interactionComplete;
    protected Element editPath;
    protected java.awt.Point lastEventPoint;
    protected java.awt.Point lastMouseDownPoint;
    protected boolean interactionToggle;
    public void setCanvas(JSVGCanvas canvas)
    {
        jsvgCanvas = canvas;
    }
    public void setShapesContainer(ArrayList<Shape> shapes)
    {
        shapesContainer = shapes;
    }
    public void setStyle(Style style)
    {
        currentStyle = style;
    }
}
