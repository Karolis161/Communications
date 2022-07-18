package com.ignitis.Communications.controller;

import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "getUsers")
    public List<User> getUsers() {
        return adminService.getUsers();
    }

    @PostMapping(path = "addNewUser")
    public void addNewUser(@RequestBody User user) {
        adminService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        adminService.deleteUser(userId);
    }

    @GetMapping(path = "getUserData/{userId}")
    public Map<String, Object> getUsersData(@PathVariable("userId") User user) {
        return adminService.getUsersData(user);
    }
}
