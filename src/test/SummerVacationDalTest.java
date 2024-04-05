package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import main.SummerVacationDAL;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class SummerVacationDalTest {
    private static SummerVacationDAL summerVacationDAL;

    @BeforeClass
    public static void setUp() {
        try {
            Properties properties = new Properties();
            // Load the properties file
            properties.load(new FileInputStream("test_config.properties"));

            // Retrieve test database credentials
            String databaseName = properties.getProperty("databaseName");
            String userName = properties.getProperty("username");
            String password = properties.getProperty("password");

            // Initialize the connection using test database credentials
            summerVacationDAL = new SummerVacationDAL(databaseName, userName, password);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file not found or other IO errors
        }
    }

    @AfterClass
    public static void tearDown() {
        // Close the connection after running tests
        SummerVacationDAL.closeConnection();
    }

    @Test
    public void testTryGetDestinationForActivity() {
        // Test for tryGetDestinationForActivity method
        List<String> destinations = summerVacationDAL.tryGetDestinationForActivity("Hiking");
        assertNotNull(destinations);
        assertFalse(destinations.isEmpty());
        assertTrue(destinations.contains("Mountain Park"));
    }

}
