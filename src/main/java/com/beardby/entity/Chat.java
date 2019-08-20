package com.beardby.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Chat {
    private static int nextID = 0;

    private ArrayList<User> users = new ArrayList<>();
    private final int ID;

    {
        ID = nextID;
        nextID++;
    }

    public synchronized void addUsers(User... users) {
        this.users.addAll(Arrays.asList(users));
    }

    public synchronized void sendToAll(Message msg) throws IOException {
        for (User u : users) u.sendMsg(msg);
    }

    public int getID() {
        return ID;
    }

    public boolean leaveChat(User u){
        return users.remove(u);
    }
}
