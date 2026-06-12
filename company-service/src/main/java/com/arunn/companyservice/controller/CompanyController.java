package com.arunn.companyservice.controller;

import com.arunn.companyservice.VO.CompanyDetailsVO;
import com.arunn.companyservice.entity.Company;
import com.arunn.companyservice.saga.CompanyCreationSagaRequest;
import com.arunn.companyservice.saga.SagaOrchestrator;
import com.arunn.companyservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final SagaOrchestrator sagaOrchestrator;

    /**
     * Create company with saga pattern for coordinated creation across services
     */
    @PostMapping("/saga")
    public Company createCompanyWithSaga(@RequestBody CompanyCreationSagaRequest request) {
        sagaOrchestrator.startCompanyCreationSaga(request);
        return Company.builder()
                .companyName(request.getCompanyName())
                .companyDescription(request.getCompanyDescription())
                .build();
    }

    /**
     * Create company directly (existing endpoint)
     */
    @PostMapping
    public Company saveCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{companyId}")
    public CompanyDetailsVO getCompanyDetails(@PathVariable("companyId") Long companyId) {
        return companyService.getCompanyDetails(companyId);
    }
}
