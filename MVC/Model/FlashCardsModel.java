package MVC.Model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class    FlashCardsModel {

    DefaultListModel<Subject> subjects = new DefaultListModel<>();

    Map<Integer, DefaultListModel<Card>> cardModels = new HashMap<>(); //Create a hashmap for all the cards in a subject


    public FlashCardsModel() {
    }

    public void addSubject(Subject subject) {
        subjects.addElement(subject);
        cardModels.put(subject.getId(), new DefaultListModel<>());
    }

    public void addCard(Subject s1, Card c1) {
        cardModels.get(s1.getId()).addElement(c1);
    }

    public ListModel<Subject> getSubjectListModel() {
        return subjects;
    }

    public ListModel<Card> getCardListModel(Subject subject) {
        return cardModels.get(subject.getId());
    }

    public void removeSubject(Subject subject) {
        subjects.removeElement(subject);
        cardModels.remove(subject);
    }

    public void clearSubjects() {
        subjects.clear();
    }

    public void clearCards() {
        cardModels.clear();
    }
}
