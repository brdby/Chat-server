package com.beardby.util;

import com.beardby.entity.Chat;
import com.beardby.entity.Message;
import com.beardby.entity.User;
import com.sun.jdi.ArrayReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONChatParser {
    public static String createJSONMessage(Chat chat, User user, String msg){
        JSONObject jsonObj = new JSONObject();
        jsonObj.append("chatID", chat.getID());
        jsonObj.append("user", user.toString());
        jsonObj.append("message", msg);
        return jsonObj.toString() + "\n";
    }

    public static String createJSONMessage(Chat chat, String msg){
        JSONObject jsonObj = new JSONObject();
        jsonObj.append("chatID", chat.getID());
        jsonObj.append("user", null);
        jsonObj.append("message", msg);
        return jsonObj.toString() + "\n";
    }

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

    public static String getCommand(String jsonContainer){
        try {
            JSONObject jsonObj = new JSONObject(jsonContainer);
            String msg = jsonObj.getString("commandName");
            return msg;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String[] getCommandSpecifier(String jsonContainer){
        try {
            JSONObject jsonObj = new JSONObject(jsonContainer);
            JSONArray jsonArr = jsonObj.getJSONArray("commandSpecifier");
            ArrayList<String> solution = new ArrayList<>();
            Iterator iterator = jsonArr.iterator();
            while (iterator.hasNext()){
                solution.add((String) iterator.next());
            }
            if (!solution.isEmpty()) return (String[]) solution.toArray();
            else return null;
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
