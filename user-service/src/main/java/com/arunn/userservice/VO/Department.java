package com.arunn.userservice.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Long departmentId;
    private String departmentName;
    private String departmentCode;
}
