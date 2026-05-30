package com.arunn.userservice.VO;

import com.arunn.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {
    private User user;
    private Department department;
}
