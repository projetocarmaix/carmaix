package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
        tab.setupWithViewPager(viewPager);

        return view;
    }

}
