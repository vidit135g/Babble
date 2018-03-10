package com.babble.chatMessenger;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by vidit on 2/25/2018.
 */

public class CustomPageAdapterText extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mResources;
    private Typeface customfont;
    public CustomPageAdapterText(Context context, String[] resources,Typeface custom) {
        mContext = context;
        mResources = resources;
        customfont=custom;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item_text, container, false);
        TextView textView = (TextView) itemView.findViewById(R.id.textView);
        textView.setText(mResources[position]);
        textView.setTypeface(customfont);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
