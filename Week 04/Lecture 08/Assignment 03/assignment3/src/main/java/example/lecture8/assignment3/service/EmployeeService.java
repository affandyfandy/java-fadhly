package example.lecture8.assignment3.service;

import example.lecture8.assignment3.model.Employee;
import example.lecture8.assignment3.repository.EmployeeRepository1;
import example.lecture8.assignment3.repository.EmployeeRepository2;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository1 employeeRepository1;
    private final EmployeeRepository2 employeeRepository2;

    public EmployeeService(EmployeeRepository1 employeeRepository1, EmployeeRepository2 employeeRepository2) {
        this.employeeRepository1 = employeeRepository1;
        this.employeeRepository2 = employeeRepository2;
    }

    @Transactional("transactionManager1")
    public List<Employee> findAll1() {
        return employeeRepository1.findAll();
    }

    @Transactional("transactionManager2")
    public List<Employee> findAll2() {
        return employeeRepository2.findAll();
    }

    @Transactional("transactionManager1")
    public Employee findEmployeeById1(int id) {
        return employeeRepository1.findEmployeeById(id);
    }

    @Transactional("transactionManager2")
    public Employee findEmployeeById2(int id) {
        return employeeRepository2.findEmployeeById(id);
    }

    @Transactional("transactionManager1")
    public String createEmployee1(Employee emp) {
        int status = employeeRepository1.createEmployee(emp);

        if(status == 0) {
            return "Failed to Create Employee";
        }

        return "Employee Data:\nName: " + emp.getName() + "\nDepartment: " + emp.getDepartment() + "\nAge: " + emp.getAge() +"\nEmployee been Created" ;
    }

    @Transactional("transactionManager2")
    public String createEmployee2(Employee emp) {
        int status = employeeRepository2.createEmployee(emp);

        if(status == 0) {
            return "Failed to Create Employee";
        }

        return "Employee Data:\nName: " + emp.getName() + "\nDepartment: " + emp.getDepartment() + "\nAge: " + emp.getAge() +"\nEmployee been Created" ;
    }

    @Transactional("transactionManager1")
    public String updateEmployee1(Employee emp, int id) {
        Employee emp1 = employeeRepository1.findEmployeeById(id);
        int status = employeeRepository1.updateEmployee(emp, id);

        if(status == 0) {
            return "Failed to Edit Employee";
        }

        return "Before Updated:\nName: " + emp1.getName() + "\nDepartment: " + emp1.getDepartment() + "\nAge: " + emp1.getAge() + "\nAfter Updated:\nName: " + emp.getName() + "\nDepartment: " + emp.getDepartment() + "\nAge: " + emp.getAge() + "\nEmployee been Edited";
    }

    @Transactional("transactionManager2")
    public String updateEmployee2(Employee emp, int id) {
        Employee emp1 = employeeRepository2.findEmployeeById(id);
        int status = employeeRepository2.updateEmployee(emp, id);

        if(status == 0) {
            return "Failed to Edit Employee";
        }

        return "Before Updated:\nName: " + emp1.getName() + "\nDepartment: " + emp1.getDepartment() + "\nAge: " + emp1.getAge() + "\nAfter Updated:\nName: " + emp.getName() + "\nDepartment: " + emp.getDepartment() + "\nAge: " + emp.getAge() + "\nEmployee been Edited";
    }

    @Transactional("transactionManager1")
    public String deleteEmployee1(int id) {
        Employee emp = employeeRepository1.findEmployeeById(id);
        int status = employeeRepository1.deleteEmployee(id);

        if(status == 0) {
            return "Failed to Delete Employee";
        }

        return "Employee Data:\nName: " + emp.getName() + "\nDepartment: " + emp.getDepartment() + "\nAge: " + emp.getAge() + "\nEmployee been Deleted";
    }

    @Transactional("transactionManager2")
    public String deleteEmployee2(int id) {
        Employee emp = employeeRepository2.findEmployeeById(id);
        int status = employeeRepository2.deleteEmployee(id);

        if(status == 0) {
            return "Failed to Delete Employee";
        }

        return "Employee Data:\nName: " + emp.getName() + "\nDepartment: " + emp.getDepartment() + "\nAge: " + emp.getAge() + "\nEmployee been Deleted";
    }
}
