package com.babble.chatMessenger;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vidit on 3/16/2018.
 */
public class CustomSettingsAdapter extends ArrayAdapter<SettingsList>{

    private Typeface tf;
    private Typeface pf;

    private static final String LOG_TAG = CustomSettingsAdapter.class.getSimpleName();

    public CustomSettingsAdapter(Activity context, ArrayList<SettingsList> settingsLists) {
        super(context,0, settingsLists);
        this.tf = Typeface.createFromAsset(context.getAssets(),"fonts/fprimary.ttf");
        this.pf= Typeface.createFromAsset(context.getAssets(),"fonts/fbold.ttf");
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.settings_list,parent,false);
        }

        SettingsList settingsList =getItem(position);

        TextView nameTextView=(TextView)listItemView.findViewById(R.id.settingname);
        TextView descTextView=(TextView)listItemView.findViewById(R.id.settingDesc);
        ImageView avatarIocn=(ImageView)listItemView.findViewById(R.id.list_item_setting_icon);

        nameTextView.setText(settingsList.getUserName());
        descTextView.setText(settingsList.getUserConv());
        avatarIocn.setImageResource(settingsList.getAvatarResourceId());
        nameTextView.setTypeface(pf);
        descTextView.setTypeface(tf);
        return listItemView;
    }
}
