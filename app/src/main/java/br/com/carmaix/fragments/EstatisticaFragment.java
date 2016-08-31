package br.com.carmaix.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliarActivity;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.EstatisticaReturn;
import br.com.carmaix.services.EstatisticaRows;
import br.com.carmaix.services.FipePassecarrosReturn;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/07/16.
 */
public class EstatisticaFragment extends BaseFragment {
    private TextView avaliadosUnidadeQuant;
    private TextView avaliadosUnidadeMed;
    private TextView avaliadosUnidadeMax;
    private TextView avaliadosUnidadeMin;
    private TextView avaliadosUnidadeMedDias;
    private TextView avaliadosUnidadeRep;
    private TextView avaliadosGrupoQuant;
    private TextView avaliadosGrupoMed;
    private TextView avaliadosGrupoMax;
    private TextView avaliadosGrupoMin;
    private TextView avaliadosGrupoMedDias;
    private TextView avaliadosGrupoRep;
    private TextView avaliadosTotalQuant;
    private TextView avaliadosTotalMed;
    private TextView avaliadosTotalMax;
    private TextView avaliadosTotalMin;
    private TextView avaliadosTotalMedDias;
    private TextView avaliadosTotalRep;
    private TextView estoqueUnidadeQuant;
    private TextView estoqueUnidadeMed;
    private TextView estoqueUnidadeMax;
    private TextView estoqueUnidadeMin;
    private TextView estoqueUnidadeMedDias;
    private TextView estoqueUnidadeRep;
    private TextView estoqueGrupoQuant;
    private TextView estoqueGrupoMed;
    private TextView estoqueGrupoMax;
    private TextView estoqueGrupoMin;
    private TextView estoqueGrupoMedDias;
    private TextView estoqueGrupoRep;
    private TextView estoqueTotalQuant;
    private TextView estoqueTotalMed;
    private TextView estoqueTotalMax;
    private TextView estoqueTotalMin;
    private TextView estoqueTotalMedDias;
    private TextView estoqueTotalRep;
    private TextView vendidosUnidadeQuant;
    private TextView vendidosUnidadeMed;
    private TextView vendidosUnidadeMax;
    private TextView vendidosUnidadeMin;
    private TextView vendidosUnidadeMedDias;
    private TextView vendidosUnidadeRep;
    private TextView vendidosGrupoQuant;
    private TextView vendidosGrupoMed;
    private TextView vendidosGrupoMax;
    private TextView vendidosGrupoMin;
    private TextView vendidosGrupoMedDias;
    private TextView vendidosGrupoRep;
    private TextView vendidosTotalQuant;
    private TextView vendidosTotalMed;
    private TextView vendidosTotalMax;
    private TextView vendidosTotalMin;
    private TextView vendidosTotalMedDias;
    private TextView vendidosTotalRep;
    private TextView quantPassecarros;
    private TextView medPassecarros;
    private TextView maxPassecarros;
    private TextView minPassecarros;
    private TextView medDiasPassecarros;
    private TextView repPassecarros;
    private TextView quantFipe;
    private TextView medFipe;
    private TextView maxFipe;
    private TextView minFipe;
    private TextView medDiasFipe;
    private TextView repFipe;

    private EstatisticaReturn estatisticaReturn;
    private String modelo;
    private String ano;
    private String combustivel;

