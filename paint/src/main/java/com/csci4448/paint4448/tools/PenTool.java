package com.csci4448.paint4448.tools;

import org.apache.batik.dom.svg.SVGDOMImplementation;

import com.csci4448.paint4448.shapes.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Created by Bi on 4/30/2017.
 */
public class PenTool extends Tool {

    private Path outputShape;
    private static final int PEN_DRAWING_LINE = 0;
    private static final int PEN_DRAWING_CURVE = 1;
    private int penDrawingStatus;
    private ArrayList<String> pathCommands;
    public PenTool()
    {
        pathCommands = new ArrayList<>();
        editPath = null;
        outputShape = null;
        interactionComplete = true;
        lastEventPoint = null;
        penDrawingStatus = -1;
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
        else if(inputEvent.getID() == MouseEvent.MOUSE_CLICKED )
        {
            return true;
        }
        else if(inputEvent.getID() == MouseEvent.MOUSE_MOVED)
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
        if(interactionComplete == false && e.getButton() == MouseEvent.BUTTON3)
        {
            interactionComplete = true;
            editPath.setAttributeNS(null,"d", printPathCommands());
            outputShape.setPathCommands(new ArrayList<>(pathCommands));
            outputShape.setStyle(new Style(currentStyle));
            shapesContainer.add(outputShape);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            lastMouseDownPoint = e.getPoint();
            if(interactionComplete==true)
            {
                interactionComplete=false;
                penDrawingStatus = -1;
                editPath = jsvgCanvas.getSVGDocument().createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "path");
                outputShape = new Path();
                pathCommands.clear();
                pathCommands.add(String.format("M%1$f %2$f", lastMouseDownPoint.getX(), lastMouseDownPoint.getY()));
                editPath.setAttributeNS(null,"d", printPathCommands());
                editPath.setAttributeNS(null,"stroke", currentStyle.getStroke());
                editPath.setAttributeNS(null,"fill", currentStyle.getFill());
                editPath.setAttributeNS(null,"stroke-width", currentStyle.getStrokeWidth());
                jsvgCanvas.getSVGDocument().getDocumentElement().appendChild(editPath);
            }
            else
            {
                switch(penDrawingStatus)
                {
                    case PEN_DRAWING_LINE:
                        pathCommands.add(String.format("L%1$f %2$f", e.getPoint().getX(), e.getPoint().getY()));
                        editPath.setAttributeNS(null,"d", printPathCommands());
                        break;
                    case PEN_DRAWING_CURVE:
                        pathCommands.add(String.format("L%1$f %2$f", e.getPoint().getX(), e.getPoint().getY()));
                        editPath.setAttributeNS(null,"d", printPathCommands());
                        break;
                    default:
                        break;

                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!interactionComplete)
        {
            if(e.getPoint().equals(lastMouseDownPoint))
            {
                penDrawingStatus = PEN_DRAWING_LINE;
            }
            else
            {
                penDrawingStatus = PEN_DRAWING_CURVE;
            }
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

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(interactionComplete == false)
        {
            switch (penDrawingStatus)
            {
                case PEN_DRAWING_LINE:
                    editPath.setAttributeNS(null,"d", printPathCommands() + " " + String.format("L%1$f %2$f", e.getPoint().getX(), e.getPoint().getY()));
                    break;
                case PEN_DRAWING_CURVE:
                    editPath.setAttributeNS(null,"d", printPathCommands() + " " + String.format("L%1$f %2$f", e.getPoint().getX(), e.getPoint().getY()));
                    break;
                default:
                    break;
            }
        }
    }

    public String printPathCommands()
    {
        String ret = "";
        for (String s : pathCommands)
        {
            if(ret != "")
                ret += " ";
            ret += s;
        }
        return ret;
    }
}

