package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliarActivity;
import br.com.carmaix.services.InformacoesAvaliacaoReturn;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/07/16.
 */
public class OpcionaisFragment extends BaseFragment {
    private InformacoesAvaliacaoReturn informacoesAvaliacaoReturn;
    private String[] opcionais;
    private String[] itens;

    private LinearLayout opcionaosCol1;
    private LinearLayout opcionaosCol2;
    private LinearLayout itensCol;

    private EditText editAro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.opcionais_fragment,container,false);
        opcionaosCol1 = (LinearLayout)v.findViewById(R.id.opcionais_col1);
        opcionaosCol2 = (LinearLayout)v.findViewById(R.id.opcionais_col2);
        itensCol = (LinearLayout)v.findViewById(R.id.items);

        editAro = (EditText) v.findViewById(R.id.edit_aro);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadValues();
        /*runBackground("",false,true, Constants.ACTION_LIST);*/
    }

    private void loadValues() {
        informacoesAvaliacaoReturn = ((AvaliarActivity) fragmentActivity).getInformacoesAvaliacaoReturn();
        opcionais = informacoesAvaliacaoReturn.getVeiculo().getOpcionais();
        itens = informacoesAvaliacaoReturn.getVeiculo().getItens();

        if(opcionais.length > 0) {
            for (String opcional : opcionais) {
                for (int i = 0; i < opcionaosCol1.getChildCount(); i++) {
                    CheckBox checkBox = (CheckBox) opcionaosCol1.getChildAt(i);
                    if (opcional.equals(checkBox.getTag())) {
                        checkBox.setChecked(true);
                        break;
                    }
                }

                for (int i = 0; i < opcionaosCol2.getChildCount(); i++) {
                    CheckBox checkBox = (CheckBox) opcionaosCol2.getChildAt(i);
                    if (opcional.equals(checkBox.getTag())) {
                        checkBox.setChecked(true);
                        break;
                    }
                }
            }
        }

        if(itens.length > 0) {
            for (String item : itens) {
                for (int i = 0; i < itensCol.getChildCount(); i++) {
                    if(itensCol.getChildAt(i) instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) itensCol.getChildAt(i);
                        if (item.equals(checkBox.getTag())) {
                            checkBox.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }

        editAro.setText(informacoesAvaliacaoReturn.getVeiculo().getAro());
    }


    /*@Override
    protected void backgroundMethod(int action) throws Throwable {
        if(action == Constants.ACTION_LIST) {
            informacoesAvaliacaoReturn = ((AvaliarActivity) fragmentActivity).getInformacoesAvaliacaoReturn();
            opcionais = informacoesAvaliacaoReturn.getVeiculo().getOpcionais();
            itens = informacoesAvaliacaoReturn.getVeiculo().getItens();
        }
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {
            if(opcionais.length > 0) {
                for (String opcional : opcionais) {
                    for (int i = 0; i < opcionaosCol1.getChildCount(); i++) {
                        CheckBox checkBox = (CheckBox) opcionaosCol1.getChildAt(i);
                        if (opcional.equals(checkBox.getTag())) {
                            checkBox.setChecked(true);
                            break;
                        }
                    }

                    for (int i = 0; i < opcionaosCol2.getChildCount(); i++) {
                        CheckBox checkBox = (CheckBox) opcionaosCol2.getChildAt(i);
                        if (opcional.equals(checkBox.getTag())) {
                            checkBox.setChecked(true);
                            break;
                        }
                    }
                }
            }

            if(itens.length > 0) {
                for (String item : itens) {
                    for (int i = 0; i < itensCol.getChildCount(); i++) {
                        if(itensCol.getChildAt(i) instanceof CheckBox) {
                            CheckBox checkBox = (CheckBox) itensCol.getChildAt(i);
                            if (item.equals(checkBox.getTag())) {
                                checkBox.setChecked(true);
                                break;
                            }
                        }
                    }
                }
            }

            editAro.setText(informacoesAvaliacaoReturn.getVeiculo().getAro());
        }
    }*/

    public String getAro() {
        return editAro.getText().toString();
    }

    public String[] getOpcionais() {
        ArrayList<String> opcionais = new ArrayList<>();
        for (int i = 0; i < opcionaosCol1.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) opcionaosCol1.getChildAt(i);
            if(checkBox.isChecked()) {
                opcionais.add((String)checkBox.getTag());
            }
        }

        for (int i = 0; i < opcionaosCol2.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) opcionaosCol2.getChildAt(i);
            if(checkBox.isChecked()) {
                opcionais.add((String)checkBox.getTag());
            }
        }
        String[] opcionaisArray = new String[opcionais.size()];
        opcionaisArray = opcionais.toArray(opcionaisArray);
        return opcionaisArray;
    }


    public String[] getItens() {
        ArrayList<String> itens = new ArrayList<>();

        for (int i = 0; i < itensCol.getChildCount(); i++) {
            if(itensCol.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) itensCol.getChildAt(i);
                if (checkBox.isChecked()) {
                    String tag = (String)checkBox.getTag();
                    itens.add(tag);
                }
            }
        }

        String[] itensArray = new String[itens.size()];
        itensArray = itens.toArray(itensArray);
        return itensArray;
    }
}
