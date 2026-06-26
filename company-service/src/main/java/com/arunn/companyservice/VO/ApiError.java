package com.arunn.companyservice.VO;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private String path;
    private HttpStatus status;
    private LocalDateTime timestamps;
}
