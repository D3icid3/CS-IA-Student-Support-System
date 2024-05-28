package MVC.DB;
import MVC.Controller.MainController;
import MVC.Model.Subject;
import MVC.Model.User;
import MVC.Utility.DbException;
import MVC.Utility.DbHelper;
import com.sun.tools.javac.Main;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SubjectDb {
    /**
     * TODO: Return Subjects, add subjects, delete subjects, Update/modify subjects
     * C- use of prepared statements are required due to "?" used in variables
     */

    private static String SQL_GET_SUBJECTS = "SELECT * FROM subject ORDER BY name";
    private static String SQL_ADD_SUBJECT = "INSERT INTO subject (name) VALUES (?)";
    private static String SQL_DELETE_SUBJECT = "DELETE FROM subject WHERE id = ?";
    private static String SQL_MODIFY_SUBJECT = "UPDATE subject SET name=? WHERE id=?";

    /**
     * get the list of every subject in the database
     *
     * @return
     * @throws Exception
     */
    public static List<Subject> getSubjects() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Subject> subjects = new ArrayList<>(); //array list == dynamic array
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_GET_SUBJECTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("name"));
                subjects.add(subject);
            }
            System.out.println(MessageFormat.format("(DB) [{0}] subject(s) loaded", subjects.size()));
            return subjects;
        } finally {
            DbHelper.close(rs);
            DbHelper.close(ps);
        }
    }

    /**
     * update an existing subject in the database
     *
     * @param subjectToUpdate
     * @throws Exception
     */
    public static void updateSubject(Subject subjectToUpdate) throws Exception {
        PreparedStatement ps = null;
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_MODIFY_SUBJECT);
            ps.setString(1, subjectToUpdate.getName());
            ps.setInt(2, subjectToUpdate.getId());
            ps.executeUpdate();
            System.out.println(MessageFormat.format("(DB) Subject [{0}] name modified to [{1}]", subjectToUpdate.getId(), subjectToUpdate.getName()));
        } finally {
            DbHelper.close(ps);
        }
    }

    /**
     * add a new subject to the database
     *
     * @param subject
     * @return new subject with generated ID
     * @throws Exception
     */
    public static Subject addSubject(Subject subject) throws DbException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_ADD_SUBJECT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subject.getName());
            ps.executeUpdate();
            Subject newSubject = new Subject();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                newSubject.setId(rs.getInt(1));
            }
            newSubject.setName(subject.getName());
            System.out.println(MessageFormat.format("(DB) Subject [{0}]:[{1}] added", newSubject.getId(), newSubject.getName()));
            return newSubject;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbHelper.close(ps);
        }
    }

    /**
     * remove a subject from the database
     *
     * @param subjectToRemove
     * @throws Exception
     */
    public static void deleteSubject(Subject subjectToRemove) throws DbException {
        PreparedStatement ps = null;
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQL_DELETE_SUBJECT);
            ps.setInt(1, subjectToRemove.getId());
            ps.executeUpdate();
            System.out.println(MessageFormat.format("(DB) Subject [{0}]:[{1}] removed", subjectToRemove.getId(), subjectToRemove.getName()));
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbHelper.close(ps);
        }
    }


    // TEST
    /*
    public static void main(String[] args) {
        try {
            Subject s1 = SubjectDb.addSubject(new Subject().withName("s1"));
            System.out.println(s1.getId() + "::" + s1.getName());
            Subject s2 = SubjectDb.addSubject(new Subject().withName("s2r"));
            Subject s3 = SubjectDb.addSubject(new Subject().withName("s3"));
            SubjectDb.deleteSubject(s3);
            s2.setName("s2");
            SubjectDb.updateSubject(s2);
            List<Subject> subjects = SubjectDb.getSubjects();
            subjects.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

     */

}
