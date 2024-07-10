package com.gtasterix.department_service.Services;

import com.gtasterix.department_service.Entity.Department;
import com.gtasterix.department_service.Exception.DataNotFoundException;
import com.gtasterix.department_service.Exception.IDNotFoundException;
import com.gtasterix.department_service.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() throws DataNotFoundException {
        List<Department> allDepartments = departmentRepository.findAll();
        if (allDepartments.isEmpty()) {
            throw new DataNotFoundException("No departments found");
        }
        return allDepartments;
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IDNotFoundException("Department not found with ID: " + id));
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    existingDepartment.setName(updatedDepartment.getName());
                    existingDepartment.setDescription(updatedDepartment.getDescription());
                    existingDepartment.setLocation(updatedDepartment.getLocation());
                    existingDepartment.setHead(updatedDepartment.getHead());
                    return departmentRepository.save(existingDepartment);
                })
                .orElseThrow(() -> new IDNotFoundException("Department not found with ID: " + id));
    }

    public void deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new IDNotFoundException("Department not found with ID: " + id);
        }
    }
}
