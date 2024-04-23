package GUI;
import javax.swing.*;
import java.awt.*;
public class Login {
    public static void main(String[] args){
        JFrame Login_Frame = new JFrame();
        JButton Login_Button = new JButton("Login");
        JButton CreateAccount_Button = new JButton("Create new Account");
        Login_Frame.setSize(500,500);
        Login_Button.setSize(50,50);
        Login_Frame.add(Login_Button);
        Login_Frame.add(CreateAccount_Button);
        CreateAccount_Button.setSize(50,50);
        //Login_Frame.setLayout();

        Login_Frame.setVisible(true);
    }


}
