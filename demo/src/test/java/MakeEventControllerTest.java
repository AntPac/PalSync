import com.example.demo.MakeEventController;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakeEventControllerTest {
    @Test
    void testComboBox() {
        System.out.println("\n=== Running testComboBox ===");
        // Simulate ComboBox components
        List<String> startTimeItems = new ArrayList<>();
        List<String> endTimeItems = new ArrayList<>();

        // Simulate the initialization logic
        for (int i = 0; i < 24; i++) {
            String time = String.format("%02d:00", i);
            startTimeItems.add(time);
            endTimeItems.add(time);
        }

        // Print the items
        System.out.println("startTimeComboBox items: " + startTimeItems);
        System.out.println("endTimeComboBox items: " + endTimeItems);

        // Assertions to verify the ComboBox contains the correct times
        assertEquals(24, startTimeItems.size(), "startTimeComboBox should contain 24 items.");
        assertEquals(24, endTimeItems.size(), "endTimeComboBox should contain 24 items.");
        assertEquals("00:00", startTimeItems.get(0), "First item in startTimeComboBox should be 00:00.");
        assertEquals("23:00", startTimeItems.get(23), "Last item in startTimeComboBox should be 23:00.");
        assertEquals("00:00", endTimeItems.get(0), "First item in endTimeComboBox should be 00:00.");
        assertEquals("23:00", endTimeItems.get(23), "Last item in endTimeComboBox should be 23:00.");

        System.out.println("ComboBox was successfully.");


        System.out.println("=== Finished testComboBox ===");
    }
    @Test
    void testSaveButtonFunctionality() {
        System.out.println("\n=== Running testSaveButtonFunctionality ===");
        String eventName = "Test Event";
        String selectedDate = "2024-12-01"; // Simulating a valid selected date
        String startTime = "10:00";
        String endTime = "11:00";
        String note = "Test note";

        // Case 1: All valid inputs
        String result = simulateSaveButtonLogic(eventName, selectedDate, startTime, endTime, note);
        assertEquals("Success: Event saved!", result, "Expected success message for valid inputs.");
        System.out.println(result);

        // Case 2: Missing selectedDate
        result = simulateSaveButtonLogic(eventName, null, startTime, endTime, note);
        assertEquals("Error: No date selected for the event.", result, "Expected error message for missing date.");
        System.out.println(result);

        // Case 3: Missing fields
        result = simulateSaveButtonLogic("", selectedDate, startTime, endTime, note);
        assertEquals("Error: Please fill in all the required fields.", result, "Expected error message for missing fields.");
        System.out.println(result);
        System.out.println("=== Finished testSaveButtonFunctionality ===");
    }
    private String simulateSaveButtonLogic(String eventName, String selectedDate, String startTime, String endTime, String note) {
        // Simulate the save button logic
        if (selectedDate == null) {
            return "Error: No date selected for the event.";
        }
        if (eventName.isEmpty() || startTime == null || endTime == null) {
            return "Error: Please fill in all the required fields.";
        }
        // Simulate saving the event to the database
        return "Success: Event saved!";
    }

    //Test when eventDate is Null
    @Test
    void testEventDateIsNull() {
        System.out.println("\n=== Running testEventDateIsNull ===");

        MakeEventController controller = new MakeEventController();
        System.out.println("Controller created in testEventDateIsNull.");

        controller.saveEventToDatabase("event", null, "02:00:00", "03:00:00", "note");

        System.out.println("=== Finished testEventDateIsNull ===");
    }

    //Test database connection
    @Test
    void testDatabaseConnection() {
        System.out.println("\n=== Running testDatabaseConnection ===");

        String url = "jdbc:mysql://localhost:3306/me";
        String username = "root";
        String password = "Password1";
        Connection connection = null;

        try {
            // Attempt to connect to the database
            System.out.println("Attempting to connect to the database...");
            connection = DriverManager.getConnection(url, username, password);

            // Assert that the connection is not null
            assertNotNull(connection, "The database connection should not be null");
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            // Print error message and fail the test
            System.out.println("Failed to connect to the database: " + e.getMessage());
            fail("Failed to connect to the database: " + e.getMessage());
        } finally {
            // Close the connection after the test
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("=== Finished testDatabaseConnection ===");
    }


    //Checks if user exist
    @Test
    void testUserIdQuerySuccess() {
        System.out.println("\n=== Running testUserIdQuerySuccess ===");

        MakeEventController controller = new MakeEventController();
        System.out.println("Controller created in testUserIdQuerySuccess.");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Attempt to connect to the database
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/me", "root", "Password1");
            System.out.println("Database connection established.");

            // Prepare the statement to query for user_ID
            System.out.println("Preparing SQL query for user_ID...");
            preparedStatement = connection.prepareStatement("SELECT user_ID FROM users WHERE username = ?");
            preparedStatement.setString(1, "temp"); // Replace with an actual valid username
            System.out.println("Executing SQL query...");
            resultSet = preparedStatement.executeQuery();

            // Assert that user_ID is found
            if (resultSet.next()) {
                System.out.println("User found in the database.");
                int userId = resultSet.getInt("user_ID");
                System.out.println("Retrieved user_ID: " + userId);
                assertTrue(userId > 0, "user_ID should be a positive number");
            } else {
                System.out.println("No user found with the username 'temp'.");
                fail("User should be found in the database.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException occurred: " + e.getMessage());
            e.printStackTrace();
            fail("SQLException occurred: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                    System.out.println("ResultSet closed.");
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    System.out.println("PreparedStatement closed.");
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close resources: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("=== Finished testUserIdQuerySuccess ===");
    }


    //Checks if user does not exist
    @Test
    void testUserIdQueryNotFound() {
        System.out.println("\n=== Running testUserIdQueryNotFound ===");

        MakeEventController controller = new MakeEventController();
        System.out.println("Controller created in testUserIdQueryNotFound.");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Attempt to connect to the database
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/me", "root", "Password1");
            System.out.println("Database connection established.");

            // Prepare the statement with a non-existing username
            System.out.println("Preparing SQL query for non-existing user...");
            preparedStatement = connection.prepareStatement("SELECT user_ID FROM users WHERE username = ?");
            preparedStatement.setString(1, "notARealUser");
            System.out.println("Executing SQL query...");
            resultSet = preparedStatement.executeQuery();

            // Assert that no user was found
            if (!resultSet.next()) {
                System.out.println("No user found with the username 'notARealUser'.");
            } else {
                System.out.println("Unexpected user found for username 'notARealUser'.");
                fail("User should not be found in the database");
            }
        } catch (SQLException e) {
            System.out.println("SQLException occurred: " + e.getMessage());
            e.printStackTrace();
            fail("SQLException occurred: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                    System.out.println("ResultSet closed.");
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    System.out.println("PreparedStatement closed.");
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close resources: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("=== Finished testUserIdQueryNotFound ===");
    }


    //Successful event insertion
    @Test
    void testEventInsertion() {
        System.out.println("\n=== Running testEventInsertion ===");

        MakeEventController controller = new MakeEventController();
        System.out.println("Controller created in testEventInsertion.");

        String eventName = "Test Event";
        String eventDate = "2024-12-01";  // Make sure this date format is correct for your DB
        String startTime = "02:00:00";
        String endTime = "03:00:00";
        String note = "Test note";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Set up the connection
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/me", "root", "Password1");
            System.out.println("Database connection established.");

            // Prepare the SQL statement for inserting an event
            System.out.println("Preparing SQL insert statement...");
            String insertSQL = "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Mock the user_id (assume it's 1)
            int userId = 1;  // This should match a valid user in your database
            System.out.println("Setting parameters for the insert statement...");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, eventName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(5, java.sql.Time.valueOf(endTime));
            preparedStatement.setString(6, note);

            // Execute the insert
            System.out.println("Executing SQL insert...");
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
            assertEquals(1, rowsInserted, "Event should be inserted successfully.");

            // Query the database to check if the event exists
            System.out.println("Verifying that the event was inserted...");
            String query = "SELECT * FROM events WHERE event_name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, eventName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the event is actually inserted
            if (resultSet.next()) {
                System.out.println("Event found in the database.");
            } else {
                System.out.println("Event not found in the database.");
            }
            assertTrue(resultSet.next(), "Event should be found in the database.");

            // Close the ResultSet
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
            e.printStackTrace();
            fail("SQL exception occurred: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    System.out.println("PreparedStatement closed.");
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close resources: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("=== Finished testEventInsertion ===");
    }


    //Test SQL exception handling
    @Test
    void testSQLExceptionHandling() {
        System.out.println("\n=== Running testSQLExceptionHandling ===");

        MakeEventController controller = new MakeEventController();
        System.out.println("Controller created in testSQLExceptionHandling.");

        String eventName = "Test Event";
        String eventDate = "2024-12-01";  // Ensure this date format matches your database schema
        String startTime = "02:00:00";
        String endTime = "03:00:00";
        String note = "Test note for the event";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Attempt to connect to the database with incorrect credentials or to a non-existent database
            System.out.println("Attempting to connect to a non-existent database...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/NotRealDataBase", "wrongUser", "wrongPassword");

            // Prepare the SQL statement for inserting an event
            System.out.println("Preparing SQL insert statement...");
            String insertSQL = "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Set parameters for the SQL insert statement
            System.out.println("Setting parameters for the insert statement...");
            preparedStatement.setInt(1, 1);  // Assuming a mock user ID
            preparedStatement.setString(2, eventName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(5, java.sql.Time.valueOf(endTime));
            preparedStatement.setString(6, note);

            // Attempt to execute the insert statement
            System.out.println("Executing SQL insert statement...");
            preparedStatement.executeUpdate(); // This should fail due to invalid connection
        } catch (SQLException e) {
            // Catch and handle the expected SQLException
            System.out.println("Expected SQLException caught: " + e.getMessage());
            assertTrue(e instanceof SQLException, "The caught exception should be an instance of SQLException.");
        } finally {
            // Ensure resources are closed
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    System.out.println("PreparedStatement closed.");
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close resources: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("=== Finished testSQLExceptionHandling ===");
    }

    //Test for closing resources
    @Test
    void testResourceClosing() {
        System.out.println("\n=== Running testResourceClosing ===");

        MakeEventController controller = new MakeEventController();
        System.out.println("Controller created in testResourceClosing.");

        String eventName = "Test Event";
        String eventDate = "2024-12-01";  // Ensure this date format matches your database schema
        String startTime = "02:00:00";
        String endTime = "03:00:00";
        String note = "Test note for the event";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Set up a real database connection
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/me", "root", "Password1");
            System.out.println("Database connection established.");

            // Prepare the SQL statement for inserting an event
            System.out.println("Preparing SQL insert statement...");
            String insertSQL = "INSERT INTO events (user_id, event_name, event_date, start_time, end_time, note) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            // Execute the insert operation
            System.out.println("Setting parameters for the insert statement...");
            preparedStatement.setInt(1, 1);  // Assuming a valid user ID
            preparedStatement.setString(2, eventName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(eventDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(5, java.sql.Time.valueOf(endTime));
            preparedStatement.setString(6, note);

            System.out.println("Executing SQL insert statement...");
            preparedStatement.executeUpdate();  // Execute the update
            System.out.println("SQL insert executed successfully.");

            // Verify that resources can be closed without exceptions
            System.out.println("Verifying resource closure...");
            PreparedStatement finalPreparedStatement = preparedStatement;
            Connection finalConnection = connection;

            assertDoesNotThrow(() -> {
                if (finalPreparedStatement != null && !finalPreparedStatement.isClosed()) {
                    finalPreparedStatement.close();
                    System.out.println("PreparedStatement closed successfully.");
                }
                if (finalConnection != null && !finalConnection.isClosed()) {
                    finalConnection.close();
                    System.out.println("Connection closed successfully.");
                }
            });

        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
            e.printStackTrace();
            fail("SQL exception occurred: " + e.getMessage());
        } finally {
            // Ensure resources are closed
            System.out.println("Closing resources in finally block...");
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                    System.out.println("PreparedStatement closed in finally block.");
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed in finally block.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close resources: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("=== Finished testResourceClosing ===");
    }


}