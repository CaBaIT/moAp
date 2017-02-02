package com.example.cabait.rssfeed;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFeedFragment extends Fragment {
    private OnURLChangedListener onURLChangedListener;

    public interface OnURLChangedListener{
        void onURLChange(String url);
    }
    public AddFeedFragment() {
        // Required empty public constructor
    }


    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_feed, container, false);
        // Inflate the layout for this fragment

        editText =  ((EditText) view.findViewById(R.id.edURL));
        editText.setText("http://rss.kicker.de/team/scfreiburg");
        Button bt = (Button) view.findViewById(R.id.bSaveFeed);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url =editText.getText().toString();
                onURLChangedListener.onURLChange(url);
                getFragmentManager()
                        .beginTransaction()
                        .remove(AddFeedFragment.this)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AddFeedFragment.OnURLChangedListener){
            this.onURLChangedListener = (AddFeedFragment.OnURLChangedListener) context;

        }
    }

}
