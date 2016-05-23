package br.com.carmaix.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        AvaliacaoFragment avaliacaoFragment = new AvaliacaoFragment();
        Bundle bundle = new Bundle();
        if(position == 0) {
            bundle.putString("tipo","Não Avaliado");
        }else if (position == 1) {
            bundle.putString("tipo","Avaliado");
        }else {
            bundle.putString("tipo","Estoque");
        }

        avaliacaoFragment.setArguments(bundle);

        return avaliacaoFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if(position == 0) {
            title = "Não Avaliado";
        }else if (position == 1) {
            title = "Avaliado";
        }else {
            title = "Estoque";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
