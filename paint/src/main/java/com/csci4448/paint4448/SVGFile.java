package com.csci4448.paint4448;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SVGFile {
    File file;
    String xmlVersion;
    String xmlEncoding;

    public SVGFile(String filePath) {
        file = new File(filePath);
    }

    void save(String data){
        try (PrintWriter out = new PrintWriter(file)) {
            out.print(data);
        } catch (FileNotFoundException e) {
            // TODO handle file not found
            e.printStackTrace();
        }

    }

    void load(){

    }
}