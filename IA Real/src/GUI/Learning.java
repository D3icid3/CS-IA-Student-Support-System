package GUI;
import javax.security.auth.login.CredentialException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Learning extends JFrame{
    public static void main(String[] args){
        //Frame
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Login Page");


        //buttons
        JButton button_login = new JButton("Login");


        JTextField textField = new JTextField("Write here?");

        //Panel
        JPanel panel = new JPanel();
        panel.add(button_login, BorderLayout.CENTER);
        panel.add(textField);
        frame.add(panel, BorderLayout.WEST);
        frame.setLayout(new GridLayout(2,2));


        frame.setVisible(true);

    }
    public void CreateCloseButton(){
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

}
