import java.sql.*;

public class SqlConnect {

    private final static String databaseURL = "jdbc:mysql://localhost:3306/Bank?characterEncoding=utf8";
    private final static String databaseUsername = "root";
    private final static String databasePassword = "f6#0~P)QY3o-nmBr#a)hXP-(3)>m>+";

    public static String[][] executeCustomerQuery(final String query) {
        String[][] result = new String[0][0];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int columnCount = resultSet.getMetaData().getColumnCount();
            result = new String[getNumberRows(query)][columnCount];

            int rowIndex = 0;
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    result[rowIndex][i-1] = resultSet.getString(i);
                }
                rowIndex++;
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[][] executeCustomerUpdate(final String query) {
        String[][] result = new String[0][0];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getNumberRows(String query){
        try{
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.last()){
                return resultSet.getRow();
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (Exception e){
            System.out.println("Error getting row count");
            e.printStackTrace();
        }
        return 0;
    }
}
