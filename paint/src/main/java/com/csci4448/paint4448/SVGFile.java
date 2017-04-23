package com.csci4448.paint4448;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SVGFile {
    File file;
    String xmlVersion;
    String xmlEncoding;
    String data;

    public SVGFile(File file) {
        this.file = file;
    }

    public void save(){
        try (PrintWriter out = new PrintWriter(file)) {
            out.print(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load(){
    }
}
