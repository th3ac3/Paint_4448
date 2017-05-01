package com.csci4448.paint4448.tools;

import com.csci4448.paint4448.shapes.Ellipse;
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
public class EllipseTool extends Tool{
    private Ellipse outputShape;

    public EllipseTool()
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
                outputShape = new Ellipse();
                editPath = jsvgCanvas.getSVGDocument().createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "ellipse");
                editPath.setAttributeNS(null,"cx", String.format("%1$f", lastMouseDownPoint.getX()));
                editPath.setAttributeNS(null,"cy", String.format("%1$f", lastMouseDownPoint.getY()));
                editPath.setAttributeNS(null,"rx", "0");
                editPath.setAttributeNS(null,"ry", "0");
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
            outputShape.setRx((float)getRx(e.getPoint()));
            outputShape.setRy((float)getRy(e.getPoint()));
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

    private double getRx(java.awt.Point point)
    {
        return (point.getX() - lastMouseDownPoint.getX());
    }
    private double getRy(java.awt.Point point)
    {
        return (point.getY() - lastMouseDownPoint.getY());
    }

    private void updateElement(java.awt.Point point)
    {
        double rx = getRx(point);
        double ry = getRy(point);
        if(rx<0)
        {
            editPath.setAttributeNS(null,"cx", String.format("%1$f", point.getX()));
            rx = Math.abs(rx);
        }
        if(ry<0)
        {
            editPath.setAttributeNS(null,"cy", String.format("%1$f", point.getY()));
            ry = Math.abs(ry);
        }
        editPath.setAttributeNS(null,"rx", String.format("%1$f", rx));
        editPath.setAttributeNS(null,"ry", String.format("%1$f", ry));
    }
}
