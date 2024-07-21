package example.lecture12.assignment1.specification;

import example.lecture12.assignment1.entity.Employee;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class EmployeeSpecification {
    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(root.get("firstName"), firstName);
    }

    public static Specification<Employee> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(root.get("lastName"), lastName);
    }

    public static Specification<Employee> hasGender(String gender) {
        return (root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<Employee> hasHireDate(Date hireDate) {
        return (root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(root.get("hireDate"), hireDate);
    }
}
