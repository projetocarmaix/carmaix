package br.com.carmaix.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;
import br.com.carmaix.services.AnoFabricacaoReturn;
import br.com.carmaix.services.AnoModeloReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.CategoriaReturn;
import br.com.carmaix.services.MarcasCategoriaReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoActivity extends BaseActivity {

    private SearchView searchView;
    private AvaliacaoFragmentTab avaliacaoFragmentTab = new AvaliacaoFragmentTab();
    private int mStackLevel =0 ;
    private Spinner spinnerMarca;
    private Spinner spinnerModelo;
    private Spinner spinnerAno;
    ArrayList<ValueLabelDefault> marcasCategoriaReturns = null;
    ArrayList<ValueLabelDefault> modelosMarcaReturns = null;
    ArrayList<ValueLabelDefault> anoFabricacaoReturns = null;
    private ArrayList<ValueLabelDefault> anoModeloReturns;

    private Spinner spinnerVendedor;
    private Spinner spinnerCategoria;
    private Spinner spinnerCombustivel;
    private Spinner spinnerPortas;
    private Spinner spinnerClassificacao;
    private Spinner spinnerAcessorios;
    private Spinner spinnerMotivoAvaliacao;
    private Spinner spinnerNotas;
    private Spinner spinnerUf;
    private Spinner spinnerMarcasCategoria;
    private Spinner spinnerModelosMarca;
    private Spinner spinnerAnoFabricacao;
    private Spinner spinnerAnoModelo;
    private Spinner spinnerCidades;

    private Boolean firstLoadMarcas = true;
    private Boolean firstLoadModelos = true;
    private Boolean firstLoadAnoFabricacao = true;
    private Boolean firstLoadAnoModelo = true;
    private Boolean firstLoadCidades = true;


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
        }else if(id == R.id.menu_tabela_passecarros) {
            Intent intent = new Intent(this, DialogTabelaPassecarrosActivity.class);
            this.startActivity(intent);
        }
        super.onNavigationItemSelected(item);
        return true;
    }

    public void setItemMenuSelected(int index) {
        getNavigationView().getMenu().getItem(index).setChecked(true);
    }
}