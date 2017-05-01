package com.csci4448.paint4448;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.csci4448.paint4448.dialogs.OpenDialog;
import com.csci4448.paint4448.dialogs.RotateDialog;
import com.csci4448.paint4448.dialogs.SaveDialog;
import com.csci4448.paint4448.shapes.Style;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.csci4448.paint4448.tools.*;

// Paint is a singleton
public class Paint implements ActionListener, ChangeListener {

    private static Paint paint = null;
    private JFrame window;
    private Canvas canvas;
    private SessionFactory factory;
    private Style currentStyle;


    private Paint() {
        setupGUI();
        //setupDB();
    }

    public static Paint getInstance() {
        if (paint == null) paint = new Paint();

        return paint;
    }

    private void setupDB() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
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
        itemRotate.addActionListener(ae -> {
            new RotateDialog(window, canvas);
        });
        subMenuCanvas.add(itemRotate);

        frame.setJMenuBar(menuBar);
    }

    private void setupToolOptionsBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel brushSizeLabel = new JLabel("Stroke-Width:");
        panel.add(brushSizeLabel);

        SpinnerModel model = new SpinnerNumberModel(
                3, // Default
                1, // Min
                300, // Max
                1 // Step
        );
        JSpinner spinner = new JSpinner(model);
        spinner.setAlignmentX(JSpinner.LEFT_ALIGNMENT);
        spinner.setName("StrokeWidth");
        spinner.addChangeListener(this);
        panel.add(spinner);

        JLabel pxLabel = new JLabel("px");
        panel.add(pxLabel);

        JLabel strokeColorL = new JLabel("Stroke-Color:");
        JTextField strokeColorTF = new JTextField("black");
        strokeColorTF.setName("StrokeColor");
        strokeColorTF.addActionListener(this);
        panel.add(strokeColorL);
        panel.add(strokeColorTF);

        JLabel fillColorL = new JLabel("Fill:");
        JTextField fillColorTF = new JTextField("none");
        fillColorTF.setName("Fill");
        fillColorTF.addActionListener(this);
        panel.add(fillColorL);
        panel.add(fillColorTF);

        JLabel toolL = new JLabel("Tool:");
        String[] tools = {"Pen", "Rectangle", "Ellipse"};
        JComboBox toolCB = new JComboBox(tools);
        toolCB.setName("Tool");
        toolCB.addActionListener(this);
        panel.add(toolCB);

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
        canvas = new Canvas(panel, 1000, 1000);

        Tool blah = new PenTool();
        currentStyle = new Style();
        currentStyle.setFill("none");
        currentStyle.setStroke("black");
        currentStyle.setStrokeWidth("3");
        blah.setStyle(currentStyle);
        canvas.setTool(blah);
        window.add(panel);
    }

    public SessionFactory getFactory() {
        if (factory == null) setupDB();
        return factory;
    }

    public static void main(String[] args) {
        getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JComboBox)
        {
            JComboBox cb = (JComboBox)e.getSource();
            if(cb.getName().equals("Tool"))
            {
                Tool newtool = null;
                switch((String)cb.getSelectedItem())
                {
                    case "Pen":
                        newtool = new PenTool();
                        break;
                    case "Rectangle":
                        newtool = new RectangleTool();
                        break;
                    case "Ellipse":
                        newtool = new EllipseTool();
                        break;
                }
                newtool.setStyle(currentStyle);
                canvas.setTool(newtool);
            }
        }
        else if(e.getSource() instanceof JTextField)
        {
            JTextField tf = (JTextField)e.getSource();
            switch (tf.getName())
            {
                case "StrokeColor":
                    currentStyle.setStroke(tf.getText());
                    break;
                case "Fill":
                    currentStyle.setFill(tf.getText());
                    break;
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner)e.getSource();
        if(spinner!=null)
        {
            if(spinner.getName().equals("StrokeWidth"))
            {
                currentStyle.setStrokeWidth(spinner.getValue().toString());
            }
        }
    }
}
