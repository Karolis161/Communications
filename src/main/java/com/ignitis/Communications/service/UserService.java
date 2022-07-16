package com.ignitis.Communications.service;

import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("username exists");
        }
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

//    @Transactional
//    public void updateUser(Integer userId, String username) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "user with id " + userId + " does not exist"));
//
//        if (username != null
//                && username.length() > 0
//                && !Objects.equals(user.getUsername(), username)) {
//            Optional<User> userOptional = userRepository.findUserByUsername(username);
//            if (userOptional.isPresent()) {
//                throw new IllegalStateException("username is taken");
//            }
//            user.setUsername(username);
//        }
//    }
}
