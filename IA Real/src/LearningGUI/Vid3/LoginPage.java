package LearningGUI.Vid3;

import javax.swing.*;
import java.awt.*;

    public class LoginPage {
        public static void main(String[] args) {
            //Frame
            JFrame frame = new JFrame("Login Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);

            //Panel
            JPanel panel = new JPanel();
            panel.setBackground(new Color(100, 227, 200));
            panel.setLayout(new GridBagLayout());

            //Font
            Font graphiteWideLight = new Font("Graphite", Font.PLAIN, 12);

            GridBagConstraints constraints = new GridBagConstraints();

            JLabel titleLabel = new JLabel("Welcome your student support system!");
            titleLabel.setFont(new Font("Graphite", Font.PLAIN, 30));
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(50, 0, 0, 0);
            panel.add(titleLabel, constraints);

            JLabel usernameLabel = new JLabel("Username:");
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.anchor = GridBagConstraints.LINE_START;
            constraints.insets = new Insets(50, 50, 0, 0);
            panel.add(usernameLabel, constraints);

            //usernameField
            JTextField usernameField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.anchor = GridBagConstraints.LINE_START;
            constraints.insets = new Insets(50, 50, 0, 0);
            panel.add(usernameField, constraints);

            //Password label
            JLabel passwordLabel = new JLabel("Password:");
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 1;
            constraints.anchor = GridBagConstraints.LINE_START;
            constraints.insets = new Insets(50, 50, 0, 0);
            panel.add(passwordLabel, constraints);

            //Password
            JPasswordField passwordField = new JPasswordField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            constraints.gridwidth = 1;
            constraints.anchor = GridBagConstraints.LINE_START;
            constraints.insets = new Insets(50, 50, 0, 0);
            panel.add(passwordField, constraints);

            //login Button
            JButton loginButton = new JButton("Login");
            constraints.gridx = 1;
            constraints.gridy = 3;
            constraints.gridwidth = 1;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(50, 50, 0, 0);
            panel.add(loginButton, constraints);

            frame.add(panel);
            frame.setVisible(true);
        }
    }

