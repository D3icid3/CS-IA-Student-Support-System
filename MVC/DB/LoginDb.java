package MVC.DB;

import MVC.Model.User;
import MVC.Utility.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDb {
    /**
     * TODO: Create a simple login and register SQL statement (See Login_Page_Code for inspiration)
     * C- use of prepared statements are required due to "?" used in variables
     */
    private static String SQL_LOGIN = "SELECT * FROM user WHERE login_name = ? AND password = ?";

    private static String SQL_CHECKER = "SELECT COUNT(*) FROM user WHERE login_name = ?;";

    private static String SQL_MAXID = "SELECT max(id) FROM user;";

    private static String SQL_REGISTER = "INSERT INTO user VALUES (?,?,?);";

    /**
     *
     * @param loginName
     * @param password
     * @return user
     * @throws Exception
     *          -user not found
     */

    public static User login(String loginName, String password) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = DbHelper.getInstance().getConnection();
            ps = connection.prepareStatement(SQL_LOGIN);
            ps.setString(1, loginName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            // we expect one user
            if (rs.next()) {
                // we found the user in the database
                System.out.println("user logged in");
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUserName(loginName);
                return user;
            } else {
                throw new Exception("user not found");
            }
        } finally {
            DbHelper.close(rs);
            DbHelper.close(ps);
        }

    }

    /**
     * TODO: fix max id - id needs to become incremented in DB for code redability
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    public static User register(String loginName, String password) throws Exception {
        PreparedStatement psChecker = null;
        PreparedStatement psMaxid = null;
        PreparedStatement psRegister= null;


        ResultSet rsChecker = null;
        ResultSet rsMaxid = null;
        ResultSet rsRegister = null;

        int check = -1;
        int maxID = -1;
        try {
            Connection connection = DbHelper.getInstance().getConnection();

            psChecker = connection.prepareStatement(SQL_CHECKER);
            psChecker.setString(1, loginName);
            rsChecker = psChecker.executeQuery();

            psMaxid = connection.prepareStatement(SQL_MAXID);
            rsMaxid = psMaxid.executeQuery();




            while (rsMaxid.next())
                maxID = rsMaxid.getInt(1);

            while (rsChecker.next())
                check = rsChecker.getInt(1);

            if (check == 0) {
                System.out.println("Registration Complete");
                User user = new User();
                user.setId(maxID + 1);
                user.setUserName(loginName);
                user.setPassword(password);

                psRegister = connection.prepareStatement(SQL_REGISTER);
                psRegister.setString(1, String.valueOf(user.getId()));
                psRegister.setString(2, user.getUserName());
                psRegister.setString(3, user.getPassword());
                psRegister.executeUpdate();

                return user;


            } else {
                throw new Exception("User already exists");
            }
        }finally{
            DbHelper.close(rsChecker);
            DbHelper.close(psChecker);
            DbHelper.close(psRegister);
            DbHelper.close(psMaxid);
            DbHelper.close(rsMaxid);


        }
    }

    //TEST
    /*
    public static void main(String[] args) {
        try {
            login("Andi", "Band2i");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            register("Soma", "Kemeny");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

     */
}
