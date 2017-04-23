package com.csci4448.paint4448.dialogs;

import com.csci4448.paint4448.SVGFile;

import javax.swing.*;
import java.io.File;

public class SaveDialog {
    public SaveDialog(JFrame window) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(window);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            SVGFile svgFile = new SVGFile(file);
            svgFile.save();
            // TODO Save out to the file
        } // Otherwise save cancelled
    }
}
