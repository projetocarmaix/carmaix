package br.com.carmaix.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliacaoVisualizarActivity;
import br.com.carmaix.adapters.AvaliacaoAdapter;
import br.com.carmaix.domain.Avaliacao;
import br.com.carmaix.domain.AvaliacaoService;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoFragment extends BaseFragment {
    private LinearLayoutManager linearLayoutManager;
    private List<Avaliacao> avaliacaoList;
    private RecyclerView recyclerView;
    private String tipo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avaliacao_fragment,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.avaliacaoRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
        registerForContextMenu(recyclerView);
        if(this.getArguments() != null) {
            tipo = (String)this.getArguments().get("tipo");
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskAvaliacao();
    }


    private void taskAvaliacao() {
        this.avaliacaoList = AvaliacaoService.returnListAvaliacao(tipo);
        recyclerView.setAdapter(new AvaliacaoAdapter(getContext(),this.avaliacaoList));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent i = new Intent(getContext(),AvaliacaoVisualizarActivity.class);
        startActivity(i);
        return true;
    }
}
