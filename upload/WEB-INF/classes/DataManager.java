

import java.sql.*;

public class DataManager {
    private String dbURL;
    private String username;
    private String password;

    public DataManager () {
        this.dbURL = Config.dbURL;
        this.username = Config.username;
        this.password = Config.password;
    }

        public DataManager (String dbURL, String username, String password) {
        this.dbURL = dbURL;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection () {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to database");
            return connection;

        } catch(SQLException e) {
            System.out.println("Something went wrong. cannot to connect to database");
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUserExist(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet re = statement.executeQuery();
            while(re.next()) {
                return true;
            }
            connection.close();
        } catch(SQLException e) {
            System.out.println("Something went wrong. cannot to connect to database");
            e.printStackTrace();
        }
        return false;
    }

}
