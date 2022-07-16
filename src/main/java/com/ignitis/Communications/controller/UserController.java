package com.ignitis.Communications.controller;

import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService usersService;

    @Autowired
    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getUsers() {
        return usersService.getUsers();
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        usersService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        usersService.deleteUser(userId);
    }

//    @PutMapping(path = "{userId}")
//    public void updateUser(@PathVariable("userId") Integer userId,
//            @RequestParam(required = false) String username) {
//        usersService.updateUser(userId, username);
//    }
}
