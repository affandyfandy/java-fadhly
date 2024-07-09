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
    public String findEmployeeById1(int id) {
        Employee emp = employeeRepository1.findEmployeeById(id);
        if(emp == null) {
            return "Employee with Id " + id + " doesn't exist";
        }

        return emp.show();
    }

    @Transactional("transactionManager2")
    public String findEmployeeById2(int id) {
        Employee emp = employeeRepository2.findEmployeeById(id);
        if(emp == null) {
            return "Employee with Id " + id + " doesn't exist";
        }

        return emp.show();
    }

    @Transactional("transactionManager1")
    public String createEmployee1(Employee emp) {
        int status = employeeRepository1.createEmployee(emp);

        if(status == 0) {
            return "Failed to Create Employee";
        }

        return "Employee Success Created";
    }

    @Transactional("transactionManager2")
    public String createEmployee2(Employee emp) {
        int status = employeeRepository2.createEmployee(emp);

        if(status == 0) {
            return "Failed to Create Employee";
        }

        return "Employee Success Created";
    }

    @Transactional("transactionManager1")
    public String updateEmployee1(Employee emp, int id) {
        int status = employeeRepository1.updateEmployee(emp, id);

        if(status == 0) {
            return "Failed to Edit Employee";
        } else if(status == -1) {
            return "Employee with id " + id + " doesn't exist";
        } else if(status == -2) {
            return "Body Column doesn't exist";
        }

        Employee emp1 = employeeRepository1.findEmployeeById(id);
        return "After Updated:\n" + emp1.show() + "\nEmployee been Edited";
    }

    @Transactional("transactionManager2")
    public String updateEmployee2(Employee emp, int id) {
        int status = employeeRepository2.updateEmployee(emp, id);

        if(status == 0) {
            return "Failed to Edit Employee";
        } else if(status == -1) {
            return "Employee with id " + id + " doesn't exist";
        } else if(status == -2) {
            return "Body Column doesn't exist";
        }

        Employee emp1 = employeeRepository2.findEmployeeById(id);
        return "After Updated:\n" + emp1.show() + "\nEmployee been Edited";
    }

    @Transactional("transactionManager1")
    public String deleteEmployee1(int id) {
        int status = employeeRepository1.deleteEmployee(id);

        if(status == 0) {
            return "Failed to Delete Employee";
        } else if(status == -1) {
            return "Employee with id " + id + " doesn't exist";
        }

        return "Employee been Deleted";
    }

    @Transactional("transactionManager2")
    public String deleteEmployee2(int id) {
        int status = employeeRepository2.deleteEmployee(id);

        if(status == 0) {
            return "Failed to Delete Employee";
        } else if(status == -1) {
            return "Employee with id " + id + " doesn't exist";
        }

        return "Employee been Deleted";
    }

    @Transactional("transactionManager1")
    public void createEmployeeTestTransaction(Employee emp1, Employee emp2) {
        try {
            employeeRepository1.createEmployee(emp1);

            employeeRepository1.createEmployee2(emp2);
        } catch(Exception e) {
            throw e;
        }
    }
}