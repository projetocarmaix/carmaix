package br.com.carmaix.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.DialogTabelaPassecarrosActivity;
import br.com.carmaix.services.AnoFabricacaoReturn;
import br.com.carmaix.services.AnoModeloReturn;
import br.com.carmaix.services.AnosCombustivelReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.CategoriaReturn;
import br.com.carmaix.services.CombustiveisModelosReturn;
import br.com.carmaix.services.MarcasCategoriaReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.services.ValorMedioReturn;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 21/07/16.
 */
public class DialogTabelaPassecarrosFragment extends BaseFragment {
    ArrayList<ValueLabelDefault> categoriaReturns = null;
    ArrayList<ValueLabelDefault> marcaReturns = null;
    ArrayList<ValueLabelDefault> modeloReturns = null;
    private ArrayList<ValueLabelDefault> combustivelReturns = null;
    private ArrayList<ValueLabelDefault> anoReturns = null;

    private Spinner spinnerCategoria;
    private Spinner spinnerMarca;
    private Spinner spinnerModelo;
    private Spinner spinnerCombustivel;
    private Spinner spinnerAno;
    private View view;

    private ProgressBar progressbarCategoria;
    private ProgressBar progressbarMarca;
    private ProgressBar progressbarModelo;
    private ProgressBar progressbarCombustivel;
    private ProgressBar progressbarAno;
    private ProgressBar progressbarValorMedio;

    private Boolean firstLoadMarcas = true;
    private Boolean firstLoadModelos = true;
    private Boolean firstLoadCombustivel = true;
    private Boolean firstLoadAno= true;
    private Boolean firstLoadValorMedio= true;

    private ValorMedioReturn valorMedioReturn = null;

