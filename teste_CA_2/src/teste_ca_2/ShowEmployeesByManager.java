package teste_ca_2; // Package declaration for organization and access control

import java.util.InputMismatchException; // Importing exception class for handling input errors
import java.util.List; // Importing List interface for storing applicant data
import java.util.Scanner; // Importing Scanner class for user input

public class ShowEmployeesByManager {
    private final List<String> applicantList; // List to hold the applicant information

    // Constructor to initialize the applicant list
    public ShowEmployeesByManager(List<String> applicantList) {
        this.applicantList = applicantList; // Assigning the passed list to the instance variable
    }

    // Method to display employees based on selected manager role
    public void display(Scanner scanner) {
        // Display available manager roles for selection
        System.out.println("\nPlease select the Manager role:");
        System.out.println("1 - Head Manager");
        System.out.println("2 - Assistant Manager");
        System.out.println("3 - Team Lead");
        System.out.println("4 - Supervisor");
        System.out.println("5 - Project Coordinator");

        // Get the user's role choice
        int roleChoice = getRoleChoice(scanner);
        
        // Retrieve the role name based on user's choice
        String role = getRoleByChoice(roleChoice);
        if (role != null) { // If a valid role was selected
            System.out.println("Employees with the role " + role + ": \n");
            boolean found = false; // Flag to check if any employee was found
            for (String applicant : applicantList) {
                // Check if the applicant's information contains the selected role
                if (applicant.contains(role)) { // Assuming the format includes the role name
                    System.out.println(applicant); // Print the applicant's information
                    found = true; // Set found flag to true if an employee matches
                }
            }
            // If no employees were found, notify the user
            if (!found) {
                System.out.println("No employees found with the role " + role + ".");
            }
        } else {
            // Notify the user of an invalid selection
            System.out.println("Invalid role selection.");
        }
        System.out.println(); // Print a new line for better readability
    }

    // Method to get a valid role choice from the user
    private int getRoleChoice(Scanner scanner) {
        int choice = -1; // Initialize choice to an invalid state
        // Loop until a valid choice is made
        while (choice < 1 || choice > 5) {
            System.out.print("Enter your choice (1-5): "); // Prompt for input
            try {
                choice = scanner.nextInt(); // Read user input
                scanner.nextLine(); // Consume the newline character
                if (choice < 1 || choice > 5) {
                    System.out.println("Error: Please select a number between 1 and 5."); // Error message for out-of-range input
                }
            } catch (InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return choice; // Return the valid choice
    }

    // Method to get the role name based on the user's choice
    private String getRoleByChoice(int choice) {
        return switch (choice) { // Switch expression to return role names
            case 1 -> "Head Manager";
            case 2 -> "Assistant Manager";
            case 3 -> "Team Lead";
            case 4 -> "Supervisor";
            case 5 -> "Project Coordinator";
            default -> null; // Return null for invalid choice (shouldn't reach here)
        };
    }
}
