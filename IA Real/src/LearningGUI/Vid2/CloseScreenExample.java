package LearningGUI.Vid2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CloseScreenExample extends JFrame {
    public CloseScreenExample() {
        setTitle("Close Screen Example");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton closeButton = new JButton("Close");
        add(closeButton);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the screen
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CloseScreenExample();
    }
}