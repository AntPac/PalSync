import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.DBUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.*;

public class SignUpTest {

    @Mock
    Connection testConnection;

    @Mock
    PreparedStatement testStatement;

    @Mock
    ResultSet testResult;



    public void signupTest() {
        MockitoAnnotations.openMocks(this);
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

        testConnection.prepareStatement("SELECT * FROM users WHERE username = ?");
        when(testConnection.prepareStatement(anyString())).thenReturn(testStatement);
        when(testStatement.executeQuery()).thenReturn(testResult);
        when(testResult.isBeforeFirst()).thenReturn(true);

        try {
            DBUtils.signUpUser(null, "FakeExistingUser", "FakePassword");
            fail("Expected ERROR alert not thrown");
        } catch (Exception e) {
           // Test passes here
        }

    }

    @Test
    public void SQLUserAddTest() throws SQLException {

        Connection testConnection = DriverManager.getConnection("jdbc:mysql://192.168.4.34:3306/PalSyncData", "root", "Silverlining1986");

        DBUtils.signUpUser(null, "FakeNewUser" , "FakePassword");
        PreparedStatement PS = testConnection.prepareStatement("SELECT * FROM users WHERE username = ?");

        PS.setString(1, "FakeNewUser");
        try (ResultSet testResults = PS.executeQuery()) {
            assertTrue(testResults.next(), "User should exist in the database");
            assertEquals("FakeNewUser", testResults.getString("username"), "Username should match");
        }

        testConnection.close();

    }



}
