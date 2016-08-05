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

    private CheckBox check_abs_tras;
    private CheckBox check_airbag_mot;
    private CheckBox check_airbag_lateral;
    private CheckBox check_alarme;
    private CheckBox check_alarme_orig;
    private CheckBox check_ar_condicionado;
    private CheckBox check_ar_cond_digital;
    private CheckBox check_ar_quente;
    private CheckBox check_barra_no_teto;
    private CheckBox check_bco_couro;
    private CheckBox check_cambio_aut;
    private CheckBox check_capota_maritima;
    private CheckBox check_chave_code;
    private CheckBox check_dir_hidraulica;
    private CheckBox check_dir_eletrica;
    private CheckBox check_desemb_traseiro;
    private CheckBox check_farois_neblina;
    private CheckBox check_rodas_liga_orig;
    private CheckBox check_rodas_liga_esp;
    private CheckBox check_limpador_tras;
    private CheckBox check_gnv;
    private CheckBox check_prot_cacamba;
    private CheckBox check_retrovisor_eletr;
    private CheckBox check_santo_antonio;
    private CheckBox check_teto_solar_eletr;
    private CheckBox check_trava_eletr;
    private CheckBox check_vidros_eletr_dian;
    private CheckBox check_vidros_eletr_tras;
    private CheckBox check_cd;
    private CheckBox check_cd_orig;
    private CheckBox check_cd_mp3;
    private CheckBox check_tracao_4_4;
    private CheckBox check_xenon_orig;
    private CheckBox check_xenon;
    private CheckBox check_sensor_est;
    private CheckBox check_sensor_crep;
    private CheckBox check_sensor_chuva;

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

        check_abs_tras = (CheckBox) v.findViewById(R.id.check_abs_tras);
        check_airbag_mot = (CheckBox) v.findViewById(R.id.check_airbag_mot);
        check_airbag_lateral =(CheckBox) v.findViewById(R.id.check_airbag_lateral);
        check_alarme =(CheckBox) v.findViewById(R.id.check_alarme);
        check_alarme_orig =(CheckBox) v.findViewById(R.id.check_alarme_orig);
        check_ar_condicionado =(CheckBox) v.findViewById(R.id.check_ar_condicionado);
        check_ar_cond_digital =(CheckBox) v.findViewById(R.id.check_ar_cond_digital);
        check_ar_quente =(CheckBox) v.findViewById(R.id.check_ar_quente);
        check_barra_no_teto =(CheckBox) v.findViewById(R.id.check_barra_no_teto);
        check_bco_couro =(CheckBox) v.findViewById(R.id.check_bco_couro);
        check_cambio_aut =(CheckBox) v.findViewById(R.id.check_cambio_aut);
        check_capota_maritima = (CheckBox)v.findViewById(R.id.check_capota_maritima);
        check_chave_code =(CheckBox) v.findViewById(R.id.check_chave_code);
        check_dir_hidraulica = (CheckBox)v.findViewById(R.id.check_dir_hidraulica);
        check_dir_eletrica =(CheckBox) v.findViewById(R.id.check_dir_eletrica);
        check_desemb_traseiro = (CheckBox)v.findViewById(R.id.check_desemb_traseiro);
        check_farois_neblina =(CheckBox) v.findViewById(R.id.check_farois_neblina);
        check_rodas_liga_orig =(CheckBox) v.findViewById(R.id.check_rodas_liga_orig);
        check_rodas_liga_esp =(CheckBox) v.findViewById(R.id.check_rodas_liga_esp);
        check_limpador_tras =(CheckBox) v.findViewById(R.id.check_limpador_tras);
        check_gnv =(CheckBox) v.findViewById(R.id.check_gnv);
        check_prot_cacamba =(CheckBox) v.findViewById(R.id.check_prot_cacamba);
        check_retrovisor_eletr =(CheckBox) v.findViewById(R.id.check_retrovisor_eletr);
        check_santo_antonio =(CheckBox) v.findViewById(R.id.check_santo_antonio);
        check_teto_solar_eletr = (CheckBox)v.findViewById(R.id.check_teto_solar_eletr);
        check_trava_eletr = (CheckBox)v.findViewById(R.id.check_trava_eletr);
        check_vidros_eletr_dian =(CheckBox) v.findViewById(R.id.check_vidros_eletr_dian);
        check_vidros_eletr_tras = (CheckBox)v.findViewById(R.id.check_vidros_eletr_tras);
        check_cd = (CheckBox)v.findViewById(R.id.check_cd);
        check_cd_orig =(CheckBox) v.findViewById(R.id.check_cd_orig);
        check_cd_mp3 =(CheckBox) v.findViewById(R.id.check_cd_mp3);
        check_tracao_4_4 = (CheckBox)v.findViewById(R.id.check_tracao_4_4);
        check_xenon_orig =(CheckBox) v.findViewById(R.id.check_xenon_orig);
        check_xenon =(CheckBox) v.findViewById(R.id.check_xenon);
        check_sensor_est =(CheckBox) v.findViewById(R.id.check_sensor_est);
        check_sensor_crep =(CheckBox) v.findViewById(R.id.check_sensor_crep);
        check_sensor_chuva = (CheckBox)v.findViewById(R.id.check_sensor_chuva);

        editAro = (EditText) v.findViewById(R.id.edit_aro);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        runBackground("",false,true, Constants.ACTION_LIST);
    }

    @Override
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
    }
}
