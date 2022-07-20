package com.ignitis.communications.service;

import com.ignitis.communications.dto.Message;
import com.ignitis.communications.dto.User;
import com.ignitis.communications.repository.AdminRepository;
import com.ignitis.communications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<User> getUsers() {
        return adminRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = adminRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Entered user already exist");
        }
        adminRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        boolean exists = adminRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id: " + userId + " does not exist");
        }
        adminRepository.deleteById(userId);
    }

    public Map<String, Object> getUserData(String senderUsername) {
        Map<String, Object> map = new LinkedHashMap<>();
        float sum = 0;

        for (int i = 0; i < userRepository.countBySenderUsername(senderUsername); i++) {
            Message message = userRepository.findMessageBySenderUsername(senderUsername).get(i);
            int count = message.getUserMessage().length();
            sum += count;
        }

        if (userRepository.countBySenderUsername(senderUsername) >= 1) {
            map.put("User", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageAsc(senderUsername).getSenderUsername());
            map.put("Message Count", userRepository.countBySenderUsername(senderUsername));
            map.put("Time Of The First Message", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageAsc(senderUsername).getTimeOfMessage());
            map.put("Time Of The Last Message", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageDesc(senderUsername).getTimeOfMessage());
            map.put("Average Message Length", sum / userRepository.countBySenderUsername(senderUsername));
            map.put("Last Message", userRepository.findFirstBySenderUsernameOrderByTimeOfMessageDesc(senderUsername).getUserMessage());
        } else throw new IllegalStateException("Not enough data");

        return map;
    }
}
