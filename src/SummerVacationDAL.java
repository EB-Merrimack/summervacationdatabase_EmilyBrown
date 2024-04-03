import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SummerVacationDAL {
    private static Connection connection; // Cache the connection

    // Notice that the databaseName, user and password are passed into this method. We are in the DAL,
    // and we cannot prompt the user for this information. That should be done in the presentation layer
    private static void initializeConnection(String databaseName, String user, String password) {
        try {
            if (connection == null || connection.isClosed()) { // Check if connection is null or closed
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
            }
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database" + exception.getMessage());
        }
    }

    public SummerVacationDAL(String databaseName, String userName, String password) {
        initializeConnection(databaseName, userName, password);
    }

    public List<String> tryGetDestinationForActivity(String activityName) {
        List<String> parks = new ArrayList<String>();
        try {
            PreparedStatement myStatement = connection.prepareStatement("Select * From Plan Where ActivityName = ?");
            myStatement.setString(1, activityName);
            ResultSet myRelation = myStatement.executeQuery();
            while (myRelation.next()) {
                parks.add(myRelation.getString("ParkName"));
            }
            return parks;
        } catch (SQLException ex) {
            System.out.println("Failed to get activity destinations" + ex.getMessage());
            return parks;
        }
    }

    public boolean tryInsertingData(String databaseName, String username, String password) {
        // SQL query to call the InsertNewRecipe stored procedure
        String query = "CALL InsertActivityAndPark(?, ?, ?, ?, ?,?)";

        try {
            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for recipe details
            System.out.print("Enter the Activity name: ");
            String activity_name = scanner.nextLine();

            System.out.print("Enter the max number of players: ");
            int num_players = scanner.nextInt();

            System.out.print("Enter the park Name: ");
            String park_name = scanner.nextLine();

            System.out.print("Enter the address of the park: ");
            String park_address = scanner.nextLine();

            System.out.print("Enter the state: ");
            String park_state = scanner.nextLine();

            System.out.print("Enter the zipcode: ");
            int park_zipcode = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Set values for the stored procedure parameters
            preparedStatement.setString(1, activity_name);
            preparedStatement.setInt(2, num_players);
            preparedStatement.setString(3, park_name);
            preparedStatement.setString(4, park_address);
            preparedStatement.setString(5, park_state);
            preparedStatement.setInt(6, park_zipcode);

            // Execute the query
            preparedStatement.execute();

            // Close resources
            preparedStatement.close();

            System.out.println("Park & Activity inserted successfully.");

            return true;
        } catch (SQLException e) {
            System.out.println("Failed to insert data: " + e.getMessage());
            return false;
        }
    }

    // Add a method to close the connection if needed
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failed to close the connection" + ex.getMessage());
        }
    }
}
