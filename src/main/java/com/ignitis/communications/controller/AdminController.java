package com.ignitis.communications.controller;

import com.ignitis.communications.dto.User;
import com.ignitis.communications.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("getUsers")
    public List<User> getUsers() {
        return adminService.getUsers();
    }

    @PostMapping("addNewUser")
    public void addNewUser(@RequestBody User user) {
        adminService.addNewUser(user);
    }

    @DeleteMapping("deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        adminService.deleteUser(userId);
    }

    @GetMapping("getUserData")
    public Map<String, Object> getUserData(@RequestParam String senderUsername) {
        return adminService.getUserData(senderUsername);
    }
}
