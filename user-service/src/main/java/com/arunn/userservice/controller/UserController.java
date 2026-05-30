package com.arunn.userservice.controller;

import com.arunn.userservice.VO.ResponseVO;
import com.arunn.userservice.entity.User;
import com.arunn.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseVO getUserWithDepartment(@PathVariable("userId") Long userId){
        return userService.getUserWithDepartment(userId);
    }

    @GetMapping("/companies/{companyId}")
    public List<User> getUsersByCompanyId(@PathVariable("companyId") Long companyId){
        return userService.getUsersByCompanyId(companyId);
    }
}
