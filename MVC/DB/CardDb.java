package MVC.DB;

import MVC.Model.Card;
import MVC.Model.Subject;
import MVC.Model.User;
import MVC.Utility.DbException;
import MVC.Utility.DbHelper;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CardDb {
    /**
     * TODO: Return cards by subject,add cards, delete cards, modify cards
     * C- use of prepared statements are required due to "?" used in variables
     */

    private static String SQL_GET_CARDS_BY_SUBJECT = "SELECT * FROM card WHERE subject_id = ?";
    private static String SQL_ADD_CARD = "INSERT INTO card (front, back, subject_id) VALUES (?,?,?)";
    private static String SQL_DELETE_CARD = "DELETE FROM card WHERE id=?";
    private static String SQL_MODIFY_CARD = "UPDATE card SET subject_id=?, front=?, back=? WHERE id=?";

    private static String SQL_RANDOM_CARD = "SELECT * FROM card ORDER BY RAND() LIMIT 1;";

    private static String SQL_GET_CARD_FRONT = "SELECT * FROM card WHERE id = ?;";

    /**
     * get the list of every card filtered by the subject
     *
     * @return
     * @throws Exception
     */
    public static List<Card> getCards(Subject subject) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Card> cards = new ArrayList<>();
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_GET_CARDS_BY_SUBJECT);
            ps.setInt(1, subject.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getInt("id"));
                card.setSubjectId(rs.getInt("subject_id"));
                card.setFront(rs.getString("front"));
                card.setBack(rs.getString("back"));
                cards.add(card);
            }
            System.out.println(MessageFormat.format("(DB) [{0}] card(s) loaded for subject [{1}]", cards.size(), subject.getName()));
            return cards;
        } finally {
            DbHelper.close(rs);
            DbHelper.close(ps);
        }
    }

    /**
     * update an existing card in the database
     *
     * @param currentCard
     * @param newFront
     * @param newBack
     * @throws Exception
     */
    public static void updateCard(Card currentCard, String newFront, String newBack) throws Exception {
        PreparedStatement ps = null;
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_MODIFY_CARD);
            ps.setInt(1, currentCard.getSubjectId());
            ps.setString(2, newFront);
            ps.setString(3, newBack);
            ps.setInt(4, currentCard.getId());
            ps.executeUpdate();
            System.out.println(MessageFormat.format("(DB) Card [{0}] modified to [{1}:{2}]", currentCard.getId(), newFront, newBack));
        } finally {
            DbHelper.close(ps);
        }
    }

    /***
     *
     * @param subject
     * @param card
     * @return
     * @throws Exception
     */
    public static Card addCard(Subject subject, Card card) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_ADD_CARD, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, card.getFront());
            ps.setString(2, card.getBack());
            ps.setInt(3, subject.getId());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                card.setId(rs.getInt(1));
            }
            card.setSubjectId(subject.getId());
            card.setFront(card.getFront());
            card.setBack(card.getBack());
            System.out
                    .println(MessageFormat.format("(DB) Card [{0}:{1}] added to subject [{2}]", card.getFront(), card.getBack(), subject.getName()));
            return card;
        } finally {
            DbHelper.close(ps);
        }
    }

    /**
     * remove a card from the database
     *
     * @param cardToRemove
     * @throws Exception
     */
    public static void deleteCard(Card cardToRemove) throws Exception {
        PreparedStatement ps = null;
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_DELETE_CARD);
            ps.setInt(1, cardToRemove.getId());
            ps.executeUpdate();
            System.out.println(
                    MessageFormat.format("(DB) Card [{0}] [{1}/{2}] removed", cardToRemove.getId(), cardToRemove.getFront(), cardToRemove.getBack()));
        } finally {
            DbHelper.close(ps);
        }
    }
    public static int randomCard(Subject subject) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = DbHelper.getInstance().getConnection();
        String front,back;
        int ids;
        try{
            ps = connection.prepareStatement(SQL_RANDOM_CARD, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            Card card = new Card();
            if (rs.next()) {
                card.setId(rs.getInt(1));
            }
            card.setSubjectId(subject.getId());
            card.setFront(card.getFront());
            card.setBack(card.getBack());
            ids = card.getId();
            return ids;
        } finally {
            DbHelper.close(ps);
        }
    }

    public static String getCardFront(int cardId){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = DbHelper.getInstance().getConnection();
        String front = "";
        try{
            ps = connection.prepareStatement(SQL_GET_CARD_FRONT, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                front = rs.getString(1);
            }
            return front;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbHelper.close(ps);
        }
    }

    // TEST
    /*public static void main(String[] args) {
        try {
            List<Subject> subjects = SubjectDb.getSubjects();
            if( subjects.isEmpty() ) {
                Subject s1 = SubjectDb.addSubject(new Subject().withName("testsub"));
                subjects.add(s1);
            }
            Card c1 = CardDb.addCard(subjects.get(0), "front1", "back1");
            Card c2 = CardDb.addCard(subjects.get(0), "front2", "back22");
            Card c3 = CardDb.addCard(subjects.get(0), "front3", "back3");
            CardDb.deleteCard(c3);
            CardDb.updateCard(c2, c2.getFront(), "back2");
            List<Card> cards = CardDb.getCards(subjects.get(0));
            cards.stream().forEach(System.out::println);
        } catch (Exception | DbException e) {
            System.err.println(e.getMessage());
        }
    }*/

}
