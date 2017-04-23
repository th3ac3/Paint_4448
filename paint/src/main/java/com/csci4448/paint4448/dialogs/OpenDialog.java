package com.csci4448.paint4448.dialogs;

import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class OpenDialog {
    public OpenDialog(JFrame window, JSVGCanvas canvas) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(window);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            canvas.setURI(String.valueOf(file.toURI()));
        } // else user cancelled
    }
}
