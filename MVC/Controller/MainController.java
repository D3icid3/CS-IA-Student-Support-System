package MVC.Controller;

import MVC.DB.CardDb;
import MVC.DB.LoginDb;
import MVC.DB.SubjectDb;
import MVC.Model.Card;
import MVC.Model.FlashCardsModel;
import MVC.Model.Subject;
import MVC.Model.User;
import MVC.Utility.DbException;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class MainController {

    private FlashCardsModel model;

    private ChangeActivePanelListener changeActivePanelListener;
    private ClearLoginPageListener clearLoginPageListener;

    public MainController(FlashCardsModel model, ChangeActivePanelListener changeActivePanelListener) {
        this.model = model;
        // listener design pattern
        this.changeActivePanelListener = changeActivePanelListener;
    }

    public void setClearLoginListener(ClearLoginPageListener clearLoginPageListener) {
        this.clearLoginPageListener = clearLoginPageListener;
    }

    /**
     *
     * @param user
     * loads all the data required for the subjects and cards
     * ToDo: make it user specific
     */
    public void loadData(User user) {
        try {
            List<Subject> subjects = SubjectDb.getSubjects();
            for (Subject subject : subjects) {
                model.addSubject(subject);
                List<Card> cards = CardDb.getCards(subject);
                for (Card card : cards) {
                    model.addCard(subject, card);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }




    public void addSubject(Subject subject, User user) {
        try {
            subject = SubjectDb.addSubject(subject, user);
            model.addSubject(subject);
        } catch (DbException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void addCard(Card card, Subject subject) {
        try {
            card = CardDb.addCard(subject, card);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ListModel<Subject> getSubjectListModel() {
        return model.getSubjectListModel();
    }

    public void removeSubject(Subject subject) {
        try {
            SubjectDb.deleteSubject(subject);
            model.removeSubject(subject);
        } catch (DbException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeCard(Card card) {
        try {
            CardDb.deleteCard(card);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateCard(Card card, String newFront, String newBack){
        try{
            CardDb.updateCard(card,newFront, newBack);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User login(String loginName, String password) throws Exception {
        User user = LoginDb.login(loginName,password);
        loadData(user);
        if (changeActivePanelListener != null) {
            changeActivePanelListener.onChangeActivePanel(ChangeActivePanelListener.PanelName.SUBJECT_LIST);
        }
        return user;
    }

    public User register(String loginName, String password) throws Exception{
        User user = LoginDb.register(loginName, password);
        return user;
    }

    public ListModel<Card> getCardListModel(Subject subject) {
        return model.getCardListModel(subject);
    }

    public int RandomCard(Subject subject) {
        try {
            int ids;
            ids = CardDb.randomCard(subject);
            return ids;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public String getCardFront(int cardId){
        return CardDb.getCardFront(cardId);
    }
    public void logout() {
        model.clearSubjects(); //clears subjects and cards off of the list once signed out so it does not dupe
        model.clearCards();
        clearLoginPageListener.clearLoginPage();
        if (changeActivePanelListener != null) {
            changeActivePanelListener.onChangeActivePanel(ChangeActivePanelListener.PanelName.LOGIN);
        }
    }

}
