package example.lecture9.assignment3.service.Impl;

import lombok.AllArgsConstructor;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;

import example.lecture9.assignment3.model.Employee;
import example.lecture9.assignment3.service.EmployeeService;
import example.lecture9.assignment3.repository.EmployeeRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final TemplateEngine templateEngine;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Employee findById(String theId) {
        return employeeRepository.findById(theId).orElseThrow();
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(String theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public void saveCSV(MultipartFile file) {
        SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("M/dd/yyyy");
        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            reader.readNext(); // Skip the header line

            while ((nextLine = reader.readNext()) != null) {
                String id = nextLine[0];
                String name = nextLine[1];
                String dateStr = nextLine[2];
                Date dob = INPUT_FORMAT.parse(dateStr);
                String address = nextLine[3];
                String department = nextLine[4];
                int salary = Integer.parseInt(nextLine[5]);

                Employee employee = new Employee(id, name, dob, address, department, salary);
                employees.add(employee);
            }

            employeeRepository.saveAll(employees);
        } catch (Exception e) {}
    }

    @Override
    public String renderTemplateToString(String templateName, Model model) {
        Context context = new Context();
        model.asMap().forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }

    @Override
    public void generatePdfFromHtml(HttpServletResponse response, String htmlContent) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();

            response.getOutputStream().write(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate PDF from HTML", e);
        }
    }

    @Override
    public Employee highestSalaryEmployee() {
        List<Employee> employee = employeeRepository.findAll();
        return employee.stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }

    @Override
    public Employee lowestSalaryEmployee() {
        List<Employee> employee = employeeRepository.findAll();
        return employee.stream().min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }

    @Override
    public int averageSalary() {
        return (int) employeeRepository.findAll()
        .stream()
        .mapToDouble(Employee::getSalary)
        .average()
        .orElse(0.0);
    }

    @Override
    public long totalRecords() {
        return employeeRepository.count();
    }
}
