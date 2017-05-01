package com.csci4448.paint4448.shapes;

import javax.persistence.Embeddable;

@Embeddable
public class Transform {
    private String matrix;
    private String translate;
    private String scale;
    private String rotate;
    private String skewX;
    private String skewY;

    public Transform(){}

    public Transform(String parseString) {
        String[] transforms = parseString.split(" ");

        for (String string : transforms) {
            String transform = string.substring(0, string.indexOf('('));
            String value = string.substring(string.indexOf('(') + 1, string.indexOf(')'));

            switch (transform) {
                case "matrix":
                    setMatrix(value);
                    break;
                case "translate":
                    setTranslate(value);
                    break;
                case "scale":
                    setScale(value);
                    break;
                case "rotate":
                    setRotate(value);
                    break;
                case "skewX":
                    setSkewX(value);
                    break;
                case "skewY":
                    setSkewY(value);
                    break;
            }
        }
    }

    public String toString() {
        String transform = "";

        if (matrix != null)
            transform += "matrix(" + getMatrix() + ")";

        if (translate != null)
            transform += "translate(" + getTranslate() + ")";

        if (scale != null)
            transform += "scale(" + getScale() + ")";

        if (rotate != null)
            transform += "rotate(" + getRotate() + ") ";

        if (skewX != null)
            transform += "skewX(" + getSkewX() + ")";

        if (skewY != null)
            transform += "skewY(" + getSkewY() + ")";

        // Remove blank space at end
        if (!transform.equals(""))
            transform = transform.substring(0, transform.length() - 1);

        return transform;
    }

    public String getMatrix() {
        return matrix;
    }
    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getTranslate() {
        return translate;
    }
    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getScale() {
        return scale;
    }
    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getRotate() {
        return rotate;
    }
    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getSkewX() {
        return skewX;
    }
    public void setSkewX(String skewX) {
        this.skewX = skewX;
    }

    public String getSkewY() {
        return skewY;
    }
    public void setSkewY(String skewY) {
        this.skewY = skewY;
    }
}
