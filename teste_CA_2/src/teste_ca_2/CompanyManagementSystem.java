package teste_ca_2;

import java.util.*;

public class CompanyManagementSystem {
    private List<String> applicantList = new ArrayList<>(); // List to hold applicant information
    private boolean running = true; // Control flag for the menu loop to continue or finish
    private final EmployeeRemover employeeRemover; // Instance to handle employee removal functionality

    // Constructor to initialize the CompanyManagementSystem
    public CompanyManagementSystem() {
        FileManager fileManager = new FileManager(); // Create an instance of FileManager for file operations
        applicantList = fileManager.loadApplicants(); // Load applicants from a file into the list
        employeeRemover = new EmployeeRemover(applicantList); // Initialize the EmployeeRemover with the applicant list
    }

    // Main method to start the program
    public static void main(String[] args) {
        CompanyManagementSystem system = new CompanyManagementSystem(); // Create an instance of the system
        system.run(); // Start the main loop of the application
    }

    // Method to run the main loop of the application
    public void run() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input
        displayFirst20Records(); // Display the first 20 records from the applicant list

        // Loop to keep the application running until the user chooses to exit
        while (running) {
            displayMenu(); // Display the menu options to the user
            try {
                int choice = scanner.nextInt(); // Get user choice as an integer
                scanner.nextLine(); // Consume newline character after the integer input

                // Retrieve the corresponding menu option based on user choice
                MenuOption option = getMenuOption(choice);
                if (option != null) {
                    option.execute(this, scanner); // Execute the selected menu option
                } else {
                    System.out.println("Invalid choice. Please try again."); // Handle invalid choice
                }
            } catch (InputMismatchException e) {
                // Handle the case where the input is not an integer
                System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                scanner.nextLine(); // Clear the invalid input to prevent an infinite loop
            }
        }

        // Save the applicant list to the file upon exiting the application
        new FileManager().saveApplicants(applicantList); 
        scanner.close(); // Close the scanner to free resources
    }

    // Method to display the first 20 records of applicants
    private void displayFirst20Records() {
        System.out.println("First 20 records:\n");
        for (int i = 0; i < Math.min(20, applicantList.size()); i++) {
            System.out.println(applicantList.get(i)); // Print each applicant's information
        }
    }

    // Method to display the menu options to the user
    private void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1 - Sort Applicants");
        System.out.println("2 - Search Applicants");
        System.out.println("3 - Add Employee");
        System.out.println("4 - Generate Random Employee");
        System.out.println("5 - Show Employees by Manager");
        System.out.println("6 - Show Employees by Department");
        System.out.println("7 - Remove an Employee");
        System.out.println("8 - Exit");
    }

    // Method to map the user's choice to the corresponding MenuOption
    private MenuOption getMenuOption(int choice) {
        // Use switch expression to return the corresponding MenuOption based on user input
        return switch (choice) {
            case 1 -> MenuOption.SORT_APPLICANTS; // Sort applicants
            case 2 -> MenuOption.SEARCH_APPLICANTS; // Search for applicants
            case 3 -> MenuOption.ADD_EMPLOYEE; // Add an employee
            case 4 -> MenuOption.GENERATE_RANDOM_EMPLOYEE; // Generate a random employee
            case 5 -> MenuOption.SHOW_EMPLOYEES_BY_MANAGER; // Show employees under a specific manager
            case 6 -> MenuOption.SHOW_EMPLOYEES_BY_DEPARTMENT; // Show employees in a specific department
            case 7 -> MenuOption.REMOVE_EMPLOYEE; // Remove an employee from the list
            case 8 -> MenuOption.EXIT; // Exit the application
            default -> null; // Return null if the choice is invalid
        };
    }

    // Getters for applicantList and employeeRemover to be used in MenuOption enum
    public List<String> getApplicantList() {
        return applicantList; // Return the applicant list
    }

    public EmployeeRemover getEmployeeRemover() {
        return employeeRemover; // Return the employee remover instance
    }

    // Method to stop the main loop when the user chooses to exit
    public void stopRunning() {
        running = false; // Set running flag to false to exit the loop
    }
}
