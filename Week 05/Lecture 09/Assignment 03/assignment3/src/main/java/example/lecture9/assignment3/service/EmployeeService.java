package example.lecture9.assignment3.service;

import org.springframework.web.multipart.MultipartFile;

import example.lecture9.assignment3.model.Employee;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(String theId);

    void save(Employee theEmployee);

    void deleteById(String theId);

    void saveCSV(MultipartFile file);

    String renderTemplateToString(String templateName, Model model);

    void generatePdfFromHtml(HttpServletResponse response, String htmlContent);

    Employee highestSalaryEmployee();

    Employee lowestSalaryEmployee();
    
    int averageSalary();

    long totalRecords();
}