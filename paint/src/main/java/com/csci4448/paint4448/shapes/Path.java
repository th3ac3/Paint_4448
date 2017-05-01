package com.csci4448.paint4448.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.util.ArrayList;

public class Path extends Shape {
    private Point startPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public ArrayList<String> getPathCommands() {
        return pathCommands;
    }

    public void setPathCommands(ArrayList<String> pathCommands) {
        this.pathCommands = pathCommands;
    }

    public Path()
    {

    }

    public Path(Element elem) {
        super(elem);
        setStartPoint(new Point());
        String d = elem.getAttribute("d");
        String[] commands = d.split("(?=[a-zA-Z])");
        pathCommands = new ArrayList<>();
        if (!commands.equals(""))
        {
            for (String string : commands) {
                pathCommands.add(string);
            }
        }
    }

    private Point endPoint;
    private ArrayList<String> pathCommands;

    @Override
    public String toXML() {
        Document doc = getDocument();

        Element path = doc.createElement("path");

        path.setAttribute("d", printPathCommands());

        setGlobalAttributes(path);

        doc.appendChild(path);

        return docToString(doc);
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
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
