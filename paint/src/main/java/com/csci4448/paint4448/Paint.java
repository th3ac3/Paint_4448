package com.csci4448.paint4448;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

// Paint is a singleton
public class Paint {

    private static Paint paint = null;

    private Paint() {
        setupGUI();
    }

    public static Paint getInstance() {
        if (paint == null) paint = new Paint();

        return paint;
    }

    private void setupGUI() {
        JFrame frame = new JFrame("Paint Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Hello Paint");
        contentPane.add(label);

        setupMenuBar(frame);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    private void setupMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menuFile);

        JMenuItem itemSave = new JMenuItem("Save Image");
        itemSave.setMnemonic(KeyEvent.VK_S);
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuFile.add(itemSave);

        JMenuItem itemLoad = new JMenuItem("Open Image");
        itemLoad.setMnemonic(KeyEvent.VK_O);
        itemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuFile.add(itemLoad);

        // Edit menu
        JMenu menuEdit = new JMenu("Edit");
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuBar.add(menuEdit);

        // Canvas submenu
        JMenu subMenuCanvas = new JMenu("Canvas");
        subMenuCanvas.setMnemonic(KeyEvent.VK_C);
        menuEdit.add(subMenuCanvas);

        JMenuItem itemResize = new JMenuItem("Resize");
        itemResize.setMnemonic(KeyEvent.VK_I);
        itemResize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        subMenuCanvas.add(itemResize);

        JMenuItem itemRotate = new JMenuItem("Rotate");
        itemRotate.setMnemonic(KeyEvent.VK_R);
        itemRotate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        subMenuCanvas.add(itemRotate);

        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        getInstance();
    }
}
