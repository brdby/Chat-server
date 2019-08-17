package com.beardby.threads;

import com.beardby.entity.Chat;
import com.beardby.entity.Message;
import com.beardby.entity.User;
import com.beardby.util.MessageJsonParser;

import java.io.IOException;

public class ClientHandler extends Thread{
    private User user;

    public ClientHandler(User user){
        this.user = user;
    }

    @Override
    public void run() {
        try {
            while (true){
                String msg = user.readMsg();
                if (msg == null || !user.isConnected()) break;
                Message message = MessageJsonParser.getMessage(user, msg);
                message.getChat().sendToAll(message);
            }
            for (Chat c : user.getChatList()){
                c.leaveChat(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
