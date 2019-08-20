package com.beardby;

import com.beardby.entity.Chat;
import com.beardby.entity.User;
import com.beardby.entity.commands.Command;
import com.beardby.entity.commands.changeName;
import com.beardby.threads.ClientHandler;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    private static ArrayList<Chat> allChats = new ArrayList<>();
    private static final HashMap<String, Command> commandList  =  new HashMap<>(1);

    static {
        var changeName = new changeName();
        commandList.put(changeName.getCommandText(), changeName);
    }

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

    public static boolean execCommand(String commandName, User user, String... commandSpecifier){
        Command command = commandList.get(commandName);
        if (command == null) return false;
        else return command.execCommand(user, commandSpecifier);
    }

}
