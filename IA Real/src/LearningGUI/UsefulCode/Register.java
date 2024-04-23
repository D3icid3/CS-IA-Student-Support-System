package LearningGUI.UsefulCode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Register {
    public static void register(String Username_Input, String Password_Input) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-ia", "root", "@Heineken033"); //establishes connection to MYSQL
            Statement statement = ((Connection) connection).createStatement(); //creates the actual connection between the two
            Statement GiveStatement = connection.createStatement();
            ResultSet check = GiveStatement.executeQuery("SELECT COUNT(*) FROM username_password WHERE username = '%s';");
            System.out.println(check);

            /*if()
                GiveStatement.executeUpdate(String.format("INSERT INTO `jdbc-ia`.`username_password`(`Username`,`Password`) VALUES( \"%s\", \"%s\");", Username_Input,Password_Input));
            }

             */
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
