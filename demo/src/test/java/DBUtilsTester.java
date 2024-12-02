import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.DBUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.sql.*;

public class DBUtilsTester {

    @Mock
    Connection testConnection;

    @Mock
    PreparedStatement testStatement;

    @Mock
    ResultSet testResult;

    Connection realConnection;


    // Before each test, we connect to the database.
    @BeforeEach
    public void setup() throws SQLException{
        realConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/palsync-login", "root", "Silverlining1986");
    }


    //After each test, we remove any of the user sample data we added in for the test.
    @AfterEach
    public void reset() throws SQLException{
        PreparedStatement resetPS = realConnection.prepareStatement("DELETE FROM users WHERE username = ?");
        resetPS.setString(1, "FakeNewUser");
        resetPS.setString(2, "FakeExistingUser");
        resetPS.executeUpdate();

        if (realConnection != null && !realConnection.isClosed()) {
            realConnection.close();
        }

    }

    /*
    The method "existingUserErrorTest" is used to make a fake connection to a fake database to
    try to test our code and see if our function made in DBUtils (specifically our signUpUser method)
    works.

    The method "SQLUserAddTest" actually connects to the PalSync database
    (MAKE SURE YOU REPLACE IT WHEN YOU TEST IT, IT'S ON LINE 60),
    creates a fake new user as if we were logging in and then checks to see if it is present in the database
    and that the connection and methods worked correctly.

     */

    @Test
    public void existingUserErrorTest() throws Exception {

        PreparedStatement insertSQLCommand = realConnection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");

        insertSQLCommand.setString(1, "FakeExisitinguser");
        insertSQLCommand.setString(2, "Fake321");
        insertSQLCommand.executeUpdate();

        try {
            DBUtils.signUpUser(null, "FakeExistingUser" , "Fake321");
            fail("No Alert Message Thrown");
        } catch (Exception thrownException){
            // If it catches it, then it means the test passed. The error message was thrown. EPIK
        }

    }

    @Test
    public void SQLUserAddTest() throws SQLException {

        DBUtils.signUpUser(null, "FakeNewUser", "Fake321");

        PreparedStatement insertSQLCommand = realConnection.prepareStatement("SELECT * FROM users WHERE username = ?");

        insertSQLCommand.setString(1, "FakeNewUser");

        try (ResultSet RS = insertSQLCommand.executeQuery()) {
            assertTrue(RS.next(), "User should be in the database");
            assertEquals("FakeNewUser", RS.getString("username"), "Usernames should match.");
        }
    }


}