    private TextView textValorMedio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_tabela_passecarros,container,false);
        spinnerCategoria = (Spinner)view.findViewById(R.id.spinner_dialog_categoria);
        spinnerMarca = (Spinner)view.findViewById(R.id.spinner_dialog_marca);
        spinnerModelo = (Spinner)view.findViewById(R.id.spinner_dialog_modelo);
        spinnerCombustivel = (Spinner)view.findViewById(R.id.spinner_dialog_combustivel);
        spinnerAno = (Spinner)view.findViewById(R.id.spinner_dialog_ano);
        progressbarCategoria = (ProgressBar)view.findViewById(R.id.progressbar_categoria);
        progressbarMarca = (ProgressBar)view.findViewById(R.id.progressbar_marca);
        progressbarModelo = (ProgressBar)view.findViewById(R.id.progressbar_modelo);
        progressbarCombustivel = (ProgressBar)view.findViewById(R.id.progressbar_combustivel);
        progressbarAno = (ProgressBar)view.findViewById(R.id.progressbar_ano);
        textValorMedio = (TextView) view.findViewById(R.id.text_dialog_valor_medio);
        progressbarValorMedio = (ProgressBar) view.findViewById(R.id.progressbar_valormedio);


        runBackground("",false,true, Constants.ACTION_LIST);
        return view;
    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {
        if(action == Constants.ACTION_LIST) {
            showProgressBar(progressbarCategoria,spinnerCategoria);
            categoriaReturns = CallService.listCategorias(fragmentActivity);
            categoriaReturns.get(0).setDescricao(fragmentActivity.getResources().getString(R.string.dialog_passecarros_tipo));
        }
        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {
            ArrayAdapter categoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, categoriaReturns);
            spinnerCategoria.setAdapter(categoriaSpinnerAdapter);
            hideProgressBar(progressbarCategoria,spinnerCategoria);
            setDefaultValuesToSpinners(true,false,false,false,false);
            setLoadValuesToSpinners(true,false,false,false,false);
            spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(!firstLoadMarcas) {
                        CategoriaReturn c = (CategoriaReturn) spinnerCategoria.getSelectedItem();
                        final String idCategoria = c.getId();

                        AsyncTask asyncTaskMarcas = new AsyncTask<Object, Object, Object>() {
                            @Override
                            protected void onPreExecute() {
                                showProgressBar(progressbarMarca,spinnerMarca);
                            }

                            @Override
                            protected Object doInBackground(Object[] objects) {
                                try {
                                    marcaReturns = CallService.listMarcasCategoria(fragmentActivity, idCategoria);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Object o) {
                                ArrayAdapter marcasSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, marcaReturns);
                                spinnerMarca.setAdapter(marcasSpinnerAdapter);
                                setDefaultValuesToSpinners(false,true,false,false,false);
                                setLoadValuesToSpinners(false,true,false,false,false);
                                hideProgressBar(progressbarMarca,spinnerMarca);
                                spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if(!firstLoadModelos) {
                                            MarcasCategoriaReturn c = (MarcasCategoriaReturn) spinnerMarca.getSelectedItem();
                                            final String idMarca = c.getId();

                                            AsyncTask asyncTaskModelos = new AsyncTask<Object, Object, Object>() {
                                                @Override
                                                protected void onPreExecute() {
                                                    showProgressBar(progressbarModelo,spinnerModelo);
                                                }

                                                @Override
                                                protected Object doInBackground(Object[] objects) {
                                                    try {
                                                        modeloReturns = CallService.listModelosMarca(fragmentActivity, idMarca);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    return null;
                                                }

                                                @Override
                                                protected void onPostExecute(Object o) {
                                                    ArrayAdapter modelosSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, modeloReturns);
                                                    spinnerModelo.setAdapter(modelosSpinnerAdapter);
                                                    hideProgressBar(progressbarModelo,spinnerModelo);
                                                    setDefaultValuesToSpinners(false,false,true,false, false);
                                                    setLoadValuesToSpinners(false,false,true,false,false);
                                                    spinnerModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                            if(!firstLoadCombustivel) {
                                                                ModelosMarcaReturn c = (ModelosMarcaReturn) spinnerModelo.getSelectedItem();
                                                                final String idModelo = c.getId();

                                                                AsyncTask asyncTaskCombustivel = new AsyncTask<Object, Object, Object>() {
                                                                    @Override
                                                                    protected void onPreExecute() {
                                                                        showProgressBar(progressbarCombustivel,spinnerCombustivel);
                                                                    }

                                                                    @Override
                                                                    protected Object doInBackground(Object[] objects) {
                                                                        try {
                                                                            combustivelReturns = CallService.listCombustiveisModelos(fragmentActivity, idModelo);
                                                                        } catch (Exception e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        return null;
                                                                    }

                                                                    @Override
                                                                    protected void onPostExecute(Object o) {
                                                                        ArrayAdapter combustivelSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, combustivelReturns);
                                                                        spinnerCombustivel.setAdapter(combustivelSpinnerAdapter);
                                                                        hideProgressBar(progressbarCombustivel,spinnerCombustivel);
                                                                        setDefaultValuesToSpinners(false,false,false,true, false);
                                                                        setLoadValuesToSpinners(false,false,false,true,false);

                                                                        spinnerCombustivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                            @Override
                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                if(!firstLoadAno) {
                                                                                    CombustiveisModelosReturn c = (CombustiveisModelosReturn) spinnerCombustivel.getSelectedItem();
                                                                                    final String idCombustivel = c.getId();

                                                                                    AsyncTask asyncTaskAno = new AsyncTask<Object, Object, Object>() {
                                                                                        @Override
                                                                                        protected void onPreExecute() {
                                                                                            showProgressBar(progressbarAno,spinnerAno);
                                                                                        }

                                                                                        @Override
                                                                                        protected Object doInBackground(Object[] objects) {
                                                                                            try {
                                                                                                anoReturns = CallService.listAnoCombustivel(fragmentActivity, idModelo,idCombustivel);
                                                                                            } catch (Exception e) {
                                                                                                e.printStackTrace();
                                                                                            }
                                                                                            return null;
                                                                                        }

                                                                                        @Override
                                                                                        protected void onPostExecute(Object o) {
                                                                                            ArrayAdapter anoSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anoReturns);
                                                                                            spinnerAno.setAdapter(anoSpinnerAdapter);
                                                                                            hideProgressBar(progressbarAno,spinnerAno);
                                                                                            setLoadValuesToSpinners(false,false,false,false,true);
                                                                                            setDefaultValuesToSpinners(false,false,false,false,true);
                                                                                            spinnerAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                @Override
                                                                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                    if(!firstLoadValorMedio) {
                                                                                                        AnosCombustivelReturn c = (AnosCombustivelReturn) spinnerAno.getSelectedItem();
                                                                                                        final String idAno = c.getId();

                                                                                                        AsyncTask asyncTaskSend = new AsyncTask<Object, Object, Object>() {
                                                                                                            @Override
                                                                                                            protected void onPreExecute() {
                                                                                                                progressbarValorMedio.setVisibility(View.VISIBLE);
                                                                                                            }

                                                                                                            @Override
                                                                                                            protected Object doInBackground(Object[] objects) {
                                                                                                                try {
                                                                                                                    valorMedioReturn = CallService.getValorMedio(fragmentActivity, idModelo, idAno, idCombustivel);
                                                                                                                } catch (Exception e) {
                                                                                                                    e.printStackTrace();
                                                                                                                }
                                                                                                                return null;
                                                                                                            }

                                                                                                            @Override
                                                                                                            protected void onPostExecute(Object o) {
                                                                                                                progressbarValorMedio.setVisibility(View.GONE);
                                                                                                                textValorMedio.setVisibility(View.VISIBLE);
                                                                                                                textValorMedio.setText(String.format(fragmentActivity.getResources().getString(R.string.dialog_passecarros_valor_medio), valorMedioReturn.getMed()));
                                                                                                            }
                                                                                                        };

                                                                                                        asyncTaskSend.execute();
                                                                                                    }else {
                                                                                                        firstLoadValorMedio = false;
                                                                                                    }
                                                                                                }

                                                                                                @Override
                                                                                                public void onNothingSelected(AdapterView<?> adapterView) {}
                                                                                            });
                                                                                        }
                                                                                    };

                                                                                    asyncTaskAno.execute();
                                                                                }else {
                                                                                    firstLoadAno = false;
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onNothingSelected(AdapterView<?> adapterView) {}
                                                                        });

                                                                    }
                                                                };

                                                                asyncTaskCombustivel.execute();
                                                            }else {
                                                                firstLoadCombustivel = false;
                                                            }

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> adapterView) {}
                                                    });
                                                }
                                            };

                                            asyncTaskModelos.execute();
                                        }else {
                                            firstLoadModelos = false;
                                        }

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {}
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
        }
        super.onEndBackgroundRun(action);
    }

    public void setDefaultValuesToSpinners(Boolean categoria, Boolean marca,Boolean modelo,Boolean combustivel, Boolean ano) {
        if (categoria) {
            ArrayList<ValueLabelDefault> marcasCategoriaDefault = Utils.createArrayDefault(new MarcasCategoriaReturn(fragmentActivity));
            marcasCategoriaDefault.get(0).setDescricao(fragmentActivity.getResources().getString(R.string.dialog_passecarros_marca));
            ArrayAdapter marcasCategoriaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, marcasCategoriaDefault);
            spinnerMarca.setAdapter(marcasCategoriaSpinnerAdapter);
        }

        if (categoria || marca) {
            ArrayList<ValueLabelDefault> modelosMarcaDefault = Utils.createArrayDefault(new ModelosMarcaReturn(fragmentActivity));
            modelosMarcaDefault.get(0).setDescricao(fragmentActivity.getResources().getString(R.string.dialog_passecarros_modelo));
            ArrayAdapter modelosMarcaSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, modelosMarcaDefault);
            spinnerModelo.setAdapter(modelosMarcaSpinnerAdapter);
        }

        if (categoria || marca || modelo) {
            ArrayList<ValueLabelDefault> combustiveisModelosDefault = Utils.createArrayDefault(new CombustiveisModelosReturn(fragmentActivity));
            combustiveisModelosDefault.get(0).setDescricao(fragmentActivity.getResources().getString(R.string.dialog_passecarros_combustivel));
            ArrayAdapter combustiveisModelosAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, combustiveisModelosDefault);
            spinnerCombustivel.setAdapter(combustiveisModelosAdapter);
        }

        if (categoria || marca || modelo || combustivel) {
            ArrayList<ValueLabelDefault> anosCombustivelDefault = Utils.createArrayDefault(new AnosCombustivelReturn(fragmentActivity));
            anosCombustivelDefault.get(0).setDescricao(fragmentActivity.getResources().getString(R.string.dialog_passecarros_ano));
            ArrayAdapter anosCombustivelAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, anosCombustivelDefault);
            spinnerAno.setAdapter(anosCombustivelAdapter);
        }

        if (categoria || marca || modelo || combustivel || ano) {
            textValorMedio.setVisibility(View.GONE);
        }
    }

    public void setLoadValuesToSpinners(Boolean categoria, Boolean marca,Boolean modelo,Boolean combustivel, Boolean ano) {
        if (categoria) {
            firstLoadMarcas = true;
        }

        if (categoria || marca) {
            firstLoadModelos = true;
        }

        if (categoria || marca || modelo) {
            firstLoadCombustivel = true;
        }

        if (categoria || marca || modelo || combustivel) {
            firstLoadAno= true;
        }

        if (categoria || marca || modelo || combustivel || ano) {
            firstLoadValorMedio = true;
        }
    }

    private void showProgressBar(ProgressBar progressBar, Spinner spinner) {
        spinner.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(ProgressBar progressBar, Spinner spinner) {
        spinner.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }
}

