package com.arunn.departmentservice.controller;

import com.arunn.departmentservice.entity.Department;
import com.arunn.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable("departmentId")Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/companies/{companyId}")
    public List<Department> getDepartmentsByCompanyId(@PathVariable("companyId")Long companyId){
        return departmentService.getDepartmentsByCompanyId(companyId);
    }
}
