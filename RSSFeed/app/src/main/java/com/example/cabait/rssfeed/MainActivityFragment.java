package com.example.cabait.rssfeed;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private OnItemSelectedListener onItemSelectedListener;
    //http://rss.kicker.de/team/scfreiburg
    //http://rss.nytimes.com/services/xml/rss/nyt/Politics.xml
    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public MainActivityFragment() {
    }

 public interface OnItemSelectedListener{
     void onRSSItemSelected(RSSItem item);
 }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final ListView listView= (ListView) view.findViewById(R.id.listView);
        DataBaseHelper db = DataBaseHelper.getInstance(getContext());
        if(db.getCursor()!=null){
            url=db.getUrl(db.getCursor().getCount());
        }

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
                @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              RSSItem item = (RSSItem) adapterView.getItemAtPosition(i);
              onItemSelectedListener.onRSSItemSelected(item);
            }
        });

            new GetFeed(view).execute(url);



        if (url == "" || url == null || url.isEmpty())
        {
            view.findViewById(R.id.imageView).setVisibility(View.VISIBLE);
            //Create progamming textview for EmptyView
            TextView tvEmpty = new TextView(getContext());
            String emptyString = "Please enter a URL";
            tvEmpty.setText(emptyString);
            //Allignment to horizantal und vertical to MATCH_PARENT
            tvEmpty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            //CENTER text
            tvEmpty.setGravity(Gravity.CENTER);
            //SCALE TEXT TO FRAME;
            tvEmpty.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

            //Add TextView to parent of the tasklist
            ((ViewGroup) listView.getParent()).addView(tvEmpty);
            //Set empty view for tasklist
            listView.setEmptyView(tvEmpty);
        }
        else
        {
            //Create progamming textview for EmptyView
            TextView tvEmpty = new TextView(getContext());
            String emptyString = "No news read";
            tvEmpty.setText(emptyString);
            //Allignment to horizantal und vertical to MATCH_PARENT
            tvEmpty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            //CENTER text
            tvEmpty.setGravity(Gravity.CENTER);
            //SCALE TEXT TO FRAME;
            tvEmpty.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

            //Add TextView to parent of the tasklist
            ((ViewGroup) listView.getParent()).addView(tvEmpty);
            //Set empty view for tasklist
            listView.setEmptyView(tvEmpty);
        }

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnItemSelectedListener){
            this.onItemSelectedListener = (OnItemSelectedListener) context;

        }
    }
}
