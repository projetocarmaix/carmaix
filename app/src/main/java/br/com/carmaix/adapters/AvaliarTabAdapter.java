package br.com.carmaix.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.carmaix.fragments.EstatisticaFragment;
import br.com.carmaix.fragments.FotosFragment;
import br.com.carmaix.fragments.MecanicaFragment;
import br.com.carmaix.fragments.OpcionaisFragment;
import br.com.carmaix.fragments.VeiculoClienteFragment;

/**
 * Created by fernando on 23/05/16.
 */
public class AvaliarTabAdapter extends FragmentPagerAdapter {
    private Context context;

    public AvaliarTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new OpcionaisFragment();
        if(position == 0) {
            fragment = new VeiculoClienteFragment();
        }else if(position == 1) {
            fragment = new OpcionaisFragment();
        }else if(position == 2) {
            fragment = new MecanicaFragment();
        }else if(position == 3) {
            fragment = new FotosFragment();
        }else if(position == 4) {
            fragment = new EstatisticaFragment();
        }

        return fragment;
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if(position == 0) {
            title = this.context.getResources().getString(R.string.veiculo_cliente);
        }else if (position == 1) {
            title = this.context.getResources().getString(R.string.opcionais);
        }else if (position == 2) {
            title = this.context.getResources().getString(R.string.mecanica);
        }else if (position == 3) {
            title = this.context.getResources().getString(R.string.fotos);
        }else if (position == 4) {
            title = this.context.getResources().getString(R.string.estatistica_preco);
        }

        return title;
    }*/

    @Override
    public int getCount() {
        return 5;
    }
}