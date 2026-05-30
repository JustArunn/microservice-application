package com.arunn.companyservice.VO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String fullName;
    private String email;
    private Long departmentId;
    private Long companyId;
}
