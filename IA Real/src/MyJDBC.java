import LearningGUI.UsefulCode.Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class MyJDBC {
    public static void main(String[] args){
        Scanner KB = new Scanner(System.in);
        try {
            String username_input = "Andi", password_input = "Bandi";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-ia", "root", "@Heineken033"); //establishes connection to MYSQL
            Statement statement = ((Connection) connection).createStatement(); //creates the actual connection between the two
            ResultSet resultSet = statement.executeQuery("select * from username_password");//gets all the usernames added to the sql database
            Statement addPassandUsername = connection.createStatement();
            Register.register(username_input,password_input);
            //addPassandUsername.executeUpdate(String.format("INSERT INTO `jdbc-ia`.`username_password`(`Username`,`Password`) VALUES( \"%s\", \"%s\");", username_input,password_input));
            //INSERT INTO `jdbc-ia`.`username_password`(`Username`,`Password`) VALUES ( "Ramona Kiss", "456");
            /*while(username_input != "no"){
                System.out.println("enter username wanting to be added");
                username_input = KB.nextLine();
                System.out.println("enter password wanting to be added");
                password_input = KB.nextLine();
                Statement addPassandUsername = connection.createStatement();
                addPassandUsername.executeUpdate("insert into username_password" + "(Username,Password)" + "values(Bertold,Party)");
                //addPassandUsername.executeUpdate("insert into username_password" + "(Username,Password)" + String.format("values(%s,%s)", username_input, password_input));
                System.out.println("all good");

            }
             */
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username")); //prints out all usernames added to the SQL database
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
