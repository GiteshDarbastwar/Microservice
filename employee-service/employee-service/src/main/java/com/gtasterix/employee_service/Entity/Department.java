package com.gtasterix.employee_service.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String head;
    private int employeeCount;
}
