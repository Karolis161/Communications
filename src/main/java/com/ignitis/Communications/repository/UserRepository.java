package com.ignitis.Communications.repository;

import com.ignitis.Communications.dto.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySenderUsername(String senderUsername);
    int countBySenderUsername(String senderUsername);
    Message findFirstBySenderUsernameOrderByTimeOfMessageAsc(String senderUsername);
    Message findFirstBySenderUsernameOrderByTimeOfMessageDesc(String senderUsername);
    List<Message> findMessageBySenderUsername(String senderUsername);
}