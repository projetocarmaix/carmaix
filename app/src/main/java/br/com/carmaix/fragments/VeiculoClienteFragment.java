package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.spinnerStaticValues.SpinnerStaticValues;
import br.com.carmaix.utils.BinderSpinner;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.VendedorReturn;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 12/07/16.
 */
public class VeiculoClienteFragment extends BaseFragment {
    ArrayList<ValueLabelDefault> vendedorReturns = null;
    ArrayList<ValueLabelDefault> categoriaReturns = null;
    ArrayList<ValueLabelDefault> combustiveisReturns = null;
    ArrayList<ValueLabelDefault> portasReturns = null;
    ArrayList<ValueLabelDefault> classificacaoReturns = null;
    ArrayList<ValueLabelDefault> acessoriosReturns = null;
    ArrayList<ValueLabelDefault> motivoAvaliacaoReturns = null;
    ArrayList<ValueLabelDefault> notasReturns = null;

    private Spinner spinnerVendedor;
    private Spinner spinnerCategoria;
    private Spinner spinnerCombustivel;
    private Spinner spinnerPortas;
    private Spinner spinnerClassificacao;
    private Spinner spinnerAcessorios;
    private Spinner spinnerMotivoAvaliacao;
    private Spinner spinnerNotas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.veiculo_cliente_fragment, container, false);
        spinnerVendedor = (Spinner)view.findViewById(R.id.spinner_vendedor);
        spinnerCategoria = (Spinner)view.findViewById(R.id.spinner_categoria);
        spinnerCombustivel = (Spinner)view.findViewById(R.id.spinner_combustivel);
        spinnerPortas = (Spinner)view.findViewById(R.id.spinner_portas);
        spinnerClassificacao= (Spinner)view.findViewById(R.id.spinner_classificacao);
        spinnerAcessorios = (Spinner)view.findViewById(R.id.spinner_acessorio);
        spinnerMotivoAvaliacao = (Spinner)view.findViewById(R.id.spinner_motivo_avaliacao);
        spinnerNotas = (Spinner)view.findViewById(R.id.spinner_nota);
        runBackground("",false,true, Constants.ACTION_LIST);
        return view;

    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {
        if(action == Constants.ACTION_LIST) {
            try {
                vendedorReturns = CallService.listVendedor(fragmentActivity);
                categoriaReturns = CallService.listCategorias(fragmentActivity);
                combustiveisReturns = CallService.listCombustiveis(fragmentActivity);
                portasReturns = SpinnerStaticValues.listPortas(fragmentActivity);
                classificacaoReturns = SpinnerStaticValues.listClassificacao(fragmentActivity);
                acessoriosReturns = SpinnerStaticValues.listAcessorios(fragmentActivity);
                motivoAvaliacaoReturns = SpinnerStaticValues.listMotivoAvaliacao(fragmentActivity);
                notasReturns = SpinnerStaticValues.listNota(fragmentActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {
            ArrayAdapter vendedorSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,vendedorReturns);
            spinnerVendedor.setAdapter(vendedorSpinnerAdapter);
            spinnerVendedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    VendedorReturn vs = (VendedorReturn)spinnerVendedor.getSelectedItem();
                    String label = vs.getId();
                    String value = vs.getDescricao();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            ArrayAdapter categoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,categoriaReturns);
            spinnerCategoria.setAdapter(categoriaSpinnerAdapter);

            ArrayAdapter combustivelSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,combustiveisReturns);
            spinnerCombustivel.setAdapter(combustivelSpinnerAdapter);

            ArrayAdapter portasSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,portasReturns);
            spinnerPortas.setAdapter(portasSpinnerAdapter);

            ArrayAdapter classificacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,classificacaoReturns);
            spinnerClassificacao.setAdapter(classificacaoSpinnerAdapter);

            ArrayAdapter acessoriosSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,acessoriosReturns);
            spinnerAcessorios.setAdapter(acessoriosSpinnerAdapter);

            ArrayAdapter motivoAvaliacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,motivoAvaliacaoReturns);
            spinnerMotivoAvaliacao.setAdapter(motivoAvaliacaoSpinnerAdapter);

            ArrayAdapter notasSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,notasReturns);
            spinnerNotas.setAdapter(notasSpinnerAdapter);

        }
        super.onEndBackgroundRun(action);
    }
}
