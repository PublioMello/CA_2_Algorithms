package teste_ca_2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AddEmployee {
    private final List<String> applicantList;

    public AddEmployee(List<String> applicantList) {
        this.applicantList = applicantList;
    }

    public void add(Scanner scanner) {
        System.out.println("Please input the Applicant Name and Surname:");
        String name = scanner.nextLine().trim(); // Get the employee's name and remove any leading/trailing spaces
        name = capitalizeName(name); // Format the name to capitalize only the first letter of each word

        // Check if the employee already exists
        for (String applicant : applicantList) {
            String[] parts = applicant.split(";"); // Assuming the format "Name; ManagerType; Department"
            String applicantName = parts[0].trim(); // Get the name (first part)

            if (applicantName.equalsIgnoreCase(name)) { // Check if the name matches
                System.out.println("Error: An applicant with the name \"" + name + "\" already exists.\n"); // Print error
                return; // Exit the method to prevent duplicate entry
            }
        }

        // Management staff selection with input validation
        String managerType = "";
        while (true) {
            System.out.println("\nPlease select from the following Management Staff:");
            System.out.println("Head Manager (1)");
            System.out.println("Assistant Manager (2)");
            System.out.println("Team Lead (3)");
            System.out.println("Supervisor (4)");
            System.out.println("Project Coordinator (5)");

            try {
                int managerChoice = scanner.nextInt(); // Get the manager choice
                scanner.nextLine(); // Consume the newline

                switch (managerChoice) {
                    case 1 -> managerType = "Head Manager";
                    case 2 -> managerType = "Assistant Manager";
                    case 3 -> managerType = "Team Lead";
                    case 4 -> managerType = "Supervisor";
                    case 5 -> managerType = "Project Coordinator";
                    default -> {
                        System.out.println("Invalid choice for Management Staff. Please enter a number between 1 and 5.");
                        continue; // Repeat the loop for an invalid choice
                    }
                }
                break; // Exit the loop on a valid choice
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Department selection with input validation
        String department = "";
        while (true) {
            System.out.println("\nPlease select the Department:");
            System.out.println("Customer Service (1)");
            System.out.println("Technical Support (2)");
            System.out.println("HR (3)");
            System.out.println("Marketing (4)");
            System.out.println("Finance (5)");

            try {
                int departmentChoice = scanner.nextInt(); // Get the department choice
                scanner.nextLine(); // Consume the newline

                switch (departmentChoice) {
                    case 1 -> department = "Customer Service";
                    case 2 -> department = "Technical Support";
                    case 3 -> department = "HR";
                    case 4 -> department = "Marketing";
                    case 5 -> department = "Finance";
                    default -> {
                        System.out.println("Invalid choice for Department. Please enter a number between 1 and 5.");
                        continue; // Repeat the loop for an invalid choice
                    }
                }
                break; // Exit the loop on a valid choice
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Create a new entry for the employee and add it to the applicant list
        String newEntry = name + "; " + managerType + "; " + department; // Use semicolon to match your format
        applicantList.add(newEntry);
        System.out.println(newEntry + " has been added successfully! \n"); // Confirm addition

        // Save to text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Applicants_Form.txt", true))) {
            writer.write(newEntry);
            writer.newLine(); // Add a new line after each entry
        } catch (IOException e) {
            System.out.println("An error occurred while saving the applicant: " + e.getMessage());
        }
    }

    // Helper method to capitalize the first letter of each part of the name
    private String capitalizeName(String name) {
        String[] parts = name.split("\\s+"); // Split by whitespace
        StringBuilder capitalized = new StringBuilder();

        for (String part : parts) {
            if (part.length() > 1) {
                capitalized.append(part.substring(0, 1).toUpperCase())
                           .append(part.substring(1).toLowerCase())
                           .append(" ");
            } else {
                capitalized.append(part.toUpperCase()).append(" ");
            }
        }

        return capitalized.toString().trim(); // Trim to remove any trailing spaces
    }
}
