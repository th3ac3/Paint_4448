package com.csci4448.paint4448.dialogs;

import com.csci4448.paint4448.Canvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotateDialog implements ActionListener {
    private boolean cw = true;
    private int angle = 90;
    private static final String CW_ACTION = "CW";
    private static final String CCW_ACTION = "CCW";
    private static final String D90_ACTION = "90";
    private static final String D180_ACTION = "180";
    private static final String D270_ACTION = "270";

    JRadioButton buttonCW, buttonCCW, button90, button180, button270;
    ButtonGroup directionGroup, angleGroup;

    public RotateDialog(JFrame window, Canvas canvas) {
        JComponent[] inputs = setupDialog();

        int result = JOptionPane.showConfirmDialog(window, inputs, "Rotate", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (!cw) angle = 360 - angle;

            canvas.rotate(angle);
        } // else user cancelled
    }

    private JComponent[] setupDialog() {
        buttonCW = new JRadioButton("Clockwise");
        buttonCW.setActionCommand(CW_ACTION);
        buttonCCW = new JRadioButton("Counter clockwise");
        buttonCCW.setActionCommand(CCW_ACTION);

        directionGroup = new ButtonGroup();
        directionGroup.add(buttonCW);
        directionGroup.add(buttonCCW);

        button90 = new JRadioButton("90°");
        button90.setActionCommand(D90_ACTION);
        button180 = new JRadioButton("180°");
        button180.setActionCommand(D180_ACTION);
        button270 = new JRadioButton("270°");
        button270.setActionCommand(D270_ACTION);

        angleGroup = new ButtonGroup();
        angleGroup.add(button90);
        angleGroup.add(button180);
        angleGroup.add(button270);

        buttonCW.addActionListener(this);
        buttonCCW.addActionListener(this);
        button90.addActionListener(this);
        button180.addActionListener(this);
        button270.addActionListener(this);

        return new JComponent[] {
            buttonCW, buttonCCW,
            button90, button180, button270
        };
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        switch (command) {
            case CW_ACTION:
                cw = true;
                break;
            case CCW_ACTION:
                cw = false;
                break;
            case D90_ACTION:
                angle = 90;
                break;
            case D180_ACTION:
                angle = 180;
                break;
            case D270_ACTION:
                angle = 270;
                break;
        }
    }
}
