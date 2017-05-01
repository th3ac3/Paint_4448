package com.csci4448.paint4448.shapes;

import javax.persistence.Embeddable;
import java.awt.*;

@Embeddable
public class Style {
    private String fill;
    private String fillRule;
    private String stroke;
    private String strokeWidth;
    private String strokeLineCap;
    private String strokeLineJoin;
    private String strokeOpacity;

    public Style(){}

    public Style(Style style)
    {
        fill = style.fill;
        fillRule = style.fillRule;
        stroke = style.stroke;
        strokeWidth = style.strokeWidth;
        strokeLineCap = style.strokeLineCap;
        strokeLineJoin = style.strokeLineJoin;
        strokeOpacity = style.strokeOpacity;
    }

    public Style(String parseString) {
        String[] styles = parseString.split(";");

        for (String string : styles) {
            String style = string.substring(0, string.indexOf(":"));
            String value = string.substring(string.indexOf(":") + 1);
            style = style.trim();
            value = value.trim();
            switch (style) {
                case "fill":
                    setFill(value);
                    break;
                case "fill-rule":
                    setFillRule(value);
                    break;
                case "stroke":
                    setStroke(value);
                    break;
                case "stroke-width":
                    setStrokeWidth(value);
                    break;
                case "stroke-line-cap":
                    setStrokeLineCap(value);
                    break;
                case "stroke-line-join":
                    setStrokeLineJoin(value);
                    break;
                case "stroke-opacity":
                    setStrokeOpacity(value);
                    break;
            }
        }
    }

    public Color getColor(String color) {
        color = color.replace("#", "");
        int r = Integer.parseInt(color.substring(0, 2), 16);
        int g = Integer.parseInt(color.substring(2, 4), 16);
        int b = Integer.parseInt(color.substring(4, 6), 16);

        return new Color(r, g, b);
    }

    public String toString() {
        String style = "";

        if (fill != null)
            style += "fill: " + getFill() + "; ";

        if (fillRule != null)
            style += "fill-rule: " + getFillRule() + "; ";

        if (stroke != null)
            style += "stroke: " + getStroke() + "; ";

        if (strokeWidth != null)
            style += "stroke-width: " + getStrokeWidth() + "; ";

        if (strokeLineCap != null)
            style += "stroke-line-cap: " + getStrokeLineCap() + "; ";

        if (strokeLineJoin != null)
            style += "stroke-line-join: " + getStrokeLineJoin() + "; ";

        if (strokeOpacity != null)
            style += "stroke-opacity: " + getStrokeOpacity() + "; ";

        // Remove blank space at end
        if (!style.equals(""))
            style = style.substring(0, style.length() - 1);

        return style;
    }

    public String getFill() {
        return fill;
    }
    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getFillRule() {
        return fillRule;
    }
    public void setFillRule(String fillRule) {
        this.fillRule = fillRule;
    }

    public String getStroke() {
        return stroke;
    }
    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }
    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeLineCap() {
        return strokeLineCap;
    }
    public void setStrokeLineCap(String strokeLineCap) {
        this.strokeLineCap = strokeLineCap;
    }

    public String getStrokeLineJoin() {
        return strokeLineJoin;
    }
    public void setStrokeLineJoin(String strokeLineJoin) {
        this.strokeLineJoin = strokeLineJoin;
    }

    public String getStrokeOpacity() {
        return strokeOpacity;
    }
    public void setStrokeOpacity(String strokeOpacity) {
        this.strokeOpacity = strokeOpacity;
    }
}
