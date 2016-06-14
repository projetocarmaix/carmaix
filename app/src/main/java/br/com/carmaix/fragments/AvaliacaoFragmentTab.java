package br.com.carmaix.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.carmaix.R;
import br.com.carmaix.adapters.AvaliacaoTabAdapter;


/**
 * Created by fernando on 23/05/16.
 */
public class AvaliacaoFragmentTab extends BaseFragment {
    private TabLayout tab;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avaliacao_fragment_tab,container,false);
        tab = (TabLayout)view.findViewById(R.id.avaliacaoTab);

        viewPager = (ViewPager)view.findViewById(R.id.avaliacaoViewPager);
        viewPager.setAdapter(new AvaliacaoTabAdapter(getContext(),getChildFragmentManager()));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    tab.setBackgroundColor(getActivity().getResources().getColor(R.color.tab_cinza));
                }else if(position == 1) {
                    tab.setBackgroundColor(getActivity().getResources().getColor(R.color.tab_vermelha));
                }else if(position == 2) {
                    tab.setBackgroundColor(getActivity().getResources().getColor(R.color.tab_laranja));
                }else if(position == 3) {
                    tab.setBackgroundColor(getActivity().getResources().getColor(R.color.tab_verde));
                }else if(position == 4) {
                    tab.setBackgroundColor(getActivity().getResources().getColor(R.color.tab_roxo));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tab.setSelectedTabIndicatorColor(Color.WHITE);
        tab.setTabTextColors(Color.WHITE, Color.WHITE);
        tab.setBackgroundColor(getActivity().getResources().getColor(R.color.tab_cinza));
        tab.setupWithViewPager(viewPager);

        return view;
    }

}
