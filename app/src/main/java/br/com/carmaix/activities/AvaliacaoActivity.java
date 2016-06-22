package br.com.carmaix.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragment;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoActivity extends BaseActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new AvaliacaoFragmentTab()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        setupSearchView(menuItem);

        searchView = (SearchView) menuItem.getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconified(true);

        return true;
    }

    private void setupSearchView(MenuItem searchItem) {
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
    }

}
