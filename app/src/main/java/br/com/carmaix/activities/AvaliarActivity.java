package br.com.carmaix.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;
import br.com.carmaix.fragments.AvaliarFragmentTab;
import br.com.carmaix.fragments.VeiculoClienteFragment;
import br.com.carmaix.services.AnoModeloReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.CombustiveisReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 07/07/16.
 */
public class AvaliarActivity extends BaseActivityHomeAsUp{
    private String avaliacaoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            avaliacaoId = extras.getString("avaliacaoId");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_home_as_up, new AvaliarFragmentTab()).commit();
        runBackground("",false,true, Constants.ACTION_LIST);
    }

    public String getAvaliacaoId() {
        return avaliacaoId;
    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {
        if(action == Constants.ACTION_LIST) {
            CallService.listInformacaoAvaliacao(this,avaliacaoId);
        }

        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {

        }
        super.onEndBackgroundRun(action);
    }

    public HashMap<String,String> getEstatisticaParams() {

        AvaliarFragmentTab avaliarFragmentTab = (AvaliarFragmentTab)getSupportFragmentManager().getFragments().get(0);
        List<Fragment> fragmentList = avaliarFragmentTab.getChildFragmentManager().getFragments();
        HashMap<String,String> values = new HashMap<>();

        Spinner spinnerAnoModelo = null;
        Spinner spinnerCombustivel = null;
        Spinner spinnerModelosMarca = null;
        String idModelo = null;
        String idCombustivel = null;
        String idAno = null;

        for(Fragment fragment : fragmentList) {
            if(fragment instanceof VeiculoClienteFragment) {
                spinnerAnoModelo  = ((VeiculoClienteFragment)fragment).getSpinnerAnoModelo();
                spinnerCombustivel = ((VeiculoClienteFragment)fragment).getSpinnerCombustivel();
                spinnerModelosMarca =((VeiculoClienteFragment)fragment).getSpinnerModelosMarca();
                break;
            }
        }
        if(spinnerModelosMarca != null && spinnerCombustivel != null && spinnerAnoModelo != null) {

            ModelosMarcaReturn modelo = (ModelosMarcaReturn) spinnerModelosMarca.getSelectedItem();
            if(modelo != null) {
                idModelo = modelo.getId();
            }

            CombustiveisReturn combustivel = (CombustiveisReturn) spinnerCombustivel.getSelectedItem();
            if(combustivel != null) {
                idCombustivel = combustivel.getId();
            }
            AnoModeloReturn ano = (AnoModeloReturn) spinnerAnoModelo.getSelectedItem();
            if(ano != null) {
                idAno = ano.getId();
            }

            values.put("modelo",idModelo);
            values.put("combustivel",idCombustivel);
            values.put("ano",idAno);

        }

        return values;

    }
}
