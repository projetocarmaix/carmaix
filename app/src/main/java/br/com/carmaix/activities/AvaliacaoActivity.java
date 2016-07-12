package br.com.carmaix.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SearchEvent;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragment;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoActivity extends BaseActivity {

    private SearchView searchView;
    private AvaliacaoFragmentTab avaliacaoFragmentTab = new AvaliacaoFragmentTab();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, avaliacaoFragmentTab).commit();
    }

    @Override
    public void onBackPressed() {

        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_nao_avaliados) {
            avaliacaoFragmentTab.getTabByIndex(Constants.TAB_CINZA).select();
        }else if (id == R.id.menu_avaliados) {
            avaliacaoFragmentTab.getTabByIndex(Constants.TAB_VERMELHA).select();
        }else if (id == R.id.menu_com_proposta) {
            avaliacaoFragmentTab.getTabByIndex(Constants.TAB_LARANJA).select();
        }else if (id == R.id.menu_em_estoque) {
            avaliacaoFragmentTab.getTabByIndex(Constants.TAB_VERDE).select();
        }else if (id == R.id.menu_vendidos) {
            avaliacaoFragmentTab.getTabByIndex(Constants.TAB_ROXO).select();
        }
        super.onNavigationItemSelected(item);
        return true;
    }

    public void setItemMenuSelected(int index) {
        getNavigationView().getMenu().getItem(index).setChecked(true);
    }
}