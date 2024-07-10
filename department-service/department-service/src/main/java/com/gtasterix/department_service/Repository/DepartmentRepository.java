package com.gtasterix.department_service.Repository;

import com.gtasterix.department_service.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
