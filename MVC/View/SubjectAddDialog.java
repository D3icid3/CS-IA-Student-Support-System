package MVC.View;

import MVC.Controller.MainController;
import MVC.Model.Subject;
import MVC.Controller.MainController;
import MVC.Model.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class SubjectAddDialog extends JDialog {

    private MainController MainController;
    private JTextField nameField;

    SubjectAddDialog(JFrame owner, MainController MainController) {
        super(owner, true); //blocks input until window is closed
        this.MainController = MainController;

        initComponents();
        pack(); //nice gui makes it so that the frame is in proportion to objects inside
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Add subject");
        setLayout(new FlowLayout());

        nameField = new JTextField(20);
        add(nameField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> SubjectAddDialog.this.okHandler()); //lambda just simplifies the program
        add(okButton);
    }

    private void okHandler() {
        Subject subject = new Subject();
        subject.setName(nameField.getText());
        MainController.addSubject(subject);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //removes the subject adding window in a systematic manner
    }

}
