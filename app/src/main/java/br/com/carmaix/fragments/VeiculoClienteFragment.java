package br.com.carmaix.fragments;

import android.content.Context;
import android.os.AsyncTask;
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
import br.com.carmaix.services.AnoFabricacaoReturn;
import br.com.carmaix.services.AnoModeloReturn;
import br.com.carmaix.services.CategoriaReturn;
import br.com.carmaix.services.MarcasCategoriaReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.spinnerStaticValues.SpinnerStaticValues;
import br.com.carmaix.spinnerStaticValues.UfReturn;
import br.com.carmaix.utils.BinderSpinner;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.VendedorReturn;
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
                classificacaoReturns = CallService.listClassificacoes(fragmentActivity);
                acessoriosReturns = SpinnerStaticValues.listAcessorios(fragmentActivity);
                motivoAvaliacaoReturns = CallService.listMotivoAvaliacao(fragmentActivity);
                notasReturns = SpinnerStaticValues.listNota(fragmentActivity);
                ufReturns = SpinnerStaticValues.listUf(fragmentActivity);
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
            ArrayAdapter vendedorSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,vendedorReturns);
            spinnerVendedor.setAdapter(vendedorSpinnerAdapter);
            spinnerVendedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            ArrayAdapter categoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,categoriaReturns);
            spinnerCategoria.setAdapter(categoriaSpinnerAdapter);
            setDefaultValuesToSpinners(true,false,false,false);
            spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    if(!firstLoadMarcas) {
                        CategoriaReturn c = (CategoriaReturn)spinnerCategoria.getSelectedItem();
                        final String idCategoria = c.getId();
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
                                ArrayAdapter marcasCategoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, marcasCategoriaReturns);
                                spinnerMarcasCategoria.setAdapter(marcasCategoriaSpinnerAdapter);
                                setDefaultValuesToSpinners(false,true,false,false);

                                spinnerMarcasCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if(!firstLoadModelos) {
                                            MarcasCategoriaReturn m = (MarcasCategoriaReturn)spinnerMarcasCategoria.getSelectedItem();
                                            final String idMarca = m.getId();
                                            AsyncTask asyncTaskModelos = new AsyncTask() {
                                                @Override
                                                protected Boolean doInBackground(Object[] params) {
                                                    try {
                                                        if(idMarca.isEmpty()) {return false;}
                                                        modelosMarcaReturns = CallService.listModelosMarca(fragmentActivity, idMarca);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    return true;
                                                }

                                                @Override
                                                protected void onPostExecute(Object o) {
                                                    if(((Boolean)o)== true) {
                                                        ArrayAdapter modelosMarcaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, modelosMarcaReturns);
                                                        spinnerModelosMarca.setAdapter(modelosMarcaSpinnerAdapter);
                                                        setDefaultValuesToSpinners(false,false,true,false);
                                                        spinnerModelosMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                if (!firstLoadAnoFabricacao) {
                                                                    ModelosMarcaReturn m = (ModelosMarcaReturn) spinnerModelosMarca.getSelectedItem();
                                                                    final String idModelo = m.getId();
                                                                    AsyncTask asyncTaskAnoFabricacao = new AsyncTask() {
                                                                        @Override
                                                                        protected Object doInBackground(Object[] objects) {
                                                                            try {
                                                                                if (idModelo.isEmpty()) {return false;}
                                                                                anoFabricacaoReturns = CallService.listAnoFabricacao(fragmentActivity, idModelo);
                                                                            } catch (Exception e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            return true;
                                                                        }

                                                                        @Override
                                                                        protected void onPostExecute(Object o) {
                                                                            if(((Boolean)o)==true) {
                                                                                ArrayAdapter anoFabricacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoFabricacaoReturns);
                                                                                spinnerAnoFabricacao.setAdapter(anoFabricacaoSpinnerAdapter);
                                                                                setDefaultValuesToSpinners(false,false,false,true);
                                                                                spinnerAnoFabricacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                    @Override
                                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                                                        if (!firstLoadAnoModelo) {
                                                                                            AnoFabricacaoReturn a = (AnoFabricacaoReturn) spinnerAnoFabricacao.getSelectedItem();
                                                                                            final String ano = a.getId();
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
                                                                                                        ArrayAdapter anoModeloSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoModeloReturns);
                                                                                                        spinnerAnoModelo.setAdapter(anoModeloSpinnerAdapter);
                                                                                                    }
                                                                                                }
                                                                                            };
                                                                                            asyncTaskAnoModelo.execute();
                                                                                        } else {
                                                                                            firstLoadAnoModelo = false;
                                                                                        }
                                                                                    }

                                                                                    @Override
                                                                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                                                                    }
                                                                                });
                                                                            }
                                                                        }
                                                                    };
                                                                    asyncTaskAnoFabricacao.execute();

                                                                } else {
                                                                    firstLoadAnoFabricacao = false;
                                                                }
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> adapterView) {
                                                            }
                                                        });
                                                    }
                                                }
                                            };
                                            asyncTaskModelos.execute();
                                        }else {
                                            firstLoadModelos = false;
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {}
                                });
                            }
                        };

                        asyncTaskMarcas.execute();
                    }else {
                        firstLoadMarcas = false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });

            ArrayAdapter combustivelSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,combustiveisReturns);
            spinnerCombustivel.setAdapter(combustivelSpinnerAdapter);

            ArrayAdapter portasSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,portasReturns);
            spinnerPortas.setAdapter(portasSpinnerAdapter);

            ArrayAdapter classificacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,classificacaoReturns);
            spinnerClassificacao.setAdapter(classificacaoSpinnerAdapter);

            ArrayAdapter acessoriosSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,acessoriosReturns);
            spinnerAcessorios.setAdapter(acessoriosSpinnerAdapter);

            ArrayAdapter motivoAvaliacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,motivoAvaliacaoReturns);
            spinnerMotivoAvaliacao.setAdapter(motivoAvaliacaoSpinnerAdapter);

            ArrayAdapter notasSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,notasReturns);
            spinnerNotas.setAdapter(notasSpinnerAdapter);

            ArrayAdapter ufSpinnerAdapter = new ArrayAdapter(fragmentActivity,R.layout.spinner_item,ufReturns);
            spinnerUf.setAdapter(ufSpinnerAdapter);

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
                                ArrayAdapter cidadesSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, cidadesReturns);
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
        super.onEndBackgroundRun(action);
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
            ArrayAdapter anoFabricacaoSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoFabricacaoDefault);
            spinnerAnoFabricacao.setAdapter(anoFabricacaoSpinnerAdapter);
        }
        if (categoria || marca || modelo || anoFabricacao) {
            ArrayList<ValueLabelDefault> anoModeloDefault = Utils.createArrayDefault(new AnoModeloReturn((fragmentActivity)));
            ArrayAdapter anoModeloSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoModeloDefault);
            spinnerAnoModelo.setAdapter(anoModeloSpinnerAdapter);
        }
    }
}
