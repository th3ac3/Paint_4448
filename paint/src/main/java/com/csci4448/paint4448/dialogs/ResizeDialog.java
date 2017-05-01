package com.csci4448.paint4448.dialogs;

import com.csci4448.paint4448.Canvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Bi on 5/1/2017.
 */
public class ResizeDialog {
    private boolean cw = true;
    private int newHeight = 0;
    private int newWidth = 0;


    public ResizeDialog(JFrame window, Canvas canvas) {
        JComponent[] inputs = setupDialog();

        int result = JOptionPane.showConfirmDialog(window, inputs, "Resize", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            newHeight= Integer.parseInt(((JTextField)inputs[1]).getText());
            newWidth= Integer.parseInt(((JTextField)inputs[3]).getText());
            if(newHeight > window.getSize().getWidth())
            {
                newWidth = (int)window.getSize().getWidth()-100;
            }
            if(newHeight > window.getSize().getHeight())
            {
                newHeight = (int)window.getSize().getHeight()-150;
            }
            canvas.resizeCanvas(newWidth, newHeight);
        } // else user cancelled
    }

    private JComponent[] setupDialog() {
        JTextField heightTF = new JTextField("500");
        JLabel heightL = new JLabel("Height: ");
        JTextField widthTF = new JTextField("500");
        JLabel widthL = new JLabel("Width: ");
        return new JComponent[]{heightL, heightTF, widthL, widthTF};
    }
}
