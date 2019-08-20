package com.beardby.entity;

import com.beardby.Main;
import com.beardby.util.JSONChatParser;
import com.beardby.util.UserDisconnectedException;

import java.beans.IntrospectionException;
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

    public Message readMsg() throws IOException, UserDisconnectedException {
        String raw = in.readLine();

        //Check if user disconnected not safe
        if (raw == null || !this.isConnected())
            throw new UserDisconnectedException("User " + this.toString() + "disconnected");

        //get everything from json
        Integer chatID = JSONChatParser.getChatID(raw);
        String messageText = JSONChatParser.getMessageText(raw);
        String commandName = JSONChatParser.getCommand(raw);
        String[] commandSpecifier = JSONChatParser.getCommandSpecifier(raw);

        //If there is a command, execute it
        if (commandName != null){
            Main.execCommand(commandName, this, commandSpecifier);
        }

        //Check for error with JSON
        if (chatID == null || messageText == null) {
            System.out.println("Cannot read JSON from user-" + this.toString());
            return null;
        }
        else {
            //Trying to find chat with chatID from JSON
            for (Chat c : chatList) {
                if (c.getID() == chatID) {
                    return new Message(c, this, messageText);
                }
            }
            System.out.println("Cannot find chat with id " + chatID + " user-" + this.toString());
            return null;
        }
    }

    public synchronized void sendMsg(Message message) throws IOException {
        out.write(JSONChatParser.createJSONMessage(message.getChat(), message.getUser(), message.getMessage()));
        out.flush();
    }

    public synchronized void sendMsg(String msg) throws IOException {
        for (Chat c : chatList){
            out.write(JSONChatParser.createJSONMessage(c, msg));
            out.flush();
        }
    }

    public ArrayList<Chat> getChatList() {
        return chatList;
    }

    public boolean isConnected(){
        return socket.isConnected();
    }

    @Override
    public String toString() {
        return name + "[id-" + ID + "]";
    }
}
