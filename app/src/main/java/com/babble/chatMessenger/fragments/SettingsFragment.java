package com.babble.chatMessenger.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.babble.chatMessenger.CustomSettingsAdapter;
import com.babble.chatMessenger.R;
import com.babble.chatMessenger.SettingsList;

import java.util.ArrayList;
import java.util.List;


public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments


        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Settings Activity");

        //TODO Define chat list
        ArrayList<SettingsList> settings=new ArrayList<>();
        settings.add(new SettingsList("Notifications and Sounds","Adjust the volume the type of notifics.",R.drawable.setl1));
        settings.add(new SettingsList("Connected devices","Bluetooth, Cast",R.drawable.setl2));
        settings.add(new SettingsList("Battery","71% - charging",R.drawable.setl3));
        settings.add(new SettingsList("Apps and Notifications","Permissions, Default Apps",R.drawable.setl4));
        settings.add(new SettingsList("Display","Wallpaper, sleep, font size",R.drawable.setl5));
        settings.add(new SettingsList("Storage","44% used - 35.88 GB free",R.drawable.setl6));
        settings.add(new SettingsList("Security & location","Screen lock, fingerprint",R.drawable.setl7));
        settings.add(new SettingsList("Users & accounts","Current user: Jai",R.drawable.setl8));
        settings.add(new SettingsList("Accessibility","Screen readers, display, interaction",R.drawable.setl9));
        settings.add(new SettingsList("Gestures","Gestures specific to char related support",R.drawable.setl10));
        settings.add(new SettingsList("Additional Buttons","Alert Slider, quick toggle",R.drawable.setl11));
        settings.add(new SettingsList("System","Languages, time, backup, updates",R.drawable.setl12));

        CustomSettingsAdapter customSettingsAdapter=new CustomSettingsAdapter(getActivity(),settings);
        ListView listView=(ListView)getView().findViewById(R.id.tweakslisting);
        listView.setAdapter(customSettingsAdapter);
    }
}
