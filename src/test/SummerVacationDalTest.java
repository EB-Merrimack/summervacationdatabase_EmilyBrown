package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import main.SummerVacationDAL;

import static org.junit.Assert.*;
import java.util.List;

public class SummerVacationDalTest {
    private static SummerVacationDAL summerVacationDAL;

    @BeforeClass
    public static void setUp() {
        // Initialize the connection before running tests
        summerVacationDAL = new main.SummerVacationDAL("your_database_name", "your_username", "your_password");
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

    @Test
    public void testTryInsertingData() {
        // Test for tryInsertingData method
        assertTrue(summerVacationDAL.tryInsertingData("your_database_name", "your_username", "your_password"));
    }
}
