package com.gtasterix.employee_service.Serviceses;

import com.gtasterix.employee_service.Entity.Department;
import com.gtasterix.employee_service.Entity.Employee;
import com.gtasterix.employee_service.Exception.DataNotFoundException;
import com.gtasterix.employee_service.Exception.IDNotFoundException;
import com.gtasterix.employee_service.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String DEPARTMENT_SERVICE_URL = "http://DEPARTMENT-SERVICE/departments/";

    public List<Employee> getAllEmployees() throws DataNotFoundException {
        List<Employee> allEmp = employeeRepository.findAll();
        if (allEmp.isEmpty()) {
            throw new DataNotFoundException("Data Not Found");
        }
        return allEmp;
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IDNotFoundException("Employee not found with ID: " + id));

        // Fetch department details using RestTemplate based on departmentId
        Department department = restTemplate.getForObject(DEPARTMENT_SERVICE_URL + employee.getDepartmentId(), Department.class);

        // Set the department in the employee object
        employee.setDepartment(department);

        return employee;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setFirstName(updatedEmployee.getFirstName());
                    existingEmployee.setLastName(updatedEmployee.getLastName());
                    existingEmployee.setEmail(updatedEmployee.getEmail());
                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new IDNotFoundException("ID Not Found"));
    }

    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new IDNotFoundException("ID Not Found");
        }
    }
}
