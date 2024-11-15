package teste_ca_2;

import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for searching for applicants by name in the applicant list.
 */
public class SearchApplicants {
    
    // List of applicants to search through
    private final List<String> applicantList;

    /**
     * Constructor to initialize SearchApplicants with a given list of applicants.
     * @param applicantList The list of applicants to be searched.
     */
    public SearchApplicants(List<String> applicantList) {
        this.applicantList = applicantList;
    }

 
    public void search(Scanner scanner) {
        // Prompt user to enter the name they want to search
        System.out.println("Enter name to search:");
        String name = scanner.nextLine();  // Read name input
        boolean found = false;  // Flag to check if applicant is found

        // Iterate over each applicant in the list
        for (String applicant : applicantList) {
            String[] parts = applicant.split(";");  // Split applicant data by ';'
            if (parts.length < 3) continue;  // Skip entries with insufficient data

            // Check if the first part (name) matches the input name (case-insensitive)
            if (parts[0].trim().equalsIgnoreCase(name)) {
                // If found, display applicant details and set found flag to true
                System.out.println("Found: " + parts[0] + " - Manager Type: " + parts[1] + ", Department: " + parts[2]);
                found = true;
                break;  // Exit loop once the applicant is found
            }
        }

        // If no applicant is found, print a message
        if (!found) System.out.println("No applicant found with the name: " + name);
    }
}
