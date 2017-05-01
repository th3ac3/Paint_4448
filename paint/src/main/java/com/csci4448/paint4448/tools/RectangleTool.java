package com.csci4448.paint4448.tools;

import com.csci4448.paint4448.shapes.Point;
import com.csci4448.paint4448.shapes.Rectangle;
import com.csci4448.paint4448.shapes.Style;
import org.apache.batik.dom.svg.SVGDOMImplementation;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Bi on 5/1/2017.
 */
public class RectangleTool extends Tool{

    private Rectangle outputShape;
    public RectangleTool()
    {
        editPath = null;
        outputShape = null;
        interactionComplete = true;
        lastEventPoint = null;
    }
    @Override
    public boolean startInteraction(InputEvent inputEvent) {
        if(inputEvent.getID() == MouseEvent.MOUSE_PRESSED )
        {
            return true;
        }
        else if(inputEvent.getID() == MouseEvent.MOUSE_RELEASED )
        {
            return true;
        }
        else if(inputEvent.getID() == MouseEvent.MOUSE_DRAGGED)
        {
            interactionToggle^=true;
            return interactionToggle;
        }
        return false;
    }

    @Override
    public boolean endInteraction() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            lastMouseDownPoint = e.getPoint();
            if(interactionComplete==true)
            {
                interactionComplete=false;
                outputShape = new Rectangle();
                editPath = jsvgCanvas.getSVGDocument().createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "rect");
                editPath.setAttributeNS(null,"x", String.format("%1$f", lastMouseDownPoint.getX()));
                editPath.setAttributeNS(null,"y", String.format("%1$f", lastMouseDownPoint.getY()));
                editPath.setAttributeNS(null,"width", "0");
                editPath.setAttributeNS(null,"height", "0");
                editPath.setAttributeNS(null,"stroke", currentStyle.getStroke());
                editPath.setAttributeNS(null,"fill", currentStyle.getFill());
                editPath.setAttributeNS(null,"stroke-width", currentStyle.getStrokeWidth());
                jsvgCanvas.getSVGDocument().getDocumentElement().appendChild(editPath);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!interactionComplete)
        {
            updateElement(e.getPoint());
            outputShape.setStartPoint(new Point(lastMouseDownPoint.getX(), lastMouseDownPoint.getY()));
            outputShape.setHeight(getHeight(e.getPoint()));
            outputShape.setWidth(getWidth(e.getPoint()));
            outputShape.setStyle(new Style(currentStyle));
            shapesContainer.add(outputShape);
            interactionComplete = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(interactionComplete == false)
        {
            updateElement(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private double getWidth(java.awt.Point point)
    {
        return (point.getX() - lastMouseDownPoint.getX());
    }
    private double getHeight(java.awt.Point point)
    {
        return (point.getY() - lastMouseDownPoint.getY());
    }

    private void updateElement(java.awt.Point point)
    {
        double width = getWidth(point);
        double height = getHeight(point);
        if(width<0)
        {
            editPath.setAttributeNS(null,"x", String.format("%1$f", point.getX()));
            width = Math.abs(width);
        }
        if(height<0)
        {
            editPath.setAttributeNS(null,"y", String.format("%1$f", point.getY()));
            height = Math.abs(height);
        }
        editPath.setAttributeNS(null,"width", String.format("%1$f", width));
        editPath.setAttributeNS(null,"height", String.format("%1$f", height));
    }
}
