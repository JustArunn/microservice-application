package com.arunn.departmentservice.service;

import com.arunn.departmentservice.entity.Department;
import com.arunn.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow();
    }

    public List<Department> getDepartmentsByCompanyId(Long companyId) {
        return departmentRepository.findByCompanyId(companyId);
    }
}
