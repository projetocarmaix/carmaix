package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.adapters.AvaliarTabAdapter;
import br.com.carmaix.view.CustomViewPager;

/**
 * Created by fernando on 08/07/16.
 */
public class AvaliarFragmentTab extends BaseFragment {
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private AvaliarTabAdapter avaliarTabAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avaliar_fragment_tab,container,false);

        tabLayout = (TabLayout)view.findViewById(R.id.avaliarTab);
        viewPager = (CustomViewPager) view.findViewById(R.id.avaliarViewPager);
        avaliarTabAdapter = new AvaliarTabAdapter(getContext(), getChildFragmentManager());
        viewPager.setAdapter(avaliarTabAdapter);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                if(tab.getPosition() == 4) {
                    List<Fragment> fragments = getChildFragmentManager().getFragments();
                    for (Fragment fragment : fragments) {
                        if (fragment instanceof EstatisticaFragment) {
                            ((EstatisticaFragment) fragment).loadData();
                            break;
                        }
                    }
                }
            }
        });

        setupTabIcons();

        return view;
    }

    private void setupTabIcons() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        TextView tabOne = (TextView)view.findViewById(R.id.tab_text);
        tabOne.setText(fragmentActivity.getString(R.string.veiculo_cliente));
        ImageView icon = (ImageView)view.findViewById(R.id.tab_icon);
        icon.setImageResource(R.drawable.ic_directions_car_black_48dp);
        tabOne.setTextSize(9);

        tabLayout.getTabAt(0).setCustomView(view);

        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        TextView tabTwo = (TextView)view2.findViewById(R.id.tab_text);
        tabTwo.setText(fragmentActivity.getString(R.string.opcionais));
        ImageView icon2 = (ImageView)view2.findViewById(R.id.tab_icon);
        icon2.setImageResource(R.drawable.ic_check_circle_black_48dp);

        tabLayout.getTabAt(1).setCustomView(view2);

        View view3 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        TextView tabThree = (TextView)view3.findViewById(R.id.tab_text);
        tabThree.setText(fragmentActivity.getString(R.string.mecanica));
        ImageView icon3 = (ImageView)view3.findViewById(R.id.tab_icon);
        icon3.setImageResource(R.drawable.ic_build_black_48dp);

        tabLayout.getTabAt(2).setCustomView(view3);

        View view4 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        TextView tabFour = (TextView)view4.findViewById(R.id.tab_text);
        tabFour.setText(fragmentActivity.getString(R.string.fotos));
        ImageView icon4 = (ImageView)view4.findViewById(R.id.tab_icon);
        icon4.setImageResource(R.drawable.ic_camera_alt_black_48dp);

        tabLayout.getTabAt(3).setCustomView(view4);

        View view5 = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        TextView tabFive = (TextView)view5.findViewById(R.id.tab_text);
        tabFive.setText(fragmentActivity.getString(R.string.estatistica_preco));
        tabFive.setTextSize(8);
        ImageView icon5 = (ImageView)view5.findViewById(R.id.tab_icon);
        icon5.setImageResource(R.drawable.ic_attach_money_black_48dp);

        tabLayout.getTabAt(4).setCustomView(view5);

        tabLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.tab_background_avaliar));
        tabLayout.setSelectedTabIndicatorColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
    }



}
