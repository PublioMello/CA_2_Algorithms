package teste_ca_2;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class EmployeeRemover { // Capitalized class name

    private List<String> applicantList; // Reference to the main applicant list

    // Constructor to initialize the list reference
    public EmployeeRemover(List<String> applicantList) {
        this.applicantList = applicantList;
    }

    // Method to remove an employee from the applicant list
    public void removeEmployee(Scanner scanner) {
        System.out.println("Enter the name of the employee to remove:");
        String name = scanner.nextLine().toLowerCase(); // Convert the input name to lowercase
        boolean found = false; // Flag to check if the employee is found

        // Loop through the list to find and remove the employee
        for (int i = 0; i < applicantList.size(); i++) {
            String applicant = applicantList.get(i).toLowerCase(); // Convert each applicant to lowercase for comparison
            if (applicant.startsWith(name + ";")) { // Match name format (case-insensitive)
                applicantList.remove(i); // Remove the employee from the list
                System.out.println("Employee \"" + name + "\" has been removed successfully.\n");
                found = true;
                updateFile(); // Update the file to reflect the removal
                break;
            }
        }

        if (!found) {
            System.out.println("No employee found with the name \"" + name + "\".\n"); // If not found
        }
    }

    // Method to update the file after an employee is removed
    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Applicants_Form.txt"))) {
            for (String applicant : applicantList) {
                writer.write(applicant);
                writer.newLine(); // Add a new line after each entry
            }
            System.out.println("File updated successfully.\n");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file: " + e.getMessage());
        }
    }
}
