package LearningGUI.Vid1;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private JFrame window;

    public MainWindow(){
        window = new JFrame();
        window.setTitle("Hello world");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 500);
        window.setLocationRelativeTo(null); //centres in the middle "Null"

    }
    public void show(){
        window.setVisible(true);

    }
}
