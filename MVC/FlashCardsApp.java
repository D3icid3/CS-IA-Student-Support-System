package MVC;

import MVC.Controller.ChangeActivePanelListener;
import MVC.Controller.ClearLoginPageListener;
import MVC.Controller.MainController;
import MVC.Model.FlashCardsModel;
import MVC.View.DeckEditDialog;
import MVC.View.LoginPage;
import MVC.View.SubjectListPanel;


import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FlashCardsApp implements ChangeActivePanelListener {

    FlashCardsModel model;
    MainController MainController;

    JFrame frame;
    JPanel mainPanel;
    CardLayout cardLayout;

    LoginPage loginPage;
    SubjectListPanel subjectListPanel;

    FlashCardsApp() {
        model = new FlashCardsModel();
        MainController = new MainController(model, this);

        frame = new JFrame("App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // center on screen
        mainPanel = new JPanel();
        frame.add(mainPanel);

        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        loginPage = new LoginPage(MainController);
        MainController.setClearLoginListener((ClearLoginPageListener) loginPage);

        subjectListPanel = new SubjectListPanel(frame, MainController);
        mainPanel.add(loginPage, ChangeActivePanelListener.PanelName.LOGIN.name());
        mainPanel.add(subjectListPanel, PanelName.SUBJECT_LIST.name());
    }

    void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });
    }
    public void onChangeActivePanel(PanelName panel) {
        cardLayout.show(mainPanel, panel.name());
    }



    public static void main(String[] args) {
        new FlashCardsApp().start();
    }



}
