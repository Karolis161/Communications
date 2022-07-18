package com.ignitis.Communications.service;

import com.ignitis.Communications.dto.Message;
import com.ignitis.Communications.repository.AdminRepository;
import com.ignitis.Communications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public List<Message> getMessage(String senderUsername) {
        return userRepository.findBySenderUsername(senderUsername);
    }

    public void sendMessage(Message message) {
        if (!adminRepository.existsByUsername(message.getSenderUsername())) {
            throw new IllegalStateException("No such user exists");
        }
        userRepository.save(message);
    }
}
