package com.example.cabait.rssfeed;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.OnItemSelectedListener, AddFeedFragment.OnURLChangedListener  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("RSS Reader");
        if (savedInstanceState == null) {
            MainActivityFragment firstFragment = new MainActivityFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frameLayout, firstFragment);
            ft.commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.changeurl) {
           AddFeedFragment addFeedFragment = new AddFeedFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout,addFeedFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRSSItemSelected(RSSItem item) {
        DetailFragment fragDetail = new DetailFragment();
        fragDetail.setRSSItem(item);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout,fragDetail)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onURLChange(String url) {
        MainActivityFragment mainActivityFragment= new MainActivityFragment();
        DataBaseHelper db = DataBaseHelper.getInstance(this);
        db.addUrl(url);
        //mainActivityFragment.setUrl(url);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout,mainActivityFragment)
                .addToBackStack(null)
                .commit();
    }
}
