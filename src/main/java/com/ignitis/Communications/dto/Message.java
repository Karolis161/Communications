package com.ignitis.Communications.dto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )

    private int id;
    private String senderUsername;
    private LocalDateTime time;
    private String message;

    public Message() {
    }

    public Message(int id, String senderUsername, LocalDateTime time, String message) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.time = time;
        this.message = message;
    }

    public Message(String senderUsername, LocalDateTime time, String message) {
        this.senderUsername = senderUsername;
        this.time = time;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
