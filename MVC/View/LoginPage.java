package MVC.View;

import MVC.Controller.ClearLoginPageListener;
import MVC.Controller.MainController;
import MVC.Model.FlashCardsModel;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPage extends JPanel implements ClearLoginPageListener {

    private MainController MainController;

    private JTextField usernameField;

    /*
    public static void main(String[] args) {
        FlashCardsModel model = new FlashCardsModel();
        MainController appController = new MainController(model, null);
        new LoginPage(appController);
    }

     */

    public LoginPage(MainController MainController) {
        this.MainController = MainController;
        initComponents();
    }

    public void initComponents() {
        setBackground(new Color(100, 227, 200));
        setLayout(new GridBagLayout());

        //Font
        Font graphiteWideLight = new Font("Graphite", Font.PLAIN, 12);

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Welcome to your student support system!");
        titleLabel.setFont(new Font("Graphite", Font.PLAIN, 30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(50, 0, 0, 0);
        add(titleLabel, constraints);

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(50, 50, 0, 0);
        add(usernameLabel, constraints);

        //usernameField
        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(50, 50, 0, 0);
        add(usernameField, constraints);

        //Password label
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(50, 50, 0, 0);
        add(passwordLabel, constraints);

        //Password field that hides input
        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(50, 50, 0, 0);
        add(passwordField, constraints);

        //login Button
        JButton loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(50, 50, 0, 0);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage.this.loginHandler(passwordField);
            }
        });
        add(loginButton, constraints);


        //register button
        JButton registerButton = new JButton("Register");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(50, 50, 0, 0);
        registerButton.addActionListener(e -> LoginPage.this.registerHandler(usernameField, passwordField));
        add(registerButton, constraints);
    }

    private void loginHandler(JTextField passwordField) {
        String passwordValue = passwordField.getText();
        String usernameValue = usernameField.getText();
        try {
            MainController.login(usernameValue, passwordValue);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "User not found", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }

    }

    private void registerHandler(JTextField usernameField, JTextField passwordField) {
        String passwordValue = passwordField.getText();
        String usernameValue = usernameField.getText();
        try {
            MainController.register(usernameValue, passwordValue);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "User already registered", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }

    public void clearLoginPage() {
        usernameField.setText("");
    }

}


