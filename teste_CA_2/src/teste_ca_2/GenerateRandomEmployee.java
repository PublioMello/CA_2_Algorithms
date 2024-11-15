package teste_ca_2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GenerateRandomEmployee {
    private final List<String> applicantList;

    public GenerateRandomEmployee(List<String> applicantList) {
        this.applicantList = applicantList;
    }

    public void generate() {
        Random rand = new Random();
        
        // Separate first and last names for more variety
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Charlie", "Mia", "Ethan", "Grace", "Lily", "Ava", "Logan", "Sophia", "Chloe", "Ryan", "Zoe"};
        String[] lastNames = {"Doe", "Smith", "Johnson", "Brown", "Black", "Carter", "Davis", "Taylor", "Martinez", "Anderson", "Harris", "Thompson", "Roberts", "Hall", "Young"};
        String[] managerTypes = {"Head Manager", "Assistant Manager", "Team Lead", "Supervisor", "Project Coordinator"};
        String[] departments = {"Customer Service", "Technical Support", "HR", "Marketing", "Finance"};

        String randomName;
        boolean isDuplicate;

        // Loop to generate a unique name
        do {
            // Generate a random first and last name combination
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            randomName = firstName + " " + lastName;

            // Check if the generated name already exists in applicantList
            isDuplicate = applicantList.stream().anyMatch(applicant -> applicant.toLowerCase().startsWith(randomName.toLowerCase() + ";"));
            
        } while (isDuplicate); // Repeat if a duplicate is found

        // Randomly select manager type and department
        String randomManagerType = managerTypes[rand.nextInt(managerTypes.length)];
        String randomDepartment = departments[rand.nextInt(departments.length)];
        
        String randomEmployee = randomName + "; " + randomManagerType + "; " + randomDepartment;

        // Add the generated employee to the list
        applicantList.add(randomEmployee);
        System.out.println("Generated and added: " + randomEmployee);

        // Save to text file after alterations
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Applicants_Form.txt", true))) {
            writer.write(randomEmployee);
            writer.newLine(); // Add a new line after each entry
            System.out.println("Employee saved to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the employee: " + e.getMessage());
        }
    }
}
