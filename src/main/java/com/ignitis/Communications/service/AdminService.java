package com.ignitis.Communications.service;

import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.repository.AdminRepository;
import com.ignitis.Communications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return adminRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = adminRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("username exists");
        }
        adminRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        boolean exists = adminRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        adminRepository.deleteById(userId);
    }

    public Map<String, Object> getUsersData(User user, String senderUsername) {
        Map<String, Object> map = new HashMap<>();

        map.put("Username", user.getUsername());
        map.put("Number of messages", "aaaa");
        map.put("Time of first message", "aaaa");
        map.put("Time of last message", "aaaa");
        map.put("Average message length", "aaaa");
        map.put("Text of last message", "aaaa");
        System.out.println(userRepository.countBySenderUsername(senderUsername));
        return map;
    }
}
