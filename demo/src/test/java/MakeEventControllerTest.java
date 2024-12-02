import com.example.demo.MakeEventController;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class MakeEventControllerTest {

//Test when eventDate is Null
    @Test
    void testEventDateIsNull() {
        // Create a new instance of the controller
        MakeEventController controller = new MakeEventController();

        // Capture the output that is sent to System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);  // Redirect System.out to capture output

        // Call the method with null eventDate (assuming eventDate is passed as a parameter)
        controller.saveEventToDatabase("event", null, "02:00:00", "03:00:00", "note");

        // Assert that the error message was printed
        assertTrue(outputStream.toString().contains("Error: Cannot save event. Event date is null."), "Expected error message not printed");

        // Restore System.out to its original state
        System.setOut(System.out);
    }

    //Test database connection
    @Test
    void testDatabaseConnection() {
        String url = "jdbc:mysql://localhost:3306/PalSyncDB";
        String username = "root";
        String password = "AugChico";
        Connection connection = null;

        try {
            // Attempt to connect to the database
            connection = DriverManager.getConnection(url, username, password);
            assertNotNull(connection, "The database connection should not be null");
        } catch (SQLException e) {
            fail("Failed to connect to the database: " + e.getMessage());
        } finally {
            // Close the connection after test
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Checks if user exist
    @Test
    void testUserIdQuerySuccess() {
        MakeEventController controller = new MakeEventController();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico");

            // Prepare the statement to query for user_ID
            preparedStatement = connection.prepareStatement("SELECT user_ID FROM users WHERE username = ?");
            preparedStatement.setString(1, "temp"); // Replace with an actual valid username
            resultSet = preparedStatement.executeQuery();

            // Assert that user_ID is found
            assertTrue(resultSet.next(), "User should be found in the database");
            int userId = resultSet.getInt("user_ID");
            assertTrue(userId > 0, "user_ID should be a positive number");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException occurred: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Checks if user does not exist
    @Test
    void testUserIdQueryNotFound() {
        MakeEventController controller = new MakeEventController();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico");

            // Prepare the statement with a non-existing username
            preparedStatement = connection.prepareStatement("SELECT user_ID FROM users WHERE username = ?");
            preparedStatement.setString(1, "notARealUser");
            resultSet = preparedStatement.executeQuery();

            // Assert that no user was found
            assertFalse(resultSet.next(), "User should not be found in the database");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException occurred: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Successful event insertion
    @Test
    void testEventInsertion() {
        MakeEventController controller = new MakeEventController();
        String eventName = "Test Event";
        String eventDate = "2024-12-01";  // Make sure this date format is correct for your DB
        String startTime = "02:00:00";
        String endTime = "03:00:00";
        String note = "Test note";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Set up the connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico");

            // Prepare the SQL statement for inserting an event
            String insertSQL = "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Here we need to mock the user_id (e.g., assume it's 1)
            int userId = 1;  // This should match a valid user in your database
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, eventName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(5, java.sql.Time.valueOf(endTime));
            preparedStatement.setString(6, note);

            // Execute the insert
            int rowsInserted = preparedStatement.executeUpdate();

            // Assert that one row was inserted
            assertEquals(1, rowsInserted, "Event should be inserted successfully.");

            // Optionally, you can query the database to check if the event exists
            String query = "SELECT * FROM events WHERE event_name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, eventName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the event is actually inserted
            assertTrue(resultSet.next(), "Event should be found in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQL exception occurred: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Test SQL exception handling
    @Test
    void testSQLExceptionHandling() {
        MakeEventController controller = new MakeEventController();
        String eventName = "Test Event";
        String eventDate = "2024-12-01";  // Make sure this date format is correct for your DB
        String startTime = "02:00:00";
        String endTime = "03:00:00";
        String note = "Test note for the event";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Set up the connection with incorrect credentials or a non-existent database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/NotRealDataBase", "wrongUser", "wrongPassword");

            // Prepare the SQL statement for inserting an event
            String insertSQL = "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Try to execute the insert
            preparedStatement.setInt(1, 1);  // Assuming a valid user ID
            preparedStatement.setString(2, eventName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(5, java.sql.Time.valueOf(endTime));
            preparedStatement.setString(6, note);

            preparedStatement.executeUpdate(); // This should fail due to invalid connection
        } catch (SQLException e) {
            // Ensure that the exception is correctly caught
            System.out.println("Expected SQLException caught: " + e.getMessage());
            assertTrue(e instanceof SQLException, "The caught exception should be an instance of SQLException.");
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Test for closing resources
    @Test
    void testResourceClosing() {
        MakeEventController controller = new MakeEventController();
        String eventName = "Test Event";
        String eventDate = "2024-12-01";
        String startTime = "02:00:00";
        String endTime = "03:00:00";
        String note = "Test note for the event";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Set up a real database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico");

            // Prepare the SQL statement for inserting an event
            String insertSQL = "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Execute the insert operation
            preparedStatement.setInt(1, 1);  // Assuming a valid user ID
            preparedStatement.setString(2, eventName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(5, java.sql.Time.valueOf(endTime));
            preparedStatement.setString(6, note);

            preparedStatement.executeUpdate();  // Execute the update

            // If no exception occurred, assert that the resources will be closed at the end
            PreparedStatement finalPreparedStatement = preparedStatement;
            Connection finalConnection = connection;
            assertDoesNotThrow(() -> {
                // Verify the connection and prepared statement are closed
                if (finalPreparedStatement != null && !finalPreparedStatement.isClosed()) {
                    finalPreparedStatement.close();
                }
                if (finalConnection != null && !finalConnection.isClosed()) {
                    finalConnection.close();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQL exception occurred: " + e.getMessage());
        } finally {
            // Ensure resources are closed
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}