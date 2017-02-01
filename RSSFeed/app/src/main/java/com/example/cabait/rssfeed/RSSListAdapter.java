package com.example.cabait.rssfeed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Caspar on 01.02.2017.
 */

public class RSSListAdapter extends BaseAdapter {
    TextView tvLink;
    TextView tvDesc;
    TextView tvTitel;

    List<RSSItem> items;
    Context context=null;

    public RSSListAdapter(List<RSSItem> items, Context context) {
        this.items = items;
        this.context= context;
    }

    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            view = view.inflate(context,R.layout.rss_item,null);
            tvLink = (TextView) view.findViewById(R.id.Link);
            tvDesc = (TextView) view.findViewById(R.id.Desc);
            tvTitel = (TextView) view.findViewById(R.id.Titel);
         }


        tvTitel.setText(items.get(i).getTitle());


        return view;
    }



}
