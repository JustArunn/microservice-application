package com.arunn.companyservice.service;

import com.arunn.companyservice.VO.CompanyDetailsVO;
import com.arunn.companyservice.VO.Department;
import com.arunn.companyservice.VO.User;
import com.arunn.companyservice.entity.Company;
import com.arunn.companyservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final RestTemplate restTemplate;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public CompanyDetailsVO getCompanyDetails(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();

        List<Department> department = restTemplate.
                exchange("http://DEPARTMENT-SERVICE/departments/companies/" + companyId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Department>>() {}).getBody();

        List<User> users  = restTemplate
                .exchange("http://USER-SERVICE/users/companies/" + companyId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<User>>() {
                        }
                ).getBody();

        CompanyDetailsVO vo = new CompanyDetailsVO();
        vo.setCompany(company);
        vo.setUsers(users);
        vo.setDepartments(department);

        return vo;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
