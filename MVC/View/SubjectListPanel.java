package MVC.View;

import MVC.Controller.MainController;
import MVC.Model.Subject;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;



public class SubjectListPanel extends JPanel {

    private final JFrame owner;
    private final MainController MainController;

    private JList<Subject> subjectList;

    public SubjectListPanel(JFrame owner, MainController MainController) {
        this.owner = owner;
        this.MainController = MainController;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        subjectList = new JList();
        subjectList.setModel(MainController.getSubjectListModel());

        JScrollPane subjectScrollPane = new JScrollPane();
        subjectScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        subjectScrollPane.setViewportView(subjectList);
        add(subjectScrollPane, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new VerticalLayout());

        JButton addButton = new JButton();
        addButton.setText("Add subject");
        addButton.addActionListener(e -> SubjectListPanel.this.addHandler());
        eastPanel.add(addButton);


        JButton removeButton = new JButton("Remove subject");
        removeButton.addActionListener(e -> SubjectListPanel.this.removeHandler());
        eastPanel.add(removeButton);

        JButton deckEditButton = new JButton("Edit deck");
        deckEditButton.addActionListener(e -> SubjectListPanel.this.deckEditHandler());
        eastPanel.add(deckEditButton);

        JButton startGame = new JButton("Start game!");
        eastPanel.add(startGame);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> MainController.logout());
        eastPanel.add(logout);

        add(eastPanel, BorderLayout.EAST);
    }

    void addHandler() {
        new SubjectAddDialog(owner, MainController);
    }

    void removeHandler() {
        MainController.removeSubject(subjectList.getSelectedValue());
    }

    private void deckEditHandler() {
        new DeckEditDialog(owner, MainController, subjectList.getSelectedValue());
    }

}
