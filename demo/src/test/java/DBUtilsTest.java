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

    @Test
    void testUserCreation() {
        System.out.println("\n=== Running testUserCreation ===");

        String newUsername = "newTestUser"; // A unique username for the test
        String password = "testPassword123";

        Connection connection = null;

        try {
            // Step 1: Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/palsync-login", "root", "Silverlining1986");
            System.out.println("Connected to the database.");

            // Step 2: Insert the new user into the database
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            insertStatement.setString(1, newUsername);
            insertStatement.setString(2, password);
            int rowsAffected = insertStatement.executeUpdate();

            // Verify that one row was inserted
            assertEquals(1, rowsAffected, "Exactly one user should be inserted.");
            System.out.println("User creation verified: " + newUsername);

            // Step 3: Verify the user exists in the database
            PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            checkStatement.setString(1, newUsername);
            ResultSet resultSet = checkStatement.executeQuery();

            assertTrue(resultSet.next(), "User should be found in the database.");
            System.out.println("Verified: User exists in the database.");

        } catch (SQLException e) {
            fail("SQL exception occurred: " + e.getMessage());
        } finally {
            // Step 4: Ensure the database connection is closed
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("=== Finished testUserCreation ===");
    }

    @Test
    void testTemporaryAccountLogin() {
        System.out.println("\n=== Running testTemporaryAccountLogin ===");

        String tempUsername = "tempUser";
        String tempPassword = "tempPassword123";

        Connection connection = null;

        try {
            // Step 1: Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/palsync-login", "root", "Silverlining1986");
            System.out.println("Connected to the database.");

            // Step 2: Insert the temporary user into the database
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            insertStatement.setString(1, tempUsername);
            insertStatement.setString(2, tempPassword);
            int rowsAffected = insertStatement.executeUpdate();

            // Verify that the user was inserted
            assertEquals(1, rowsAffected, "Temporary user should be created successfully.");
            System.out.println("Temporary user created successfully.");

            // Step 3: Attempt to log in with the temporary user credentials
            PreparedStatement loginStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            loginStatement.setString(1, tempUsername);
            loginStatement.setString(2, tempPassword);
            ResultSet resultSet = loginStatement.executeQuery();

            assertTrue(resultSet.next(), "Temporary user should be able to log in successfully.");
            System.out.println("Temporary user login verified successfully.");

        } catch (SQLException e) {
            fail("SQL exception occurred: " + e.getMessage());
        } finally {
            // Step 4: Ensure the database connection is closed
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("=== Finished testTemporaryAccountLogin ===");
    }




}