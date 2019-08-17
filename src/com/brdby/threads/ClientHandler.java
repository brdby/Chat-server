package com.brdby.threads;

import com.brdby.entity.User;

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
                if (msg == null || !user.isConnected() || msg.equals("/exit") ) break;

            }
            user.getCurrentChat().leaveChat(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
