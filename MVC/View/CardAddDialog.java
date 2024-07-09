package MVC.View;

import MVC.Controller.MainController;
import MVC.Model.Card;
import MVC.Model.Subject;
import MVC.Controller.MainController;
import MVC.Model.Subject;
import MVC.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class CardAddDialog extends JDialog {

    private MainController MainController;
    private JTextField nameFieldFront;
    private JTextField nameFieldBack;

    CardAddDialog(JFrame owner, MainController MainController) {
        super(owner, true); //blocks input until window is closed
        this.MainController = MainController;
        addCardComponents();
        pack(); //nice gui makes it so that the frame is in proportion to objects inside
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void addCardComponents() {
        setTitle("Add Card");
        setLayout(new FlowLayout());

        nameFieldFront = new JTextField(20);
        add(nameFieldFront);

        nameFieldBack = new JTextField(20);
        add(nameFieldBack);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {CardAddDialog.this.okHandler();}); //lambda just simplifies the program
        add(okButton);
    }

    private void okHandler() {
        Card card = new Card();
        Subject subject = new Subject();
        card.setFront(nameFieldFront.getText());
        card.setBack(nameFieldBack.getText());
        MainController.addCard(card,subject);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //removes the subject adding window in a systematic manner
    }

}
