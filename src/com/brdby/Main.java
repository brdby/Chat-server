package com.brdby;

import com.brdby.entity.Chat;
import com.brdby.entity.User;
import com.brdby.threads.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	    try (ServerSocket server = new ServerSocket(3344)){
            Chat mainChat = new Chat();

	        while (true){
	            Socket client = server.accept();
	            User user = new User(client, mainChat);
                new ClientHandler(user).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
