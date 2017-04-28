package com.csci4448.paint4448.shapes;

public class Transform {
    public String matrix;
    public String translate;
    public String scale;
    public String rotate;
    public String skewX;
    public String skewY;

    public String toString() {
        String transform = "";

        if (matrix != null)
            transform += "matrix(" + matrix + ")";

        if (translate != null)
            transform += "translate(" + translate + ")";

        if (scale != null)
            transform += "scale(" + scale + ")";

        if (rotate != null)
            transform += "rotate(" + rotate + ") ";

        if (skewX != null)
            transform += "skewX(" + skewX + ")";

        if (skewY != null)
            transform += "skewY(" + skewY + ")";

        return transform;
    }
}
