package com.ignitis.Communications.service;

import com.ignitis.Communications.dto.Message;
import com.ignitis.Communications.repository.AdminRepository;
import com.ignitis.Communications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getUserData(String senderUsername) {
        Map<String, Object> map = new HashMap<>();

        float sum = 0;
        for (int i = 0; i < userRepository.countBySenderUsername(senderUsername); i++) {
            Message message = userRepository.findMessageBySenderUsername(senderUsername).get(i);
            int count = message.getMessage().length();
            sum += count;
        }

        map.put("Sender", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageAsc(senderUsername).getSenderUsername());
        map.put("Total of messages", userRepository.countBySenderUsername(senderUsername));
        map.put("First message time", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageAsc(senderUsername).getTimeOfMessage());
        map.put("Last message time", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageDesc(senderUsername).getTimeOfMessage());
        map.put("Average message length", sum / userRepository.countBySenderUsername(senderUsername));
        map.put("Last message", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageDesc(senderUsername).getMessage());
        return map;
    }
}
