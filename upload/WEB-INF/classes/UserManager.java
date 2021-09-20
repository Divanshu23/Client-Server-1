import java.nio.ByteBuffer;
import java.sql.*;
import java.util.UUID;

public class UserManager {
    private DataManager dataManager;
    public UserManager (DataManager dataManager){
        this.dataManager = dataManager;
    }

    public static byte[] asBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    public boolean addUser(String username, String password) {
        try {
            if(!isUserExist(username, password)) {
                Connection connection = dataManager.getConnection();

                UUID uuid = UUID.randomUUID();
                String sql = "INSERT INTO users (id, name, password)" + " VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setBytes(1, asBytes(uuid));
                statement.setString(2, username);
                statement.setString(3, password);
                statement.execute();
                statement.close();
                connection.close();
                return true;
            }

        } catch(SQLException e) {
            System.out.println("Something went wrong. cannot to connect to database");
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUserExist(String username, String password) {
        try {
            Connection connection = dataManager.getConnection();
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
