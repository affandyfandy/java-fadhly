import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Lab6_5 {
    public List<Lab6_5Emp> sortByName(List<Lab6_5Emp> employees) {
        return employees.stream()
                        .sorted(Comparator.comparing(Lab6_5Emp::getName))
                        .collect(Collectors.toList());
    }

    public Optional<Lab6_5Emp> findEmployeeWithMaxSalary(List<Lab6_5Emp> employees) {
        return employees.stream()
                        .max(Comparator.comparingDouble(Lab6_5Emp::getSalary));
    }

    public boolean checkIfAnyEmployeeNameMatches(List<Lab6_5Emp> employees, List<String> keywords) {
        return employees.stream()
                        .anyMatch(employee -> 
                            keywords.stream().anyMatch(keyword -> 
                                employee.getName().equalsIgnoreCase(keyword)
                            )
                        );
    }
}
