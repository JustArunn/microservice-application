package com.arunn.companyservice.VO;

import com.arunn.companyservice.entity.Company;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long companyId;
    private String companyName;
    private String companyDescription;

    private Long departmentId;
    private String departmentName;
    private String departmentCode;

    private Long userId;
    private String fullName;
    private String email;

    public CompanyDTO(Company company, Department department, User user){
        this.companyId = company.getCompanyId();
        this.companyName = company.getCompanyName();
        this.companyDescription = company.getCompanyDescription();

        this.departmentId = department.getDepartmentId();
        this.departmentName = department.getDepartmentName();
        this.departmentCode = department.getDepartmentCode();

        this.userId = user.getUserId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
    }
}
