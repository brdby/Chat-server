package com.beardby;

import com.beardby.entity.Chat;
import com.beardby.entity.User;
import com.beardby.threads.ClientHandler;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Chat> allChats = new ArrayList<>();

    public static void main(String[] args) {

	    try (ServerSocket server = new ServerSocket(3344)){
            Chat mainChat = new Chat();
            allChats.add(mainChat);

	        while (true){
	            Socket client = server.accept();
	            User user = new User(client, mainChat);
	            mainChat.addUsers(user);
                new ClientHandler(user).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized  void addChat(Chat chat){
        allChats.add(chat);
    }

}
