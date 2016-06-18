package br.com.carmaix.activities;

import android.os.Bundle;
import android.view.MenuItem;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragment;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoActivity extends BaseActivity {
    private AvaliacaoFragmentTab avaliacaoFragmentTab = new AvaliacaoFragmentTab();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*commit da feature teste*/
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, avaliacaoFragmentTab).commit();
        this.setItemMenuSelected(0);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_nao_avaliados) {
            avaliacaoFragmentTab.getTabByIndex(0).select();
        }else if (id == R.id.menu_avaliados) {
            avaliacaoFragmentTab.getTabByIndex(1).select();
        }else if (id == R.id.menu_com_proposta) {
            avaliacaoFragmentTab.getTabByIndex(2).select();
        }else if (id == R.id.menu_em_estoque) {
            avaliacaoFragmentTab.getTabByIndex(3).select();
        }else if (id == R.id.menu_vendidos) {
            avaliacaoFragmentTab.getTabByIndex(4).select();
        }
        super.onNavigationItemSelected(item);
        return true;
    }

    public void setItemMenuSelected(int index) {
        getNavigationView().getMenu().getItem(index).setChecked(true);
    }
}
