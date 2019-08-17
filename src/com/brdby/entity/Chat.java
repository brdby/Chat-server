package com.brdby.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

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

    public synchronized boolean sendToAll(User user, String msg) throws IOException {
        for (User u : users) u.writeMsg(user.getName() + "[id-" + user.getID() + "]", msg);
        return true;
    }

    public int getID() {
        return ID;
    }

    public boolean leaveChat(User u){
        return users.remove(u);
    }
}
