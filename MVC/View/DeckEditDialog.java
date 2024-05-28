package MVC.View;

import MVC.Controller.MainController;
import MVC.Model.Card;
import MVC.Model.Subject;
import MVC.Controller.MainController;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class DeckEditDialog extends JDialog {

    private MainController MainController;

    private JList<Card> cardList;

    DeckEditDialog(JFrame owner, MainController MainController, Subject subject) {
        super(owner, true);
        this.MainController = MainController;

        initComponents(subject);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponents(Subject subject) {
        setTitle("Edit deck");
        setLayout(new BorderLayout());

        cardList = new JList();
        cardList.setModel(MainController.getCardListModel(subject));

        JScrollPane subjectScrollPane = new JScrollPane();
        subjectScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        subjectScrollPane.setViewportView(cardList);
        subjectScrollPane.setSize(400, 300);
        add(subjectScrollPane, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new VerticalLayout());

        JButton addButton = new JButton();
        addButton.setText("Add card");
        addButton.addActionListener(e -> DeckEditDialog.this.addHandler());
        eastPanel.add(addButton);

        JButton editButton = new JButton("Edit card");
        editButton.addActionListener(e -> DeckEditDialog.this.editHandler());
        eastPanel.add(editButton);

        JButton removeButton = new JButton("Remove card");
        removeButton.addActionListener(e -> DeckEditDialog.this.removeHandler());
        eastPanel.add(removeButton);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        add(eastPanel, BorderLayout.EAST);
    }

    /**
     * Exclaim: all handlers below still needed to be done
     * C- mostly cmndC cmndV from Subject add/SubjectListPanel
     */
    private void addHandler() {
    }

    private void editHandler() {
    }

    private void removeHandler() {
    }

}
