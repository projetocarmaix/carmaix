package br.com.carmaix.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliarActivity;
import br.com.carmaix.services.AnoFabricacaoReturn;
import br.com.carmaix.services.AnoModeloReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.CategoriaReturn;
import br.com.carmaix.services.CidadesReturn;
import br.com.carmaix.services.CombustiveisReturn;
import br.com.carmaix.services.InformacoesAvaliacaoReturn;
import br.com.carmaix.services.MarcasCategoriaReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.services.UserReturn;
import br.com.carmaix.services.VendedorReturn;
import br.com.carmaix.spinnerStaticValues.AcessorioReturn;
import br.com.carmaix.spinnerStaticValues.ClassificacaoReturn;
import br.com.carmaix.spinnerStaticValues.MotivoAvaliacaoReturn;
import br.com.carmaix.spinnerStaticValues.NotaReturn;
import br.com.carmaix.spinnerStaticValues.PortasReturn;
import br.com.carmaix.spinnerStaticValues.SpinnerStaticValues;
import br.com.carmaix.spinnerStaticValues.UfReturn;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;
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
    ArrayList<ValueLabelDefault> ufReturns = null;
    ArrayList<ValueLabelDefault> marcasCategoriaReturns = null;
    ArrayList<ValueLabelDefault> modelosMarcaReturns = null;
    ArrayList<ValueLabelDefault> anoFabricacaoReturns = null;
    private ArrayList<ValueLabelDefault> anoModeloReturns;
    private ArrayList<ValueLabelDefault> cidadesReturns;
    private UserReturn userReturn;

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

    private EditText editTextDataHora;
    private EditText editTextAvaliador;
    private EditText editTextEmpresa;
    private EditText editTextCliente;
    private EditText editTextTelefone;
    private EditText editTextPlaca;
    private EditText editTextChassi;
    private EditText editTextRenavam;
    private EditText editTextSituacao;
    private EditText editTextCor;
    private EditText editTextKm;

    private RadioButton radioButtonAtacado;
    private RadioButton radioButtonVarejo;

    private RadioButton radioButtonGarantiaSim;
    private RadioButton radioButtonGarantiaNao;

    private Boolean firstLoadMarcas = true;
    private Boolean firstLoadModelos = true;
    private Boolean firstLoadAnoFabricacao = true;
    private Boolean firstLoadAnoModelo = true;
    private Boolean firstLoadCidades = true;

    private InformacoesAvaliacaoReturn informacoesAvaliacaoReturn = null;
    private ArrayAdapter vendedorSpinnerAdapter;
    private ArrayAdapter categoriaSpinnerAdapter;
    private ArrayAdapter marcasCategoriaSpinnerAdapter;
    private ArrayAdapter modelosMarcaSpinnerAdapter;
    private ArrayAdapter anoFabricacaoSpinnerAdapter;
    private ArrayAdapter anoModeloSpinnerAdapter;
    private ArrayAdapter ufSpinnerAdapter;
    private ArrayAdapter cidadesSpinnerAdapter;
    private ArrayAdapter acessoriosSpinnerAdapter;
    private ArrayAdapter combustivelSpinnerAdapter;
    private ArrayAdapter portasSpinnerAdapter;
    private ArrayAdapter motivoAvaliacaoSpinnerAdapter;
    private ArrayAdapter classificacaoSpinnerAdapter;
    private ArrayAdapter notasSpinnerAdapter;



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
        spinnerUf = (Spinner)view.findViewById(R.id.spinner_uf);
        spinnerMarcasCategoria = (Spinner)view.findViewById(R.id.spinner_marca);
        spinnerModelosMarca = (Spinner)view.findViewById(R.id.spinner_modelo);
        spinnerAnoFabricacao = (Spinner)view.findViewById(R.id.spinner_ano_fabricacao);
        spinnerAnoModelo = (Spinner)view.findViewById(R.id.spinner_ano_modelo);
        spinnerCidades = (Spinner)view.findViewById(R.id.spinner_cidade);

        editTextDataHora = (EditText)view.findViewById(R.id.edit_text_data_e_hora);
        editTextAvaliador = (EditText)view.findViewById(R.id.edit_text_avaliador);
        editTextEmpresa     = (EditText)view.findViewById(R.id.edit_text_empresa);
        editTextCliente  = (EditText)view.findViewById(R.id.edit_text_cliente);
        editTextTelefone = (EditText)view.findViewById(R.id.edit_text_telefone);
        editTextPlaca    = (EditText)view.findViewById(R.id.edit_text_placa);
        editTextChassi   = (EditText)view.findViewById(R.id.edit_text_chassi);
        editTextRenavam  = (EditText)view.findViewById(R.id.edit_text_renavam);
        editTextSituacao = (EditText)view.findViewById(R.id.edit_text_situacao);
        editTextCor     = (EditText)view.findViewById(R.id.edit_text_cor);
        editTextKm      = (EditText)view.findViewById(R.id.edit_text_km);


        radioButtonAtacado = (RadioButton)view.findViewById(R.id.radio_atacado);
        radioButtonVarejo= (RadioButton)view.findViewById(R.id.radio_varejo);
        radioButtonGarantiaSim= (RadioButton)view.findViewById(R.id.radio_garantia_de_fabrica_sim);
        radioButtonGarantiaNao= (RadioButton)view.findViewById(R.id.radio_garantia_de_fabrica_nao);

        runBackground(fragmentActivity.getResources().getString(R.string.carregando),true,true, Constants.ACTION_LIST);
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
                classificacaoReturns = CallService.listClassificacoes(fragmentActivity);
                acessoriosReturns = SpinnerStaticValues.listAcessorios(fragmentActivity);
                motivoAvaliacaoReturns = CallService.listMotivoAvaliacao(fragmentActivity);
                notasReturns = SpinnerStaticValues.listNota(fragmentActivity);
                ufReturns = SpinnerStaticValues.listUf(fragmentActivity);

                informacoesAvaliacaoReturn = ((AvaliarActivity)fragmentActivity).getInformacoesAvaliacaoReturn();


                cidadesReturns = CallService.listCidades(fragmentActivity,informacoesAvaliacaoReturn.getCliente().getEstado_id());
                marcasCategoriaReturns = CallService.listMarcasCategoria(fragmentActivity, informacoesAvaliacaoReturn.getVeiculo().getCategoria_id());
                modelosMarcaReturns= CallService.listModelosMarca(fragmentActivity, informacoesAvaliacaoReturn.getVeiculo().getMarca_id());
                anoFabricacaoReturns= CallService.listAnoFabricacao(fragmentActivity, informacoesAvaliacaoReturn.getVeiculo().getModelo_id());
                anoModeloReturns = CallService.listAnoModelo(fragmentActivity, informacoesAvaliacaoReturn.getVeiculo().getModelo_id(),informacoesAvaliacaoReturn.getVeiculo().getAno_fabricacao());

                userReturn = CallService.getAvaliador(fragmentActivity,informacoesAvaliacaoReturn.getAvaliador_id());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.backgroundMethod(action);
    }

    @Override
    public void onResume() {
        super.onResume();
        firstLoadMarcas = true;
        firstLoadModelos = true;
        firstLoadAnoFabricacao = true;
        firstLoadAnoModelo = true;
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {
            vendedorSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,vendedorReturns);
            spinnerVendedor.setAdapter(vendedorSpinnerAdapter);

            categoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,categoriaReturns);
            spinnerCategoria.setAdapter(categoriaSpinnerAdapter);
            setDefaultValuesToSpinners(true,false,false,false);
            spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(!firstLoadMarcas) {
                        loadMarcas();
                    }else {
                        firstLoadMarcas = false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });

            marcasCategoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,marcasCategoriaReturns);
            spinnerMarcasCategoria.setAdapter(marcasCategoriaSpinnerAdapter);

            modelosMarcaSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,modelosMarcaReturns);
            spinnerModelosMarca.setAdapter(modelosMarcaSpinnerAdapter);

            anoFabricacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,anoFabricacaoReturns);
            spinnerAnoFabricacao.setAdapter(anoFabricacaoSpinnerAdapter);

            anoModeloSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,anoModeloReturns);
            spinnerAnoModelo.setAdapter(anoModeloSpinnerAdapter);

            combustivelSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,combustiveisReturns);
            spinnerCombustivel.setAdapter(combustivelSpinnerAdapter);

            portasSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,portasReturns);
            spinnerPortas.setAdapter(portasSpinnerAdapter);

            classificacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,classificacaoReturns);
            spinnerClassificacao.setAdapter(classificacaoSpinnerAdapter);

            acessoriosSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,acessoriosReturns);
            spinnerAcessorios.setAdapter(acessoriosSpinnerAdapter);

            motivoAvaliacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,motivoAvaliacaoReturns);
            spinnerMotivoAvaliacao.setAdapter(motivoAvaliacaoSpinnerAdapter);

            notasSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,notasReturns);
            spinnerNotas.setAdapter(notasSpinnerAdapter);

            ufSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,ufReturns);
            spinnerUf.setAdapter(ufSpinnerAdapter);

            cidadesSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,cidadesReturns);
            spinnerCidades.setAdapter(cidadesSpinnerAdapter);

            spinnerUf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!firstLoadCidades) {
                        UfReturn ufReturn = (UfReturn) spinnerUf.getSelectedItem();
                        final String uf = ufReturn.getId();
                        AsyncTask asyncTaskCidades = new AsyncTask() {
                            @Override
                            protected Boolean doInBackground(Object[] params) {
                                try {
                                    cidadesReturns = CallService.listCidades(fragmentActivity, uf);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }

                            @Override
                            protected void onPostExecute(Object o) {
                                cidadesSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, cidadesReturns);
                                spinnerCidades.setAdapter(cidadesSpinnerAdapter);
                            }
                        };

                        asyncTaskCidades.execute();
                    } else {
                        firstLoadCidades = false;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}

            });
        }
        loadValues();

        super.onEndBackgroundRun(action);
    }

    public Spinner getSpinnerModelosMarca() {
        return spinnerModelosMarca;
    }

    public Spinner getSpinnerCombustivel() {
        return spinnerCombustivel;
    }

    public Spinner getSpinnerAnoModelo() {
        return spinnerAnoModelo;
    }

    private void loadValues() {

        for(int i = 0; i < spinnerVendedor.getCount(); i++) {
            VendedorReturn vendedorReturn = (VendedorReturn)vendedorSpinnerAdapter.getItem(i);
            if(vendedorReturn != null && (vendedorReturn.getId()).equals(informacoesAvaliacaoReturn.getVendedor_id())) {
                    spinnerVendedor.setSelection(i);
                    break;
            }
        }

        for(int i = 0; i < spinnerCategoria.getCount(); i++) {
            CategoriaReturn categoriaReturn = (CategoriaReturn)categoriaSpinnerAdapter.getItem(i);
            if(categoriaReturn != null && (categoriaReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getCategoria_id())) {
                spinnerCategoria.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerMarcasCategoria.getCount(); i++) {
            MarcasCategoriaReturn marcasCategoriaReturn = (MarcasCategoriaReturn)marcasCategoriaSpinnerAdapter.getItem(i);
            if(marcasCategoriaReturn != null && (marcasCategoriaReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getMarca_id())) {
                spinnerMarcasCategoria.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerMarcasCategoria.getCount(); i++) {
            MarcasCategoriaReturn marcasCategoriaReturn = (MarcasCategoriaReturn)marcasCategoriaSpinnerAdapter.getItem(i);
            if(marcasCategoriaReturn != null && (marcasCategoriaReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getMarca_id())) {
                spinnerMarcasCategoria.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerModelosMarca.getCount(); i++) {
            ModelosMarcaReturn modelosMarcaReturn = (ModelosMarcaReturn)modelosMarcaSpinnerAdapter.getItem(i);
            if(modelosMarcaReturn != null && (modelosMarcaReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getModelo_id())) {
                spinnerModelosMarca.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerAnoFabricacao.getCount(); i++) {
            AnoFabricacaoReturn anoFabricacaoReturn = (AnoFabricacaoReturn)anoFabricacaoSpinnerAdapter.getItem(i);
            if(anoFabricacaoReturn != null && (anoFabricacaoReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getAno_fabricacao())) {
                spinnerAnoFabricacao.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerAnoModelo.getCount(); i++) {
            AnoModeloReturn anoModeloReturn = (AnoModeloReturn)anoModeloSpinnerAdapter.getItem(i);
            if(anoModeloReturn != null && ((anoModeloReturn.getId()).split("-")[0]).equals(informacoesAvaliacaoReturn.getVeiculo().getAno_modelo())) {
                spinnerAnoModelo.setSelection(i);
                break;
            }
        }


        for(int i = 0; i < spinnerUf.getCount(); i++) {
            UfReturn ufReturn = (UfReturn)ufSpinnerAdapter.getItem(i);
            if(ufReturn != null && (ufReturn.getId()).equals(informacoesAvaliacaoReturn.getCliente().getEstado_id())) {
                spinnerUf.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerCidades.getCount(); i++) {
            CidadesReturn cidadesReturn = (CidadesReturn)cidadesSpinnerAdapter.getItem(i);
            if(cidadesReturn != null && (cidadesReturn.getId()).equals(informacoesAvaliacaoReturn.getCliente().getCidade_id())) {
                spinnerCidades.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerAcessorios.getCount(); i++) {
            AcessorioReturn acessorioReturn = (AcessorioReturn)acessoriosSpinnerAdapter.getItem(i);
            if(acessorioReturn != null && (acessorioReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getAcessorio())) {
                spinnerAcessorios.setSelection(i);
                break;
            }
        }


        for(int i = 0; i < spinnerCombustivel.getCount(); i++) {
            CombustiveisReturn combustiveisReturn = (CombustiveisReturn)combustivelSpinnerAdapter.getItem(i);
            if(combustiveisReturn != null && (combustiveisReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getCombustivel_id())) {
                spinnerCombustivel.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerPortas.getCount(); i++) {
            PortasReturn portasReturn = (PortasReturn)portasSpinnerAdapter.getItem(i);
            if(portasReturn != null && (portasReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getPortas())) {
                spinnerPortas.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerMotivoAvaliacao.getCount(); i++) {
            MotivoAvaliacaoReturn motivoAvaliacaoReturn = (MotivoAvaliacaoReturn)motivoAvaliacaoSpinnerAdapter.getItem(i);
            if(motivoAvaliacaoReturn != null && (motivoAvaliacaoReturn.getId()).equals(informacoesAvaliacaoReturn.getMotivo_avaliacao())) {
                spinnerMotivoAvaliacao.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerClassificacao.getCount(); i++) {
            ClassificacaoReturn classificacaoReturn = (ClassificacaoReturn)classificacaoSpinnerAdapter.getItem(i);
            if(classificacaoReturn != null && (classificacaoReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getClassificacao())) {
                spinnerClassificacao.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < spinnerNotas.getCount(); i++) {
            NotaReturn notaReturn = (NotaReturn)notasSpinnerAdapter.getItem(i);
            if(notaReturn != null && (notaReturn.getId()).equals(informacoesAvaliacaoReturn.getVeiculo().getNota())) {
                spinnerNotas.setSelection(i);
                break;
            }
        }


        editTextDataHora.setText(informacoesAvaliacaoReturn.getData());
        if(userReturn != null) {
            editTextAvaliador.setText(userReturn.getNome());
        }
        editTextEmpresa.setText(informacoesAvaliacaoReturn.getEmpresa());
        editTextCliente.setText(informacoesAvaliacaoReturn.getCliente().getNome());
        editTextTelefone.setText(informacoesAvaliacaoReturn.getCliente().getTelefone());
        editTextPlaca.setText(informacoesAvaliacaoReturn.getVeiculo().getPlaca());
        editTextChassi.setText(informacoesAvaliacaoReturn.getVeiculo().getChassi());
        editTextRenavam.setText(informacoesAvaliacaoReturn.getVeiculo().getRenavam());
        editTextSituacao.setText(informacoesAvaliacaoReturn.getSituacao());
        editTextCor.setText(informacoesAvaliacaoReturn.getVeiculo().getCor());
        editTextKm.setText(informacoesAvaliacaoReturn.getVeiculo().getKm());

        String tipoCompra = informacoesAvaliacaoReturn.getTipo_compra();
        if(tipoCompra.equals(Constants.OPTION_ATACADO)){
            radioButtonAtacado.setChecked(true);
        }else if(tipoCompra.equals(Constants.OPTION_VAREJO)){
            radioButtonVarejo.setChecked(true);
        }

        String garantia = informacoesAvaliacaoReturn.getVeiculo().getGarantia_fabrica();
        if(garantia.equals(Constants.OPTION_GARANTIA_SIM)){
            radioButtonGarantiaSim.setChecked(true);
        }else if(garantia.equals(Constants.OPTION_GARANTIA_NAO)){
            radioButtonGarantiaNao.setChecked(true);
        }
    }


    private void loadMarcas() {
        final String idCategoria = ((CategoriaReturn)spinnerCategoria.getSelectedItem()).getId();
        AsyncTask asyncTaskMarcas = new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    marcasCategoriaReturns = CallService.listMarcasCategoria(fragmentActivity, idCategoria);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                marcasCategoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,marcasCategoriaReturns);
                spinnerMarcasCategoria.setAdapter(marcasCategoriaSpinnerAdapter);
                setDefaultValuesToSpinners(false,true,false,false);
                spinnerMarcasCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        loadModelos();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
            }
        }.execute();
    }


    private void loadModelos() {
        MarcasCategoriaReturn m = (MarcasCategoriaReturn)spinnerMarcasCategoria.getSelectedItem();
        final String idMarca = m.getId();
        AsyncTask asyncTaskModelos = new AsyncTask() {
            @Override
            protected Boolean doInBackground(Object[] params) {
                try {
                    if (idMarca.isEmpty()) {return false;}

                    modelosMarcaReturns = CallService.listModelosMarca(fragmentActivity, idMarca);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(((Boolean)o)== true) {
                    modelosMarcaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, modelosMarcaReturns);
                    spinnerModelosMarca.setAdapter(modelosMarcaSpinnerAdapter);
                    setDefaultValuesToSpinners(false,false,true,false);
                    spinnerModelosMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            loadAnoFabricacao();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {}
                    });
                }
            }
        }.execute();
    }

    private void loadAnoFabricacao() {
        AsyncTask asyncTaskAnoFabricacao = new AsyncTask() {

            ModelosMarcaReturn m = (ModelosMarcaReturn) spinnerModelosMarca.getSelectedItem();
            final String idModelo = m.getId();

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    if (idModelo.isEmpty()) {
                        return false;
                    }
                    anoFabricacaoReturns = CallService.listAnoFabricacao(fragmentActivity, idModelo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            protected void onPostExecute(Object o) {
                if (((Boolean) o) == true) {
                    anoFabricacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoFabricacaoReturns);
                    spinnerAnoFabricacao.setAdapter(anoFabricacaoSpinnerAdapter);
                    setDefaultValuesToSpinners(false,false,false,true);
                    spinnerAnoFabricacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            loadAnoModelo();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {}
                    });
                }
            }
        }.execute();
    }

    private void loadAnoModelo() {
        AnoFabricacaoReturn a = (AnoFabricacaoReturn) spinnerAnoFabricacao.getSelectedItem();
        final String ano = a.getId();

        ModelosMarcaReturn m = (ModelosMarcaReturn) spinnerModelosMarca.getSelectedItem();
        final String idModelo = m.getId();

        AsyncTask asyncTaskAnoModelo = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    if(idModelo.isEmpty() || ano.isEmpty()) {return false;}
                    anoModeloReturns = CallService.listAnoModelo(fragmentActivity, idModelo, ano);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(((Boolean)o) == true) {
                    anoModeloSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoModeloReturns);
                    spinnerAnoModelo.setAdapter(anoModeloSpinnerAdapter);
                }
            }
        }.execute();
    }

    public void setDefaultValuesToSpinners(Boolean categoria, Boolean marca,Boolean modelo, Boolean anoFabricacao) {
        if(categoria) {
            ArrayList<ValueLabelDefault> marcasCategoriaDefault = Utils.createArrayDefault(new MarcasCategoriaReturn(fragmentActivity));
            ArrayAdapter marcasCategoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, marcasCategoriaDefault);
            spinnerMarcasCategoria.setAdapter(marcasCategoriaSpinnerAdapter);
        }

        if (categoria || marca) {
            ArrayList<ValueLabelDefault> modelosMarcaDefault = Utils.createArrayDefault(new ModelosMarcaReturn(fragmentActivity));
            ArrayAdapter modelosMarcaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, modelosMarcaDefault);
            spinnerModelosMarca.setAdapter(modelosMarcaSpinnerAdapter);
        }

        if (categoria || marca || modelo) {
            ArrayList<ValueLabelDefault> anoFabricacaoDefault = Utils.createArrayDefault(new AnoFabricacaoReturn(fragmentActivity));
            anoFabricacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoFabricacaoDefault);
            spinnerAnoFabricacao.setAdapter(anoFabricacaoSpinnerAdapter);
        }
        if (categoria || marca || modelo || anoFabricacao) {
            ArrayList<ValueLabelDefault> anoModeloDefault = Utils.createArrayDefault(new AnoModeloReturn((fragmentActivity)));
            anoModeloSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoModeloDefault);
            spinnerAnoModelo.setAdapter(anoModeloSpinnerAdapter);
        }
    }
}

