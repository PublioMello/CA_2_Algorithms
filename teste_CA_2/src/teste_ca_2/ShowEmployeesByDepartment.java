package teste_ca_2;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ShowEmployeesByDepartment {

    // List of applicants to filter by department
    private final List<String> applicantList;

    /**
     * Constructor to initialize ShowEmployeesByDepartment with a list of applicants.
     * @param applicantList List of applicants represented as strings.
     */
    public ShowEmployeesByDepartment(List<String> applicantList) {
        this.applicantList = applicantList;
    }

  
    public void display(Scanner scanner) {
        // Display department choices for the user
        System.out.println("Please select the Department:");
        System.out.println("1 - Customer Service");
        System.out.println("2 - Technical Support");
        System.out.println("3 - HR");
        System.out.println("4 - Marketing");
        System.out.println("5 - Finance");

        // Get the user's department choice
        int departmentChoice = getDepartmentChoice(scanner);

        // Convert the choice to a department name
        String department = getDepartmentByChoice(departmentChoice);
        if (department != null) {
            // Display employees belonging to the selected department
            System.out.println("Employees in the " + department + " department: \n");
            boolean found = false;

            // Iterate through the applicant list to find matching department entries
            for (String applicant : applicantList) {
                if (applicant.contains(department)) { // Assuming each applicant's data includes the department name
                    System.out.println(applicant);
                    found = true;
                }
            }

            // If no employees found in the selected department
            if (!found) {
                System.out.println("No employees found in the " + department + " department.");
            }
        } else {
            // Display an error message for an invalid department selection
            System.out.println("Invalid department selection.");
        }
        System.out.println(); // Print a new line for readability
    }

 
    private int getDepartmentChoice(Scanner scanner) {
        int choice = -1;
        while (choice < 1 || choice > 5) {
            System.out.print("Enter your choice (1-5): ");
            try {
                choice = scanner.nextInt(); // Read integer input
                scanner.nextLine(); // Consume newline left over from nextInt()
                if (choice < 1 || choice > 5) {
                    // Display an error message for out-of-range input
                    System.out.println("Error: Please select a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                // Handle invalid input that is not an integer
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear invalid input from scanner buffer
            }
        }
        return choice;
    }

   
    private String getDepartmentByChoice(int choice) {
        // Use a switch expression to map the choice to a department name
        return switch (choice) {
            case 1 -> "Customer Service";
            case 2 -> "Technical Support";
            case 3 -> "HR";
            case 4 -> "Marketing";
            case 5 -> "Finance";
            default -> null; // Return null for invalid choices
        };
    }
}
