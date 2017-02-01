package com.example.cabait.rssfeed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    RSSItem rssItem;
    TextView tvDesc;
    TextView tvLink;
    TextView tvTitel;
    public DetailFragment() {
        // Required empty public constructor
    }

    public void setRSSItem(RSSItem rssItem)
    {
        this.rssItem=rssItem;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tvLink = (TextView) view.findViewById(R.id.Link);
        tvDesc = (TextView) view.findViewById(R.id.Desc);
        tvTitel = (TextView) view.findViewById(R.id.Titel);

        tvDesc.setText(rssItem.getDesc());
        tvLink.setText(rssItem.getLink());
        tvTitel.setText(rssItem.getTitle());


        return view;
    }

}
