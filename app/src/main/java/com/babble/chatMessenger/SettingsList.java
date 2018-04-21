package com.babble.chatMessenger;

/**
 * Created by vidit on 3/16/2018.
 */

public class SettingsList {
    private String cSettingsName;
    private String cSettingsDesc;
    private int cAvatarResourceId;

    public SettingsList(String name,String conv,int imageid){
        cSettingsName=name;
        cSettingsDesc=conv;
        cAvatarResourceId=imageid;
    }

    public String getUserName(){
        return cSettingsName;
    }
    public String getUserConv(){
        return cSettingsDesc;
    }
    public int getAvatarResourceId(){
        return cAvatarResourceId;
    }
}

