package com.arunn.userservice.service;

import com.arunn.userservice.VO.Department;
import com.arunn.userservice.VO.ResponseVO;
import com.arunn.userservice.entity.User;
import com.arunn.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public ResponseVO getUserWithDepartment(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Department department = restTemplate
                .getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                        Department.class);

        ResponseVO vo = new ResponseVO();
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }

    public List<User> getUsersByCompanyId(Long companyId) {
        return userRepository.findByCompanyId(companyId);
    }
}
