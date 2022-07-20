package com.ignitis.Communications.service;

import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

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
        return UserService.getUserData(senderUsername);
    }
}
