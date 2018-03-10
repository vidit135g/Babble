package com.babble.chatMessenger;

/**
 * Created by vidit on 3/6/2018.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomChatAdapter extends ArrayAdapter<ChatList>{
    private static final String LOG_TAG = CustomChatAdapter.class.getSimpleName();

    public CustomChatAdapter(Activity context, ArrayList<ChatList> chatList) {
        super(context,0, chatList);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView=convertView;
        if(listItemView==null){
            listItemView=LayoutInflater.from(getContext()).inflate(R.layout.list_view,parent,false);
        }

        ChatList chatList=getItem(position);

        TextView nameTextView=(TextView)listItemView.findViewById(R.id.username);
        TextView convTextView=(TextView)listItemView.findViewById(R.id.conv);
        TextView timeTextView=(TextView)listItemView.findViewById(R.id.ctime);
        ImageView avatarIocn=(ImageView)listItemView.findViewById(R.id.list_item_icon);

        nameTextView.setText(chatList.getUserName());
        convTextView.setText(chatList.getUserConv());
        timeTextView.setText(chatList.getChatTime());
        avatarIocn.setImageResource(chatList.getAvatarResourceId());
        return listItemView;
    }
}
