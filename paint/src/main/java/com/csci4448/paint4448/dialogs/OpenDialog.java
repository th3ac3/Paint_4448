package com.csci4448.paint4448.dialogs;

import com.csci4448.paint4448.Canvas;
import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class OpenDialog {
    public OpenDialog(JFrame window, Canvas canvas) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(window);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            canvas.open(file);
        } // else user cancelled
    }
}
