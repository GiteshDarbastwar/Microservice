package com.gtasterix.employee_service.Repository;

import com.gtasterix.employee_service.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
}
