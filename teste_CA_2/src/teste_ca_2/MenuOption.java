package teste_ca_2;

import java.util.List;
import java.util.Scanner;

/**
 * Enum representing different menu options for the CompanyManagementSystem.
 * Each menu option has a specific action (method) that interacts with the system.
 */
public enum MenuOption {
    
    // Option to sort applicants
    SORT_APPLICANTS {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Creates a new SortApplicants instance with the applicant list and sorts & displays the applicants
            new SortApplicants(system.getApplicantList()).sortAndDisplay();
        }
    },
    
    // Option to search applicants
    SEARCH_APPLICANTS {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Creates a new SearchApplicants instance and performs a search using user input from the scanner
            new SearchApplicants(system.getApplicantList()).search(scanner);
        }
    },
    
    // Option to add an employee from the applicant list
    ADD_EMPLOYEE {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Creates a new AddEmployee instance and adds an employee using input from the scanner
            new AddEmployee(system.getApplicantList()).add(scanner);
        }
    },
    
    // Option to generate a random employee and add to the applicant list
    GENERATE_RANDOM_EMPLOYEE {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Creates a new GenerateRandomEmployee instance and generates a random employee
            new GenerateRandomEmployee(system.getApplicantList()).generate();
        }
    },
    
    // Option to display employees by their manager
    SHOW_EMPLOYEES_BY_MANAGER {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Creates a new ShowEmployeesByManager instance and displays employees grouped by manager
            new ShowEmployeesByManager(system.getApplicantList()).display(scanner);
        }
    },
    
    // Option to display employees by department
    SHOW_EMPLOYEES_BY_DEPARTMENT {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Creates a new ShowEmployeesByDepartment instance and displays employees grouped by department
            new ShowEmployeesByDepartment(system.getApplicantList()).display(scanner);
        }
    },
    
    // Option to remove an employee
    REMOVE_EMPLOYEE {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Calls the removeEmployee method on the system's EmployeeRemover instance to remove an employee
            system.getEmployeeRemover().removeEmployee(scanner);
        }
    },
    
    // Option to exit the application
    EXIT {
        @Override
        public void execute(CompanyManagementSystem system, Scanner scanner) {
            // Stops the system's running status to exit the application
            system.stopRunning();
        }
    };

    /**
     * Abstract method to execute a specific action for each menu option.
     * @param system The CompanyManagementSystem instance to interact with
     * @param scanner The Scanner instance to handle user input
     */
    public abstract void execute(CompanyManagementSystem system, Scanner scanner);
}
