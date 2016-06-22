package br.com.carmaix.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;
import br.com.carmaix.fragments.SearchAvaliationFragment;

/**
 * Created by fernando on 21/05/16.
 */
public class SearchAvaliationActivity extends ParentBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new SearchAvaliationFragment()).commit();

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {

            String query = getIntent().getStringExtra(SearchManager.QUERY);

            Log.i("zzz", "zzz " + query);

            Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);

            if (appData != null) {
                String hello = appData.getString("hello");
            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        setupSearchView(menuItem);

        return true;
    }

    private void setupSearchView(MenuItem searchItem) {
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        if (id == R.id.search) {
//            onSearchRequested();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
