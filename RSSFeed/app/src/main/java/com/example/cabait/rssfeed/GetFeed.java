package com.example.cabait.rssfeed;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Caspar on 01.02.2017.
 */

public class GetFeed extends AsyncTask<String, Void, ArrayList<RSSItem>>{


    View view;
    public GetFeed(View view) {
        this.view = view;
    }

    @Override
    protected ArrayList<RSSItem> doInBackground(String... strings) {

        ArrayList<RSSItem> items = new ArrayList<>();
        InputStream is= null;
        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser rssParser = xmlFactoryObject.newPullParser();
            is = new URL(strings[0]).openConnection().getInputStream();
            rssParser.setInput(is, null);
            RSSItem item= new RSSItem();
            String result="";
            int event = rssParser.getEventType();
             while (event != XmlPullParser.END_DOCUMENT)  {
                    String name=rssParser.getName();

                    switch (event){
                     case XmlPullParser.START_TAG:
                         if(name.equals("item")){
                        item = new RSSItem();
                             item.setTitle(null);
                             item.setDesc(null);
                             item.setLink(null);
                         }
                         break;
                     case XmlPullParser.TEXT:
                         result = rssParser.getText();
                         break;
                     case XmlPullParser.END_TAG:
                         if(name.equals("item")){
                             items.add(item);
                         }if (name.equalsIgnoreCase("title")){
                         item.setTitle(result);
                     }if (name.equalsIgnoreCase("link")){
                         item.setLink(result);
                     }if (name.equalsIgnoreCase("description")){
                         item.setDesc(result);
                     }
                    break;
            }
            event = rssParser.next();
        }} catch (IOException e) {
                e.printStackTrace();
            }
            catch (XmlPullParserException e) {
                e.printStackTrace();
            }

        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<RSSItem> rssItems) {
        super.onPostExecute(rssItems);

        ListView listview = (ListView) view.findViewById(R.id.listView);
        listview.setAdapter(new RSSListAdapter(rssItems,view.getContext()));
    }
}