    private LinearLayout estatisticaMensagem;
    private LinearLayout tabelaEstatistica;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.estatistica_fragment,container,false);
        ImageView imagePassecarros =  (ImageView)view.findViewById(R.id.table_image_passecarros);
        ImageView imageFipe =  (ImageView)view.findViewById(R.id.table_image_fipe);

        Picasso.with(fragmentActivity).load(R.drawable.tabela_passecarros).into(imagePassecarros);
        Picasso.with(fragmentActivity).load(R.drawable.fipe).into(imageFipe);

        estatisticaMensagem  =  (LinearLayout)view.findViewById(R.id.estatistica_mensagem);
        tabelaEstatistica   =  (LinearLayout)view.findViewById(R.id.tabela_estatistica);

        avaliadosUnidadeQuant  =  (TextView)view.findViewById(R.id.avaliados_unidade_quant);
        avaliadosUnidadeMed    =  (TextView)view.findViewById(R.id.avaliados_unidade_med);
        avaliadosUnidadeMax    =  (TextView)view.findViewById(R.id.avaliados_unidade_max);
        avaliadosUnidadeMin    =  (TextView)view.findViewById(R.id.avaliados_unidade_min);
        avaliadosUnidadeMedDias=  (TextView)view.findViewById(R.id.avaliados_unidade_med_dias);
        avaliadosUnidadeRep    =  (TextView)view.findViewById(R.id.avaliados_unidade_rep);

        avaliadosGrupoQuant    =  (TextView)view.findViewById(R.id.avaliados_grupo_quant);
        avaliadosGrupoMed      =  (TextView)view.findViewById(R.id.avaliados_grupo_med);
        avaliadosGrupoMax      =  (TextView)view.findViewById(R.id.avaliados_grupo_max);
        avaliadosGrupoMin      =  (TextView)view.findViewById(R.id.avaliados_grupo_min);
        avaliadosGrupoMedDias  =  (TextView)view.findViewById(R.id.avaliados_grupo_med_dias);
        avaliadosGrupoRep      =  (TextView)view.findViewById(R.id.avaliados_grupo_rep);

        avaliadosTotalQuant    =  (TextView)view.findViewById(R.id.avaliados_total_quant);
        avaliadosTotalMed      =  (TextView)view.findViewById(R.id.avaliados_total_med);
        avaliadosTotalMax      =  (TextView)view.findViewById(R.id.avaliados_total_max);
        avaliadosTotalMin      =  (TextView)view.findViewById(R.id.avaliados_total_min);
        avaliadosTotalMedDias  =  (TextView)view.findViewById(R.id.avaliados_total_med_dias);
        avaliadosTotalRep      =  (TextView)view.findViewById(R.id.avaliados_total_rep);

        estoqueUnidadeQuant    =  (TextView)view.findViewById(R.id.estoque_unidade_quant);
        estoqueUnidadeMed      =  (TextView)view.findViewById(R.id.estoque_unidade_med);
        estoqueUnidadeMax      =  (TextView)view.findViewById(R.id.estoque_unidade_max);
        estoqueUnidadeMin      =  (TextView)view.findViewById(R.id.estoque_unidade_min);
        estoqueUnidadeMedDias  =  (TextView)view.findViewById(R.id.estoque_unidade_med_dias);
        estoqueUnidadeRep      =  (TextView)view.findViewById(R.id.estoque_unidade_rep);

        estoqueGrupoQuant      =  (TextView)view.findViewById(R.id.estoque_grupo_quant);
        estoqueGrupoMed        =  (TextView)view.findViewById(R.id.estoque_grupo_med);
        estoqueGrupoMax        =  (TextView)view.findViewById(R.id.estoque_grupo_max);
        estoqueGrupoMin        =  (TextView)view.findViewById(R.id.estoque_grupo_min);
        estoqueGrupoMedDias    =  (TextView)view.findViewById(R.id.estoque_grupo_med_dias);
        estoqueGrupoRep        =  (TextView)view.findViewById(R.id.estoque_grupo_rep);

        estoqueTotalQuant      =  (TextView)view.findViewById(R.id.estoque_total_quant);
        estoqueTotalMed        =  (TextView)view.findViewById(R.id.estoque_total_med);
        estoqueTotalMax        =  (TextView)view.findViewById(R.id.estoque_total_max);
        estoqueTotalMin        =  (TextView)view.findViewById(R.id.estoque_total_min);
        estoqueTotalMedDias    =  (TextView)view.findViewById(R.id.estoque_total_med_dias);
        estoqueTotalRep        =  (TextView)view.findViewById(R.id.estoque_total_rep);

        vendidosUnidadeQuant   =  (TextView)view.findViewById(R.id.vendidos_unidade_quant);
        vendidosUnidadeMed     =  (TextView)view.findViewById(R.id.vendidos_unidade_med);
        vendidosUnidadeMax     =  (TextView)view.findViewById(R.id.vendidos_unidade_max);
        vendidosUnidadeMin     =  (TextView)view.findViewById(R.id.vendidos_unidade_min);
        vendidosUnidadeMedDias =  (TextView)view.findViewById(R.id.vendidos_unidade_med_dias);
        vendidosUnidadeRep     =  (TextView)view.findViewById(R.id.vendidos_unidade_rep);

        vendidosGrupoQuant     =  (TextView)view.findViewById(R.id.vendidos_grupo_quant);
        vendidosGrupoMed       =  (TextView)view.findViewById(R.id.vendidos_grupo_med);
        vendidosGrupoMax       =  (TextView)view.findViewById(R.id.vendidos_grupo_max);
        vendidosGrupoMin       =  (TextView)view.findViewById(R.id.vendidos_grupo_min);
        vendidosGrupoMedDias   =  (TextView)view.findViewById(R.id.vendidos_grupo_med_dias);
        vendidosGrupoRep       =  (TextView)view.findViewById(R.id.vendidos_grupo_rep);

        vendidosTotalQuant     =  (TextView)view.findViewById(R.id.vendidos_total_quant);
        vendidosTotalMed       =  (TextView)view.findViewById(R.id.vendidos_total_med);
        vendidosTotalMax       =  (TextView)view.findViewById(R.id.vendidos_total_max);
        vendidosTotalMin       =  (TextView)view.findViewById(R.id.vendidos_total_min);
        vendidosTotalMedDias   =  (TextView)view.findViewById(R.id.vendidos_total_med_dias);
        vendidosTotalRep       =  (TextView)view.findViewById(R.id.vendidos_total_rep);

        quantPassecarros       =  (TextView)view.findViewById(R.id.quant_passecarros);
        medPassecarros         =  (TextView)view.findViewById(R.id.med_passecarros);
        maxPassecarros         =  (TextView)view.findViewById(R.id.max_passecarros);
        minPassecarros         =  (TextView)view.findViewById(R.id.min_passecarros);
        medDiasPassecarros     =  (TextView)view.findViewById(R.id.med_dias_passecarros);
        repPassecarros         =  (TextView)view.findViewById(R.id.rep_passecarros);

        quantFipe              =  (TextView)view.findViewById(R.id.quant_fipe);
        medFipe                =  (TextView)view.findViewById(R.id.med_fipe);
        maxFipe                =  (TextView)view.findViewById(R.id.max_fipe);
        minFipe                =  (TextView)view.findViewById(R.id.min_fipe);
        medDiasFipe            =  (TextView)view.findViewById(R.id.med_dias_fipe);
        repFipe                =  (TextView)view.findViewById(R.id.rep_fipe);


        return view;
    }

    @Override
    protected void backgroundMethod(final int action) throws Throwable {
        changeInformationState(action);
        if(action == Constants.ACTION_LIST) {
            this.estatisticaReturn = CallService.getEstatistica(fragmentActivity, this.modelo, this.ano, this.combustivel);
        }

        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {

            EstatisticaRows avaliadoRows = this.estatisticaReturn.getAvaliado();
            EstatisticaRows estoqueRows = this.estatisticaReturn.getEstoque();
            EstatisticaRows vendidoRows = this.estatisticaReturn.getVendido();
            FipePassecarrosReturn fipeRows = this.estatisticaReturn.getFipe();
            FipePassecarrosReturn passecarrosRows = this.estatisticaReturn.getPassecarros();

            if(avaliadoRows != null) {
                if(avaliadoRows.getQuant() != null) {
                    avaliadosUnidadeQuant.setText(avaliadoRows.getQuant().getUnidade());
                    avaliadosGrupoQuant.setText(avaliadoRows.getQuant().getGrupo());
                    avaliadosTotalQuant.setText(avaliadoRows.getQuant().getTotal());
                }

                if(avaliadoRows.getMax() != null) {
                    avaliadosUnidadeMax.setText(avaliadoRows.getMax().getUnidade());
                    avaliadosGrupoMax.setText(avaliadoRows.getMax().getGrupo());
                    avaliadosTotalMax.setText(avaliadoRows.getMax().getTotal());
                }


                if(avaliadoRows.getMed() != null) {
                    avaliadosUnidadeMed.setText(avaliadoRows.getMed().getUnidade());
                    avaliadosGrupoMed.setText(avaliadoRows.getMed().getGrupo());
                    avaliadosTotalMed.setText(avaliadoRows.getMed().getTotal());
                }

                if(avaliadoRows.getMin() != null) {
                    avaliadosUnidadeMin.setText(avaliadoRows.getMin().getUnidade());
                    avaliadosGrupoMin.setText(avaliadoRows.getMin().getGrupo());
                    avaliadosTotalMin.setText(avaliadoRows.getMin().getTotal());
                }

                if(avaliadoRows.getDias() != null) {
                    avaliadosUnidadeMedDias.setText(avaliadoRows.getDias().getUnidade());
                    avaliadosGrupoMedDias.setText(avaliadoRows.getDias().getGrupo());
                    avaliadosTotalMedDias.setText(avaliadoRows.getDias().getTotal());
                }

                if(avaliadoRows.getRep() != null) {
                    avaliadosUnidadeRep.setText(avaliadoRows.getRep().getUnidade());
                    avaliadosGrupoRep.setText(avaliadoRows.getRep().getGrupo());
                    avaliadosTotalRep.setText(avaliadoRows.getRep().getTotal());
                }
            }

            if(estoqueRows != null) {
                if(estoqueRows.getQuant() != null) {
                    estoqueUnidadeQuant.setText(estoqueRows.getQuant().getUnidade());
                    estoqueGrupoQuant.setText(estoqueRows.getQuant().getGrupo());
                    estoqueTotalQuant.setText(estoqueRows.getQuant().getTotal());
                }

                if(estoqueRows.getMax() != null) {
                    estoqueUnidadeMax.setText(estoqueRows.getMax().getUnidade());
                    estoqueGrupoMax.setText(estoqueRows.getMax().getGrupo());
                    estoqueTotalMax.setText(estoqueRows.getMax().getTotal());
                }


                if(estoqueRows.getMed() != null) {
                    estoqueUnidadeMed.setText(estoqueRows.getMed().getUnidade());
                    estoqueGrupoMed.setText(estoqueRows.getMed().getGrupo());
                    estoqueTotalMed.setText(estoqueRows.getMed().getTotal());
                }

                if(estoqueRows.getMin() != null) {
                    estoqueUnidadeMin.setText(estoqueRows.getMin().getUnidade());
                    estoqueGrupoMin.setText(estoqueRows.getMin().getGrupo());
                    estoqueTotalMin.setText(estoqueRows.getMin().getTotal());
                }

                if(estoqueRows.getDias() != null) {
                    estoqueUnidadeMedDias.setText(estoqueRows.getDias().getUnidade());
                    estoqueGrupoMedDias.setText(estoqueRows.getDias().getGrupo());
                    estoqueTotalMedDias.setText(estoqueRows.getDias().getTotal());
                }

                if(estoqueRows.getRep() != null) {
                    estoqueUnidadeRep.setText(estoqueRows.getRep().getUnidade());
                    estoqueGrupoRep.setText(estoqueRows.getRep().getGrupo());
                    estoqueTotalRep.setText(estoqueRows.getRep().getTotal());
                }
            }

            if(vendidoRows != null) {
                if(vendidoRows.getQuant() != null) {
                    vendidosUnidadeQuant.setText(vendidoRows.getQuant().getUnidade());
                    vendidosGrupoQuant.setText(vendidoRows.getQuant().getGrupo());
                    vendidosTotalQuant.setText(vendidoRows.getQuant().getTotal());
                }

                if(vendidoRows.getMax() != null) {
                    vendidosUnidadeMax.setText(vendidoRows.getMax().getUnidade());
                    vendidosGrupoMax.setText(vendidoRows.getMax().getGrupo());
                    vendidosTotalMax.setText(vendidoRows.getMax().getTotal());
                }


                if(vendidoRows.getMed() != null) {
                    vendidosUnidadeMed.setText(vendidoRows.getMed().getUnidade());
                    vendidosGrupoMed.setText(vendidoRows.getMed().getGrupo());
                    vendidosTotalMed.setText(vendidoRows.getMed().getTotal());
                }

                if(vendidoRows.getMin() != null) {
                    vendidosUnidadeMin.setText(vendidoRows.getMin().getUnidade());
                    vendidosGrupoMin.setText(vendidoRows.getMin().getGrupo());
                    vendidosTotalMin.setText(vendidoRows.getMin().getTotal());
                }

                if(vendidoRows.getDias() != null) {
                    vendidosUnidadeMedDias.setText(vendidoRows.getDias().getUnidade());
                    vendidosGrupoMedDias.setText(vendidoRows.getDias().getGrupo());
                    vendidosTotalMedDias.setText(vendidoRows.getDias().getTotal());
                }

                if(vendidoRows.getRep() != null) {
                    vendidosUnidadeRep.setText(vendidoRows.getRep().getUnidade());
                    vendidosGrupoRep.setText(vendidoRows.getRep().getGrupo());
                    vendidosTotalRep.setText(vendidoRows.getRep().getTotal());
                }
            }

            if(fipeRows != null) {
                if(fipeRows.getQuant() != null) {
                    quantFipe.setText(fipeRows.getQuant());
                }

                if(fipeRows.getMax() != null) {
                    maxFipe.setText(fipeRows.getMax());
                }

                if(fipeRows.getMed() != null) {
                    medFipe.setText(fipeRows.getMed());
                }

                if(fipeRows.getMin() != null) {
                    minFipe.setText(fipeRows.getMin());
                }

            }

            if(passecarrosRows != null) {
                if(passecarrosRows.getQuant() != null) {
                    quantPassecarros.setText(passecarrosRows.getQuant());
                }

                if(passecarrosRows.getMax() != null) {
                    maxPassecarros.setText(passecarrosRows.getMax());
                }

                if(passecarrosRows.getMed() != null) {
                    medPassecarros.setText(passecarrosRows.getMed());
                }

                if(passecarrosRows.getMin() != null) {
                    minPassecarros.setText(passecarrosRows.getMin());
                }

            }
        }
    }

    public void loadData() {
        HashMap<String,String> params = ((AvaliarActivity)fragmentActivity).getEstatisticaParams();
        this.modelo = params.get("modelo");
        this.combustivel = params.get("combustivel");
        this.ano = params.get("ano");

        if(!(this.modelo.isEmpty()) && !(this.combustivel.isEmpty()) && !(this.ano.isEmpty())) {
            runBackground(fragmentActivity.getResources().getString(R.string.carregando), true, true, Constants.ACTION_LIST);
        }
    }

    private void changeInformationState(final int action) {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(action == Constants.ACTION_LIST) {
                    estatisticaMensagem.setVisibility(View.GONE);
                    tabelaEstatistica.setVisibility(View.VISIBLE);
                }else if(action == Constants.ACTION_FILL) {
                    estatisticaMensagem.setVisibility(View.VISIBLE);
                    tabelaEstatistica.setVisibility(View.GONE);
                }
            }
        }.execute();
    }
}

