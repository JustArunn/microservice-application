package com.arunn.companyservice.VO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long departmentId;
    private String departmentName;
    private String departmentCode;
    private Long companyId;
}
