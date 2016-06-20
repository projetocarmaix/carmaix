package br.com.carmaix.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragment;

/**
 * Created by fernando on 23/05/16.
 */
public class AvaliacaoTabAdapter extends FragmentPagerAdapter {

    private AvaliacaoFragment tab1 = null;
    private AvaliacaoFragment tab2 = null;
    private AvaliacaoFragment tab3 = null;
    private AvaliacaoFragment tab4 = null;
    private AvaliacaoFragment tab5 = null;

    private Context context;

    public AvaliacaoTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        AvaliacaoFragment avaliacaoFragment = null;

        Bundle bundle = new Bundle();

        if(position == 0) {

            if (tab1 == null){
                tab1 = new AvaliacaoFragment();
            }

            avaliacaoFragment = tab1;

            bundle.putString("status", "pré-cadastro");

        }else if (position == 1) {

            if (tab2 == null){
                tab2 = new AvaliacaoFragment();
            }

            avaliacaoFragment = tab2;

            bundle.putString("status", "avaliado");

        }else if (position == 2) {

            if (tab3 == null){
                tab3 = new AvaliacaoFragment();
            }

            avaliacaoFragment = tab3;

            bundle.putString("status",this.context.getResources().getString(R.string.tab_title_com_proposta));

        }else if (position == 3) {

            if (tab4 == null){
                tab4 = new AvaliacaoFragment();
            }

            avaliacaoFragment = tab4;

            bundle.putString("status", "estoque");

        }else if (position == 4) {

            if (tab5 == null){
                tab5 = new AvaliacaoFragment();
            }

            avaliacaoFragment = tab5;

            bundle.putString("status",this.context.getResources().getString(R.string.tab_title_vendidos));

        }

        avaliacaoFragment.setArguments(bundle);

        return avaliacaoFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if(position == 0) {
            title = this.context.getResources().getString(R.string.tab_title_nao_avaliados);
        }else if (position == 1) {
            title = this.context.getResources().getString(R.string.tab_title_avaliados);
        }else if (position == 2) {
            title = this.context.getResources().getString(R.string.tab_title_com_proposta);
        }else if (position == 3) {
            title = this.context.getResources().getString(R.string.tab_title_em_estoque);
        }else if (position == 4) {
            title = this.context.getResources().getString(R.string.tab_title_vendidos);
        }

        return title;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
