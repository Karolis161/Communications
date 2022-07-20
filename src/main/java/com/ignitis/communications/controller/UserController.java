package com.ignitis.communications.controller;

import com.ignitis.communications.dto.Message;
import com.ignitis.communications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("sendMessage")
    public void sendMessage(@RequestBody Message message) {
        userService.sendMessage(message);
    }
}
