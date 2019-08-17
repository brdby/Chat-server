package com.beardby.entity;

public class Message {
    private Chat chat;
    private User user;
    private String message;

    public Message(Chat chat, User user, String message){
        this.chat = chat;
        this.user = user;
        this.message = message;
    }

    public Chat getChat() {
        return chat;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
