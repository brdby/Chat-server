package com.beardby.entity;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class User {
    private static int nextID = 0;

    private String name = "noName";
    private final int ID;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private ArrayList<Chat> chatList;

    public User(Socket socket, Chat chat) throws IOException {
        ID = nextID;
        nextID++;
        this.socket = socket;
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        chatList = new ArrayList<>();
        chatList.add(chat);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String readMsg() throws IOException {
        return  in.readLine();
    }

    public synchronized void writeMsg(String username, String msg) throws IOException {
        out.write(username + ":" + msg + "\n");
        out.flush();
    }

    public ArrayList<Chat> getChatList() {
        return chatList;
    }

    public boolean isConnected(){
        return socket.isConnected();
    }


}
