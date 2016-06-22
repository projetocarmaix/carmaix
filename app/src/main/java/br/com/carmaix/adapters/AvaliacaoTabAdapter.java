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
    private Context context;
    public AvaliacaoTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        AvaliacaoFragment avaliacaoFragment = new AvaliacaoFragment(position);
        Bundle bundle = new Bundle();
        if(position == 0) {
            bundle.putString("tipo",this.context.getResources().getString(R.string.tab_title_nao_avaliados));
        }else if (position == 1) {
            bundle.putString("tipo",this.context.getResources().getString(R.string.tab_title_avaliados));
        }else if (position == 2) {
            bundle.putString("tipo",this.context.getResources().getString(R.string.tab_title_com_proposta));
        }else if (position == 3) {
            bundle.putString("tipo",this.context.getResources().getString(R.string.tab_title_em_estoque));
        }else if (position == 4) {
            bundle.putString("tipo",this.context.getResources().getString(R.string.tab_title_vendidos));
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
