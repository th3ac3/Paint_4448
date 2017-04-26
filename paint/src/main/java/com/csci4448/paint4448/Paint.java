package com.csci4448.paint4448;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.csci4448.paint4448.dialogs.OpenDialog;
import com.csci4448.paint4448.dialogs.SaveDialog;

// Paint is a singleton
public class Paint {

    private static Paint paint = null;
    private JFrame window;
    private Canvas canvas;

    private Paint() {
        setupGUI();
    }

    public static Paint getInstance() {
        if (paint == null) paint = new Paint();

        return paint;
    }

    private void setupGUI() {
        window = new JFrame("Paint");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.YELLOW);
        window.setContentPane(contentPane);

        setupMenuBar(window);
        setupToolOptionsBar();
        setupSideBar();
        setupCanvas();

        window.pack();
        window.setVisible(true);
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
        itemSave.addActionListener(ae -> {
            new SaveDialog(window, canvas);
        });
        menuFile.add(itemSave);

        JMenuItem itemLoad = new JMenuItem("Open Image");
        itemLoad.setMnemonic(KeyEvent.VK_O);
        itemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        itemLoad.addActionListener(ae -> {
            new OpenDialog(window, canvas);
        });
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

    private void setupToolOptionsBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel brushSizeLabel = new JLabel("Brush Size:");
        panel.add(brushSizeLabel);

        SpinnerModel model = new SpinnerNumberModel(
                12, // Default
                1, // Min
                300, // Max
                1 // Step
        );
        JSpinner spinner = new JSpinner(model);
        spinner.setAlignmentX(JSpinner.LEFT_ALIGNMENT);
        panel.add(spinner);

        JLabel pxLabel = new JLabel("px");
        panel.add(pxLabel);

        window.add(panel, BorderLayout.NORTH);
    }

    private void setupSideBar() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Side Bar");
        panel.add(label);
        panel.setBackground(Color.GREEN);

        window.add(panel, BorderLayout.WEST);
    }

    private void setupCanvas() {
        JPanel panel = new JPanel(new GridBagLayout());
        canvas = new Canvas(panel, 500, 500);

        window.add(panel);
    }

    public static void main(String[] args) {
        getInstance();
    }
}
