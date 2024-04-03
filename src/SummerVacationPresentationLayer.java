import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class SummerVacationPresentationLayer {
    private static SummerVacationDAL GetDAL() {
        Scanner credentialScanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        // String input
        String userName = credentialScanner.nextLine();
        System.out.print("Enter password: ");
        Console console = System.console();
        char[] password = console.readPassword();
        return new SummerVacationDAL("Vacation", userName, new String(password));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. Plan your summer vacation");
        System.out.println("2. Input data for some other purpose");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                planSummerVacation();
                break;
            case 2:
                inputDataForOtherPurpose();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void planSummerVacation() {
        SummerVacationDAL dal = GetDAL();
        System.out.println("Welcome to your summer vacation planner! Please enter an activity name to see potential vacation options!");
        Scanner activityScanner = new Scanner(System.in);
        String activityName = activityScanner.nextLine();
        List<String> parks = dal.TryGetDestinationForActivity(activityName);
        for (int i = 0; i < parks.size(); i++) {
            System.out.println(parks.get(i));
        }
    }

    private static void inputDataForOtherPurpose() {
        // Implement logic to input data for some other purpose
        System.out.println("You chose to input data a new park.");
        // Add your logic here
    }
}
