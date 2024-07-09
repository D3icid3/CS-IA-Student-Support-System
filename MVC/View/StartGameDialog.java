package MVC.View;

import MVC.Controller.MainController;
import MVC.Controller.MainController;
import MVC.Model.Card;
import MVC.Model.Subject;
import MVC.Controller.MainController;
import MVC.Model.Subject;
import MVC.Model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartGameDialog extends JDialog {
    private MainController MainController;
    private JFrame deckOwner;

    private JList<Subject> subjectList;

    Subject subjectForGame;


    StartGameDialog(JFrame owner, MainController MainController, Subject subject) {
        super(owner, true);
        deckOwner = owner;
        subjectForGame = subject;
        this.MainController = MainController;
        gameComponents();
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void gameComponents(){

        setTitle("Game Time");
        setLayout(new FlowLayout());

        JLabel frontBackPanel = new JLabel(""); //TODO: Text area should start with front of card
        add(frontBackPanel); //Text panel for front and back

        JButton Next = new JButton("Next");
        Next.addActionListener(e -> StartGameDialog.this.setNewCard(frontBackPanel));
        add(Next);  //Button for showing new random

        JButton Flip = new JButton("Flip");
        Flip.addActionListener(e -> StartGameDialog.this.gameFlip());
        add(Flip);//Button for showing back of card

        JButton endGame = new JButton("End Game");
        endGame.addActionListener(e -> StartGameDialog.this.gameEnd());
        add(endGame);//Button for taking user back to the subject list panel

    }
    private void gameNext(){

    }


    private void gameFlip(){

    }
    private void gameEnd(){
        dispose(); //since StartGameDialog extends JDialog no frame needs to be referenced
    }

    private int getRandomCardID(){ //gets the id of a random card for the specific subject the game was started with
            int ids;
            ids = MainController.RandomCard(subjectForGame);
            return ids;
    }

    private String getCardFront(int cardId){
        return MainController.getCardFront(cardId);
    }
    private void setNewCard(JLabel textbox){
        int newId = getRandomCardID();
        String front = getCardFront(newId);
        textbox = new JLabel(front);
    }
    private void getCardBack(){

    }

}
