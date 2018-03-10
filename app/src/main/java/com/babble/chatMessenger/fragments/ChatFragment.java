package com.babble.chatMessenger.fragments;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ListView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.babble.chatMessenger.ChatList;
import com.babble.chatMessenger.CustomChatAdapter;
import com.babble.chatMessenger.PostLoginActivity;
import com.babble.chatMessenger.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class ChatFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //get the current date

        return inflater.inflate(R.layout.fragment_chat, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Chat Activity");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String localTime = date.format(currentLocalTime);
        //TODO create an ArrayList comprising of the chatList objects
        ArrayList<ChatList> chats=new ArrayList<ChatList>();
        chats.add(new ChatList("Jai","Hello",localTime,R.drawable.av1));
        chats.add(new ChatList("Priyesh","coming in 2 minutes",localTime,R.drawable.av2));
        chats.add(new ChatList("Apoorv","Hello",localTime,R.drawable.av3));
        chats.add(new ChatList("Shawn","Hello",localTime,R.drawable.av4));
        chats.add(new ChatList("Jason","Hello",localTime,R.drawable.av5));
        chats.add(new ChatList("Dwayne","Hello",localTime,R.drawable.av6));
        chats.add(new ChatList("Chris","Hello",localTime,R.drawable.av7));
        chats.add(new ChatList("Chris","Hello",localTime,R.drawable.av8));
        chats.add(new ChatList("Chris","Hello",localTime,R.drawable.av9));
        chats.add(new ChatList("Chris","Hello",localTime,R.drawable.av10));
        chats.add(new ChatList("Chris","Hello",localTime,R.drawable.av11));
        chats.add(new ChatList("Chris","Hello",localTime,R.drawable.av12));
        chats.add(new ChatList("Chris","Hello",localTime,R.drawable.av13));

        CustomChatAdapter customChatAdapter=new CustomChatAdapter(getActivity(),chats);

        ListView listView = (ListView) getView().findViewById(R.id.chatlisting);
        listView.setAdapter(customChatAdapter);
    }



}
