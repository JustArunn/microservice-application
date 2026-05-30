package com.arunn.companyservice.VO;

import com.arunn.companyservice.entity.Company;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetailsVO {
    Company company;
    List<Department>departments;
    List<User>users;
}
