import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.*;

class DBUtilsTest {
    @Test
    void testUsernameExists() {
        System.out.println("\n=== Running testUsernameExists ===");

        String existingUsername = "temp";
        String password = "123";

        Connection connection = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/palsync-login", "root", "Silverlining1986");
            System.out.println("Connected to the database.");

            // Insert the test user
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            insertStatement.setString(1, existingUsername);
            insertStatement.setString(2, password);
            insertStatement.executeUpdate();
            System.out.println("Test user inserted successfully.");

            // Verify the user already exists
            PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            checkStatement.setString(1, existingUsername);
            ResultSet resultSet = checkStatement.executeQuery();

            assertTrue(resultSet.next(), "User should already exist in the database.");
            System.out.println("Verified: User already exists.");

        } catch (SQLException e) {
            fail("SQL exception occurred: " + e.getMessage());
        } finally {
            // Ensure the database connection is closed
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("=== Finished testUsernameExists ===");
    }
}