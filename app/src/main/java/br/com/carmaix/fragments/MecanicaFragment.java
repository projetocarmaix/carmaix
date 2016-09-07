package br.com.carmaix.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliarActivity;
import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.services.Estrutura;
import br.com.carmaix.services.InformacoesAvaliacaoReturn;
import br.com.carmaix.services.Mecanica;
import br.com.carmaix.services.Veiculo;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.EditTextValidations;
import br.com.carmaix.utils.NumberTextWatcher;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

/**
 * Created by fernando on 21/07/16.
 */
public class MecanicaFragment extends BaseFragment {
    private InformacoesAvaliacaoReturn informacoesAvaliacaoReturn;
    private EditText editObservacoes;
    private EditText editObservacoesAdicionais;
    private EditText editReparos;
    private EditText editValor;

    private String mecMotor;
    private String mecSuspDianteira;
    private String mecHomocinetica;
    private String mecCambio;
    private String mecSuspTraseira;
    private String mecRolamentos;
    private String mecEmbreagem;
    private String mecCxDirecao;
    private String mecPneusDiant;
    private String mecFreios;
    private String mecEscapamento;
    private String mecPneusTras;
    private String mecDiferencial;

    private String estLataria;
    private String estParachoqueDiant;
    private String estPintura;
    private String estParachoqueTras;
    private String estCarroceria;
    private String estParabrisa;
    private String estPortaMalas;
    private String estEstofamento;
    private String estMotor;
    private String estFarol;
    private View view;
    private int actionParam;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mecanica_fragment,container,false);

        editReparos = (EditText)view.findViewById(R.id.edit_reparos);
        editReparos.setClickable(true);
        editReparos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(fragmentActivity);
                LayoutInflater inflater = LayoutInflater.from(fragmentActivity);
                View dialogView = inflater.inflate(R.layout.dialog_tabela_reparos,null);

                ImageButton martelinhoAdd = (ImageButton)dialogView.findViewById(R.id.image_buttom_martelinho_add);
                ImageButton martelinhoRemove = (ImageButton)dialogView.findViewById(R.id.image_buttom_martelinho_remove);

                ImageButton pintarPortaAdd = (ImageButton)dialogView.findViewById(R.id.image_buttom_pintar_porta_add);
                ImageButton pintarPortaRemove = (ImageButton)dialogView.findViewById(R.id.image_buttom_pintar_porta_remove);

                ImageButton pneus13Add = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_13_add);
                ImageButton pneus13Remove = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_13_remove);

                ImageButton pneusR15Add = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_r15_add);
                ImageButton pneusR15Remove = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_r15_remove);

                final EditText editReparosDialog = (EditText)dialogView.findViewById(R.id.edit_reparos_dialog);
                editReparosDialog.setText(editReparos.getText().toString());

                martelinhoAdd.setOnClickListener(addReparo(editReparosDialog,Constants.MARTELINHO));
                martelinhoRemove.setOnClickListener(removeReparo(editReparosDialog,Constants.MARTELINHO));

                pintarPortaAdd.setOnClickListener(addReparo(editReparosDialog,Constants.PINTAR_PORTA));
                pintarPortaRemove.setOnClickListener(removeReparo(editReparosDialog,Constants.PINTAR_PORTA));

                pneus13Add.setOnClickListener(addReparo(editReparosDialog,Constants.PNEUS_13));
                pneus13Remove.setOnClickListener(removeReparo(editReparosDialog,Constants.PNEUS_13));

                pneusR15Add.setOnClickListener(addReparo(editReparosDialog,Constants.PNEUS_R15));
                pneusR15Remove.setOnClickListener(removeReparo(editReparosDialog,Constants.PNEUS_R15));

                alerBuilder.setView(dialogView);
                alerBuilder.setPositiveButton(fragmentActivity.getResources().getString(R.string.label_dialog_confirmar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editReparos.setText(editReparosDialog.getText().toString());
                    }
                });

                alerBuilder.setNegativeButton(fragmentActivity.getResources().getString(R.string.label_dialog_cancelar),null);

                alerBuilder.create().show();
            }
        });

        editReparos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        editObservacoes = (EditText)view.findViewById(R.id.edit_observacoes);
        editObservacoesAdicionais = (EditText)view.findViewById(R.id.edit_observacoes_adicionais);
        editValor = (EditText)view.findViewById(R.id.edit_valor);

        editValor.addTextChangedListener(new NumberTextWatcher(editValor));
        actionParam = ((AvaliarActivity)fragmentActivity).getActionParam();

        if(actionParam == Constants.ACTION_AVALIAR) {
            loadValues();
        }

        initializeValueField();

        return view;
    }

    private void initializeValueField() {
        if(actionParam == Constants.ACTION_AVALIAR) {
            if (((AvaliarActivity)fragmentActivity).getLoginTable().getAvaliar()) {
                editValor.setEnabled(true);
            }else if(((AvaliarActivity)fragmentActivity).getLoginTable().getEditar()) {
                editValor.setEnabled(false);
            }
        }
    }


    private android.view.View.OnClickListener addReparo(final EditText editReparosDialog, final Integer value) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueReparos = editReparosDialog.getText().toString();
                Float valueSumReparos = null;
                if(!valueReparos.isEmpty()) {
                    valueSumReparos = Float.parseFloat(valueReparos.replace(",",".")) + value;
                }else {
                    valueSumReparos = Float.valueOf(value);
                }
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                editReparosDialog.setText((decimalFormat.format(valueSumReparos)).toString());

            }
        };
    }

    private android.view.View.OnClickListener removeReparo(final EditText editReparosDialog, final Integer value) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueReparos = editReparosDialog.getText().toString();
                Float valueSumReparos = null;
                if (!valueReparos.isEmpty()) {
                    valueSumReparos = Float.parseFloat(valueReparos.replace(",", ".")) - value;
                } else {
                    valueSumReparos = Float.valueOf(value);
                }

                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                if (valueSumReparos < 0) {
                    valueSumReparos = 0f;
                }

                editReparosDialog.setText((decimalFormat.format(valueSumReparos)).toString());

            }
        };
    }

    private void loadValues() {
        informacoesAvaliacaoReturn = ((AvaliarActivity) fragmentActivity).getInformacoesAvaliacaoReturn();
        Veiculo veiculo = informacoesAvaliacaoReturn.getVeiculo();
        Estrutura estrutura = veiculo.getEstrutura();
        Mecanica mecanica = veiculo.getMecanica();
        String[] sinaisColisao = informacoesAvaliacaoReturn.getVeiculo().getColisao();

        String mecMotor = mecanica.getMec_motor();
        if(mecMotor.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_motor_b)).setChecked(true);
        }else if (mecMotor.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_motor_m)).setChecked(true);
        }else if (mecMotor.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_motor_r)).setChecked(true);
        }

        String mecCambio = mecanica.getMec_cambio();
        if(mecCambio.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_cambio_b)).setChecked(true);
        }else if (mecCambio.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_cambio_m)).setChecked(true);
        }else if (mecCambio.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_cambio_r)).setChecked(true);
        }

        String mecEmbreagem = mecanica.getMec_embreagem();
        if(mecEmbreagem.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_embreagem_b)).setChecked(true);
        }else if (mecEmbreagem.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_embreagem_m)).setChecked(true);
        }else if (mecEmbreagem.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_embreagem_r)).setChecked(true);
        }

        String mecFreios = mecanica.getMec_freios();
        if(mecFreios.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_freios_b)).setChecked(true);
        }else if (mecFreios.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_freios_m)).setChecked(true);
        }else if (mecFreios.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_freios_r)).setChecked(true);
        }

        String mecSuspDianteira = mecanica.getMec_susp_dianteira();
        if(mecSuspDianteira.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_susp_dianteira_b)).setChecked(true);
        }else if (mecSuspDianteira.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_susp_dianteira_m)).setChecked(true);
        }else if (mecSuspDianteira.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_susp_dianteira_r)).setChecked(true);
        }

        String mecSuspTraseira = mecanica.getMec_susp_traseira();
        if(mecSuspTraseira.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_susp_traseira_b)).setChecked(true);
        }else if (mecSuspTraseira.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_susp_traseira_m)).setChecked(true);
        }else if (mecSuspTraseira.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_susp_traseira_r)).setChecked(true);
        }

        String mecCxDirecao = mecanica.getMec_cx_direcao();
        if(mecCxDirecao.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_cx_direcao_b)).setChecked(true);
        }else if (mecCxDirecao.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_cx_direcao_m)).setChecked(true);
        }else if (mecCxDirecao.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_cx_direcao_r)).setChecked(true);
        }

        String mecEscapamento = mecanica.getMec_escapamento();
        if(mecEscapamento.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_escapamento_b)).setChecked(true);
            String g = (String) ((RadioButton)view.findViewById(R.id.mec_escapamento_b)).getTag();
        }else if (mecEscapamento.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_escapamento_m)).setChecked(true);
        }else if (mecEscapamento.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_escapamento_r)).setChecked(true);
        }

        String mecHomocinetica = mecanica.getMec_homocinetica();
        if(mecHomocinetica.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_homocinetica_b)).setChecked(true);
        }else if (mecHomocinetica.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_homocinetica_m)).setChecked(true);
        }else if (mecHomocinetica.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_homocinetica_r)).setChecked(true);
        }

        String mecRolamentos = mecanica.getMec_rolamentos();
        if(mecRolamentos.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_rolamentos_b)).setChecked(true);
        }else if (mecRolamentos.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_rolamentos_m)).setChecked(true);
        }else if (mecRolamentos.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_rolamentos_r)).setChecked(true);
        }

        String mecPneusDiant = mecanica.getMec_pneus_diant();
        if(mecPneusDiant.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_pneus_diant_b)).setChecked(true);
        }else if (mecPneusDiant.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_pneus_diant_m)).setChecked(true);
        }else if (mecPneusDiant.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_pneus_diant_r)).setChecked(true);
        }

        String mecPneusTras = mecanica.getMec_pneus_tras();
        if(mecPneusTras.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_pneus_tras_b)).setChecked(true);
        }else if (mecPneusTras.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_pneus_tras_m)).setChecked(true);
        }else if (mecPneusTras.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_pneus_tras_r)).setChecked(true);
        }

        String mecDiferencial = mecanica.getMec_diferencial();
        if(mecDiferencial.equals("B")) {
            ((RadioButton)view.findViewById(R.id.mec_diferencial_b)).setChecked(true);
        }else if (mecDiferencial.equals("M")){
            ((RadioButton)view.findViewById(R.id.mec_diferencial_m)).setChecked(true);
        }else if (mecDiferencial.equals("R")){
            ((RadioButton)view.findViewById(R.id.mec_diferencial_r)).setChecked(true);
        }

        String estLataria = estrutura.getEst_lataria();
        if(estLataria.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_lataria_b)).setChecked(true);
        }else if (estLataria.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_lataria_m)).setChecked(true);
        }else if (estLataria.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_lataria_r)).setChecked(true);
        }

        String estPintura = estrutura.getEst_pintura();
        if(estPintura.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_pintura_b)).setChecked(true);
        }else if (estPintura.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_pintura_m)).setChecked(true);
        }else if (estPintura.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_pintura_r)).setChecked(true);
        }

        String estCarroceria = estrutura.getEst_carroceria();
        if(estCarroceria.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_carroceria_b)).setChecked(true);
        }else if (estCarroceria.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_carroceria_m)).setChecked(true);
        }else if (estCarroceria.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_carroceria_r)).setChecked(true);
        }

        String estPortaMalas = estrutura.getEst_porta_malas();
        if(estPortaMalas.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_porta_malas_b)).setChecked(true);
        }else if (estPortaMalas.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_porta_malas_m)).setChecked(true);
        }else if (estPortaMalas.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_porta_malas_r)).setChecked(true);
        }

        String estMotor = estrutura.getEst_motor();
        if(estMotor.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_motor_b)).setChecked(true);
        }else if (estMotor.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_motor_m)).setChecked(true);
        }else if (estMotor.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_motor_r)).setChecked(true);
        }

        String estParachoqueDiant = estrutura.getEst_parachoque_diant();
        if(estParachoqueDiant.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_parachoque_diant_b)).setChecked(true);
        }else if (estParachoqueDiant.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_parachoque_diant_m)).setChecked(true);
        }else if (estParachoqueDiant.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_parachoque_diant_r)).setChecked(true);
        }

        String estParachoqueTras = estrutura.getEst_parachoque_tras();
        if(estParachoqueTras.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_parachoque_tras_b)).setChecked(true);
        }else if (estParachoqueTras.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_parachoque_tras_m)).setChecked(true);
        }else if (estParachoqueTras.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_parachoque_tras_r)).setChecked(true);
        }

        String estParabrisa = estrutura.getEst_parabrisa();
        if(estParabrisa.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_parabrisa_b)).setChecked(true);
        }else if (estParabrisa.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_parabrisa_m)).setChecked(true);
        }else if (estParabrisa.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_parabrisa_r)).setChecked(true);
        }

        String estEstofamento = estrutura.getEst_estofamento();
        if(estEstofamento.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_estofamento_b)).setChecked(true);
        }else if (estParabrisa.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_estofamento_m)).setChecked(true);
        }else if (estParabrisa.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_estofamento_r)).setChecked(true);
        }

        String estFarol = estrutura.getEst_farol();
        if(estFarol.equals("B")) {
            ((RadioButton)view.findViewById(R.id.est_farol_b)).setChecked(true);
        }else if (estFarol.equals("M")){
            ((RadioButton)view.findViewById(R.id.est_farol_m)).setChecked(true);
        }else if (estFarol.equals("R")){
            ((RadioButton)view.findViewById(R.id.est_farol_r)).setChecked(true);
        }

        LinearLayout sinaisColisaoColumn = (LinearLayout)view.findViewById(R.id.sinaisColisaoColumn);
        for(String sinalColisao : sinaisColisao) {
            for(int i = 0; i < sinaisColisaoColumn.getChildCount(); i++) {
                CheckBox check = (CheckBox)sinaisColisaoColumn.getChildAt(i);
                if(sinalColisao.equals(check.getTag())) {
                    check.setChecked(true);
                    break;
                }
            }
        }

        editObservacoes.setText(informacoesAvaliacaoReturn.getObservacao());
        editObservacoesAdicionais.setText(informacoesAvaliacaoReturn.getObservacoes_adicionais());

        if((veiculo.getFranquia_reparos() == null || veiculo.getFranquia_reparos().isEmpty())) {
            editReparos.setText("0,00");
        }else {
            editReparos.setText(veiculo.getFranquia_reparos());
        }

        editValor.setText(veiculo.getValor());

    }


    public String getMecMotorValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_motor_b)).isChecked()) {
           ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_motor_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_motor_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecSuspDianteiraValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_susp_dianteira_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_susp_dianteira_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_susp_dianteira_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecHomocineticaValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_homocinetica_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_homocinetica_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_homocinetica_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecCambioValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_cambio_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_cambio_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_cambio_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecSuspTraseiraValueValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_susp_traseira_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_susp_traseira_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_susp_traseira_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecRolamentosValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_rolamentos_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_rolamentos_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_rolamentos_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecEmbreagemValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_embreagem_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_embreagem_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_embreagem_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecCxDirecaoValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_cx_direcao_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_cx_direcao_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_cx_direcao_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecPneusDiantValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_pneus_diant_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_pneus_diant_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_pneus_diant_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecFreiosValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_freios_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_freios_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_freios_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecEscapamentoValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_escapamento_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_escapamento_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_escapamento_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecPneusTrasValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_pneus_tras_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_pneus_tras_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_pneus_tras_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getMecDiferencialValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.mec_diferencial_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.mec_diferencial_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.mec_diferencial_r)).isChecked()) {
            ret = "R";
        }

        return ret;
    }

    public String getEstLatariaValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_lataria_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_lataria_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_lataria_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstParachoqueDiantValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_parachoque_diant_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_parachoque_diant_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_parachoque_diant_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstPinturaValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_pintura_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_pintura_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_pintura_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstParachoqueTrasValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_parachoque_tras_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_parachoque_tras_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_parachoque_tras_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstCarroceriaValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_carroceria_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_carroceria_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_carroceria_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstParabrisaValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_parabrisa_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_parabrisa_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_parabrisa_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstPortaMalasValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_porta_malas_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_porta_malas_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_porta_malas_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstEstofamentoValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_estofamento_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_estofamento_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_estofamento_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstMotorValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_motor_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_motor_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_motor_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String getEstFarolValue() {
        String ret = "";
        if(((RadioButton)view.findViewById(R.id.est_farol_b)).isChecked()) {
            ret = "B";
        }else if(((RadioButton)view.findViewById(R.id.est_farol_m)).isChecked()) {
            ret = "M";
        }else if(((RadioButton)view.findViewById(R.id.est_farol_r)).isChecked()) {
            ret = "R";
        }
        return ret;
    }

    public String[] getColisao() {
        ArrayList<String> colisao = new ArrayList<>();
        LinearLayout sinaisColisaoColumn = (LinearLayout)view.findViewById(R.id.sinaisColisaoColumn);

        for(int i = 0; i < sinaisColisaoColumn.getChildCount(); i++) {
            CheckBox check = (CheckBox)sinaisColisaoColumn.getChildAt(i);
            if(check.isChecked()) {
                String tag = (String)check.getTag();
                colisao.add(tag);
            }
        }

        String[] colisaoArray = new String[colisao.size()];
        colisaoArray = colisao.toArray(colisaoArray);
        return colisaoArray;
    }

    public String getEditObservacoes() {
        return editObservacoes.getText().toString();
    }

    public String getEditObservacoesAdicionais() {
        return editObservacoesAdicionais.getText().toString();
    }

    public String getEditValorReturn() {
        return editValor.getText().toString().replaceAll("[R$]", "");
    }

    public String getEditReparosReturn() {
        return (editReparos.getText().toString().replace(",",""));
    }

    public EditText getEditReparos() {
        return editReparos;
    }

    public EditText getEditValor() {
        return editValor;
    }

}
