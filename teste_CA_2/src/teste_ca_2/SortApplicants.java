package teste_ca_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortApplicants {
    private final List<String> applicantList;

    public SortApplicants(List<String> applicantList) {
        this.applicantList = applicantList;
    }

    public void sortAndDisplay() {
        // Create a copy of the applicant list to sort
        List<String> sortedList = new ArrayList<>(applicantList);
        Collections.sort(sortedList); // Sort the copied list

        System.out.println("Applicants sorted alphabetically. Showing first 20 records:\n");
        for (int i = 0; i < Math.min(20, sortedList.size()); i++) {
            System.out.println(sortedList.get(i)); // Display from the sorted list
        }
        System.out.println();
    }
}
