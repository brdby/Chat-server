package com.beardby.threads;

import com.beardby.entity.Chat;
import com.beardby.entity.Message;
import com.beardby.entity.User;
import com.beardby.util.JSONChatParser;
import com.beardby.util.UserDisconnectedException;

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
                Message message = user.readMsg();

                //readMsg returns null if we have problems
                if (message == null) System.out.println("Error with message from user " + user.toString());
                else message.send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UserDisconnectedException e) {
            e.getMessage();
        } finally {
            for (Chat c : user.getChatList()){
                c.leaveChat(user);
            }
        }
    }
}
