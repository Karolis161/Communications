package com.ignitis.Communications.service;

import com.ignitis.Communications.dto.Message;
import com.ignitis.Communications.repository.AdminRepository;
import com.ignitis.Communications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserService(UserRepository userRepository, AdminRepository adminRepository) {
        UserService.userRepository = userRepository;
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

    public static Map<String, Object> getUserData(String senderUsername) {
        Map<String, Object> map = new LinkedHashMap<>();
        float sum = 0;

        for (int i = 0; i < userRepository.countBySenderUsername(senderUsername); i++) {
            Message message = userRepository.findMessageBySenderUsername(senderUsername).get(i);
            int count = message.getMessage().length();
            sum += count;
        }
        map.put("User", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageAsc(senderUsername).getSenderUsername());
        map.put("Message Count", userRepository.countBySenderUsername(senderUsername));
        map.put("Time Of The First Message", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageAsc(senderUsername).getTimeOfMessage());
        map.put("Time Of The Last Message", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageDesc(senderUsername).getTimeOfMessage());
        map.put("Average Message Length", sum / userRepository.countBySenderUsername(senderUsername));
        map.put("Last Message", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageDesc(senderUsername).getMessage());

        return map;
    }
}
