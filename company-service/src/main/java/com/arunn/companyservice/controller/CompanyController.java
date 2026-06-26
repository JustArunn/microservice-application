package com.arunn.companyservice.controller;

import com.arunn.companyservice.VO.CompanyDTO;
import com.arunn.companyservice.VO.CompanyDetailsVO;
import com.arunn.companyservice.entity.Company;
import com.arunn.companyservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company saveCompany(@RequestBody Company company){
        return companyService.saveCompany(company);
    }

    @PostMapping("/new")
    public CompanyDTO saveFullCompany(@RequestBody CompanyDTO company){
        return companyService.saveFullCompany(company);
    }

    @GetMapping
    public List<Company>getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{companyId}")
    public CompanyDetailsVO getCompanyDetails(@PathVariable("companyId")Long companyId){
        return companyService.getCompanyDetails(companyId);
    }

}
