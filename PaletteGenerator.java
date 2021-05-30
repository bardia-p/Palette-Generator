// Bardia Parmoun
// 101143006

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Math;
import java.util.ArrayList;

/**
 * This class generates a palette for the user upon request
 * @author Bardia Parmoun - 101143006
 */
public class PaletteGenerator {
    /**
     * Keeps track of the main frame for the palette
     */
    private final JFrame frame;

    /**
     * An arraylist of panels keeping track of the colour panels
     */
    private ArrayList<JPanel> colourPanels;
    /**
     * An arraylist of buttons for the colour panels
     */
    private ArrayList<JButton> colourButtons;

    /**
     * The constructor for the palette generator which initializes the panels and the buttons
     */
    public PaletteGenerator() {
        this.frame = new JFrame();

        colourPanels = new ArrayList<>();
        colourButtons= new ArrayList<>();

        //one panel for each colour
        for (int i = 0; i < 8; i++){
            colourPanels.add(new JPanel(new BorderLayout()));
            colourButtons.add(getLockedButton());
            colourPanels.get(i).add(colourButtons.get(i), BorderLayout.PAGE_END);
        }
    }

    /**
     * Get colours of a certain brightness. Wow!
     * @return Color : A Color object with the generated colour.
     */
    private  Color getColour() {
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        double luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);

        while (luma < 75) {
            r = (int)(Math.random()*256);
            g = (int)(Math.random()*256);
            b = (int)(Math.random()*256);
            luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);
        }
        return new Color(r, g, b);
    }

    /**
     * Generates the palette colour.
     * @return JButton : a JButton object.
     */
    private JButton getColourButton() {
        JButton button = new JButton("Generate Palette");
        button.setSize(50,30);

        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                generatePalette();
            }
        });
        return button;
    }

    /**
     * Sets the button to either locked or not
     * @return JButton : a JButton object.
     */
    private JButton getLockedButton() {
        JButton button = new JButton("");

        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (button.getText().equals("X")) {
                    // if the button is locked it sets it to unlock
                    button.setText("");
                } else {
                    // if the button is unlocked it locks it
                    button.setText("X");
                }
            }
        });

        return button;
    }

    /**
     * Generates background colours for the panels upon request
     */
    private void generatePalette(){
        for (int i=0; i<8; i++){
            // checks to see if the button is not locked
            if (colourButtons.get(i).getText().equals("")){
                // if it is not locked sets new colour for that panel
                colourPanels.get(i).setBackground(getColour());
            }
        }
    }

    /**
     * Displays the GUI for the colour generator
     */
    public void displayGUI(){
        // some things you should specify when creating your JFrame
        frame.setTitle("My Frame is ?x?.");

        JPanel mainPanel =  new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        JPanel bodyPanel = new JPanel(new GridLayout(1,8));
        JPanel footerPanel = new JPanel();


        // create your JLabels here
        JLabel headerLabel = new JLabel("Your palette");

        // adding JLabel to the header panel
        headerPanel.add(headerLabel,BorderLayout.CENTER);


        // set the preferred sizes and colours
        headerPanel.setPreferredSize(new Dimension(1000, 100));
        bodyPanel.setPreferredSize(new Dimension(1000, 400));
        footerPanel.setPreferredSize(new Dimension(1000, 100));


        // adding the colour panels to the body panel
        for (JPanel p: colourPanels){
           bodyPanel.add(p);
        }

        // gets the original colour of the panels
        generatePalette();

        // adding the generate panel colour to the footer
        footerPanel.add(getColourButton(),BorderLayout.PAGE_END);

        // adding all the panels to the main panel
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);

        // pack
        frame.add(mainPanel);
        frame.pack();


        // add the window listener
        // we no longer want the frame to close immediately when we press "x"
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        // the frame is not visible until we set it to be so
        frame.setVisible(true);
    }
}
