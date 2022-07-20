package com.ignitis.Communications.controller;

import com.ignitis.Communications.dto.Message;
import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getMessage")
    public List<Message> getMessage(@RequestParam String senderUsername) {
        return userService.getMessage(senderUsername);
    }

    @GetMapping("getUserData")
    public Map<String, Object> getUserData(@RequestParam String senderUsername) {
        return userService.getUserData(senderUsername);
    }

    @PostMapping("sendMessage")
    public void sendMessage(@RequestBody Message message) {
        userService.sendMessage(message);
    }
}
