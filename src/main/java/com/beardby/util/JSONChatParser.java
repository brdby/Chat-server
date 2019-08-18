package com.beardby.util;

import com.beardby.entity.Chat;
import com.beardby.entity.Message;
import com.beardby.entity.User;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONChatParser {

    public static String getMessageText(String jsonContainer)  {
        try {
            JSONObject jsonObj = new JSONObject(jsonContainer);
            String msg = jsonObj.getString("messageText");
            return msg;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getChatID(String jsonContainer){
        try {
            JSONObject jsonObj = new JSONObject(jsonContainer);
            int chatID = jsonObj.getInt("chatID");
            return chatID;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
