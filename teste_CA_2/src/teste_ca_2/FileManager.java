package teste_ca_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private static final String DEFAULT_FILE_NAME = "Applicants_Form.txt";
    private List<String> applicantList;

    public FileManager() {
        applicantList = new ArrayList<>();
    }

    public List<String> loadApplicants() {
        Scanner scanner = new Scanner(System.in);
        boolean validFile = false; // Flag to check if a valid file has been read

        // Loop to prompt the user until a valid file is loaded
        while (!validFile) {
            System.out.print("Please enter the name of the archive file in the format .txt: ");
            String filename = scanner.nextLine(); // Read the file name from user input

            // Attempt to read the file
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Assuming the format is "Name; ManagerType; Department"
                    applicantList.add(line.trim()); // Add each line to the applicant list
                }
                validFile = true; // If the file was read successfully
                System.out.println("Applicants loaded successfully from " + filename + "\n"); // Confirmation message
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
                System.out.println("Please try again."); // Prompt user to try again
            }
        }
        
        return applicantList;
    }
//save the file after aterations
    public void saveApplicants(List<String> applicants) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEFAULT_FILE_NAME, false))) {
            for (String applicant : applicants) {
                writer.write(applicant);
                writer.newLine();
            }
            System.out.println("Applicants saved successfully to " + DEFAULT_FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving applicants: " + e.getMessage());
        }
    }
}
