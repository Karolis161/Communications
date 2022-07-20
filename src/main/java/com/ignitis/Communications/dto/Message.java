package com.ignitis.Communications.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    private int id;
    private String senderUsername;
    private LocalDateTime timeOfMessage;
    private String message;

    public Message() {
    }

    public Message(int id, String senderUsername, LocalDateTime timeOfMessage, String message) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.timeOfMessage = timeOfMessage;
        this.message = message;
    }

    public Message(String senderUsername, LocalDateTime timeOfMessage, String message) {
        this.senderUsername = senderUsername;
        this.timeOfMessage = timeOfMessage;
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

    public LocalDateTime getTimeOfMessage() {
        return timeOfMessage;
    }

    public void setTimeOfMessage(LocalDateTime timeOfMessage) {
        this.timeOfMessage = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderUsername='" + senderUsername + '\'' +
                ", timeOfMessage=" + timeOfMessage +
                ", message='" + message + '\'' +
                '}';
    }
}
