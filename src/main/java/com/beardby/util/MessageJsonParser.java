package com.beardby.util;

import com.beardby.entity.Chat;
import com.beardby.entity.Message;
import com.beardby.entity.User;
import org.json.JSONObject;

public class MessageJsonParser {

    public static Message getMessage(User user, String jsonContainer)  {
        Object obj = null;
            JSONObject jsonObj = new JSONObject(jsonContainer);
            Chat chat;
            String msgText;
            Message message;
            for (Chat c : user.getChatList()){
                if (c.getUsers().contains(user) && c.getID() == jsonObj.getInt("chatID")){
                    chat = c;
                    msgText = jsonObj.getString("messageText");
                    return new Message(chat, user, msgText);
                }
            }
            return null;
    }
}
