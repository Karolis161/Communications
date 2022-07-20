package com.ignitis.communications.service;

import com.ignitis.communications.dto.Message;
import com.ignitis.communications.repository.AdminRepository;
import com.ignitis.communications.repository.UserRepository;
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
            throw new IllegalStateException("Entered user does not exist");
        }
        userRepository.save(message);
    }
}
