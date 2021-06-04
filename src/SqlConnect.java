import java.sql.*;

public class SqlConnect {

    private final static String databaseURL = "jdbc:mysql://localhost:3306/Bank?characterEncoding=utf8";
    private final static String databaseUsername = "root";
    private final static String databasePassword = "f6#0~P)QY3o-nmBr#a)hXP-(3)>m>+";

    public SqlConnect() {
        // Connect to the database
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Checking_Account");
            while (resultSet.next())
                System.out.println(resultSet.getInt(1) +" " +resultSet.getInt(2) + " " +
                        resultSet.getInt(3));
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
