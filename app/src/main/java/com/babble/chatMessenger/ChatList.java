package com.babble.chatMessenger;

/**
 * Created by vidit on 3/6/2018.
 */

public class ChatList {

    private String cUserName;
    private String cUserConv;
    private String cTime;

    private int cAvatarResourceId;

    public ChatList(String name,String conv,String time,int imageid){
        cUserName=name;
        cUserConv=conv;
        cTime=time;
        cAvatarResourceId=imageid;
    }

    public String getUserName(){
        return cUserName;
    }
    public String getUserConv(){
        return cUserConv;
    }
    public String getChatTime(){
        return cTime;
    }
    public int getAvatarResourceId(){
        return cAvatarResourceId;
    }
}
