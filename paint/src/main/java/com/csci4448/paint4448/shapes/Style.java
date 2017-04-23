package com.csci4448.paint4448.shapes;

import java.awt.*;

public class Style {
    public String fill;
    public String fillRule;
    public String stroke;
    public String strokeWidth;
    public String strokeLineCap;
    public String strokeLineJoin;
    public String strokeOpacity;

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
            style += "fill: " + fill + ";";

        if (fillRule != null)
            style += "fill-rule: " + fillRule + ";";

        if (stroke != null)
            style += "stroke: " + stroke + ";";

        if (strokeWidth != null)
            style += "stroke-width: " + strokeWidth + ";";

        if (strokeLineCap != null)
            style += "stroke-line-cap: " + strokeLineCap + ";";

        if (strokeLineJoin != null)
            style += "stroke-line-join: " + strokeLineJoin + ";";

        if (strokeOpacity != null)
            style += "stroke-opacity: " + strokeOpacity + ";";

        return style;
    }
}
