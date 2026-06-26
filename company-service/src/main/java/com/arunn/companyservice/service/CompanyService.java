package com.arunn.companyservice.service;

import com.arunn.companyservice.VO.CompanyDTO;
import com.arunn.companyservice.VO.CompanyDetailsVO;
import com.arunn.companyservice.VO.Department;
import com.arunn.companyservice.VO.User;
import com.arunn.companyservice.entity.Company;
import com.arunn.companyservice.exception.ResourceAlreadyExistsException;
import com.arunn.companyservice.exception.ResourceNotFoundException;
import com.arunn.companyservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
        if(companyRepository.existsByCompanyName(company.getCompanyName())){
            throw new ResourceAlreadyExistsException("A Company already exists with this name : "
                    + company.getCompanyName());
        }
        return companyRepository.save(company);
    }

    public CompanyDTO saveFullCompany(CompanyDTO companyDTO) {

        Company company = companyRepository.save(Company.builder()
                        .companyName(companyDTO.getCompanyName())
                        .companyDescription(companyDTO.getCompanyDescription())
                .build());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Department> requestBody = new HttpEntity<>(
                Department.builder()
                        .companyId(company.getCompanyId())
                        .departmentName(companyDTO.getDepartmentName())
                        .departmentCode(companyDTO.getDepartmentCode())
                .build(),
                headers);

        Department department = restTemplate.exchange(
                "http://DEPARTMENT-SERVICE/departments",
                HttpMethod.POST,
                requestBody,
                Department.class)
                .getBody();

        if(department == null){
            throw new RuntimeException("Failed to save department");
        }

        HttpEntity<User> userRequestBody = new HttpEntity<>(User.builder()
                .companyId(company.getCompanyId())
                .departmentId(department.getDepartmentId())
                .fullName(companyDTO.getFullName())
                .email(companyDTO.getEmail())
                .build(), headers);

        User user = restTemplate.exchange(
                    "http://USER-SERVICE/users",
                    HttpMethod.POST,
                    userRequestBody,
                    User.class
        ).getBody();

        if(user == null){
            throw new RuntimeException("Failed to save user");
        }

        return new CompanyDTO(company, department, user);
    }

    public CompanyDetailsVO getCompanyDetails(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(()->new ResourceNotFoundException("Company not found with ID : " + companyId));

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
