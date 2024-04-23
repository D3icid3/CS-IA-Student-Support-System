package LearningGUI.Vid3;

import LearningGUI.UsefulCode.Register;

import java.sql.*;
class MysqlCon{
    public static void main(String args[]) {
        try {
            String x = "Andi", y = "123";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Real IA", "root", "@Heineken033");
//here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emp");
            Register.register(x,y);
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);}
    }
    }