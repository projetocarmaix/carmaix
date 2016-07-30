package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.carmaix.R;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.estatistica_fragment,container,false);
        ImageView imagePassecarros =  (ImageView)v.findViewById(R.id.table_image_passecarros);
        ImageView imageFipe =  (ImageView)v.findViewById(R.id.table_image_fipe);

        Picasso.with(fragmentActivity).load(R.drawable.tabela_passecarros).into(imagePassecarros);
        Picasso.with(fragmentActivity).load(R.drawable.fipe).into(imageFipe);

        avaliadosUnidadeQuant  =  (TextView)v.findViewById(R.id.avaliados_unidade_quant);
        avaliadosUnidadeMed    =  (TextView)v.findViewById(R.id.avaliados_unidade_med);
        avaliadosUnidadeMax    =  (TextView)v.findViewById(R.id.avaliados_unidade_max);
        avaliadosUnidadeMin    =  (TextView)v.findViewById(R.id.avaliados_unidade_min);
        avaliadosUnidadeMedDias=  (TextView)v.findViewById(R.id.avaliados_unidade_med_dias);
        avaliadosUnidadeRep    =  (TextView)v.findViewById(R.id.avaliados_unidade_rep);

        avaliadosGrupoQuant    =  (TextView)v.findViewById(R.id.avaliados_grupo_quant);
        avaliadosGrupoMed      =  (TextView)v.findViewById(R.id.avaliados_grupo_med);
        avaliadosGrupoMax      =  (TextView)v.findViewById(R.id.avaliados_grupo_max);
        avaliadosGrupoMin      =  (TextView)v.findViewById(R.id.avaliados_grupo_min);
        avaliadosGrupoMedDias  =  (TextView)v.findViewById(R.id.avaliados_grupo_med_dias);
        avaliadosGrupoRep      =  (TextView)v.findViewById(R.id.avaliados_grupo_rep);

        avaliadosTotalQuant    =  (TextView)v.findViewById(R.id.avaliados_total_quant);
        avaliadosTotalMed      =  (TextView)v.findViewById(R.id.avaliados_total_med);
        avaliadosTotalMax      =  (TextView)v.findViewById(R.id.avaliados_total_max);
        avaliadosTotalMin      =  (TextView)v.findViewById(R.id.avaliados_total_min);
        avaliadosTotalMedDias  =  (TextView)v.findViewById(R.id.avaliados_total_med_dias);
        avaliadosTotalRep      =  (TextView)v.findViewById(R.id.avaliados_total_rep);

        estoqueUnidadeQuant    =  (TextView)v.findViewById(R.id.estoque_unidade_quant);
        estoqueUnidadeMed      =  (TextView)v.findViewById(R.id.estoque_unidade_med);
        estoqueUnidadeMax      =  (TextView)v.findViewById(R.id.estoque_unidade_max);
        estoqueUnidadeMin      =  (TextView)v.findViewById(R.id.estoque_unidade_min);
        estoqueUnidadeMedDias  =  (TextView)v.findViewById(R.id.estoque_unidade_med_dias);
        estoqueUnidadeRep      =  (TextView)v.findViewById(R.id.estoque_unidade_rep);

        estoqueGrupoQuant      =  (TextView)v.findViewById(R.id.estoque_grupo_quant);
        estoqueGrupoMed        =  (TextView)v.findViewById(R.id.estoque_grupo_med);
        estoqueGrupoMax        =  (TextView)v.findViewById(R.id.estoque_grupo_max);
        estoqueGrupoMin        =  (TextView)v.findViewById(R.id.estoque_grupo_min);
        estoqueGrupoMedDias    =  (TextView)v.findViewById(R.id.estoque_grupo_med_dias);
        estoqueGrupoRep        =  (TextView)v.findViewById(R.id.estoque_grupo_rep);

        estoqueTotalQuant      =  (TextView)v.findViewById(R.id.estoque_total_quant);
        estoqueTotalMed        =  (TextView)v.findViewById(R.id.estoque_total_med);
        estoqueTotalMax        =  (TextView)v.findViewById(R.id.estoque_total_max);
        estoqueTotalMin        =  (TextView)v.findViewById(R.id.estoque_total_min);
        estoqueTotalMedDias    =  (TextView)v.findViewById(R.id.estoque_total_med_dias);
        estoqueTotalRep        =  (TextView)v.findViewById(R.id.estoque_total_rep);

        vendidosUnidadeQuant   =  (TextView)v.findViewById(R.id.vendidos_unidade_quant);
        vendidosUnidadeMed     =  (TextView)v.findViewById(R.id.vendidos_unidade_med);
        vendidosUnidadeMax     =  (TextView)v.findViewById(R.id.vendidos_unidade_max);
        vendidosUnidadeMin     =  (TextView)v.findViewById(R.id.vendidos_unidade_min);
        vendidosUnidadeMedDias =  (TextView)v.findViewById(R.id.vendidos_unidade_med_dias);
        vendidosUnidadeRep     =  (TextView)v.findViewById(R.id.vendidos_unidade_rep);

        vendidosGrupoQuant     =  (TextView)v.findViewById(R.id.vendidos_grupo_quant);
        vendidosGrupoMed       =  (TextView)v.findViewById(R.id.vendidos_grupo_med);
        vendidosGrupoMax       =  (TextView)v.findViewById(R.id.vendidos_grupo_max);
        vendidosGrupoMin       =  (TextView)v.findViewById(R.id.vendidos_grupo_min);
        vendidosGrupoMedDias   =  (TextView)v.findViewById(R.id.vendidos_grupo_med_dias);
        vendidosGrupoRep       =  (TextView)v.findViewById(R.id.vendidos_grupo_rep);

        vendidosTotalQuant     =  (TextView)v.findViewById(R.id.vendidos_total_quant);
        vendidosTotalMed       =  (TextView)v.findViewById(R.id.vendidos_total_med);
        vendidosTotalMax       =  (TextView)v.findViewById(R.id.vendidos_total_max);
        vendidosTotalMin       =  (TextView)v.findViewById(R.id.vendidos_total_min);
        vendidosTotalMedDias   =  (TextView)v.findViewById(R.id.vendidos_total_med_dias);
        vendidosTotalRep       =  (TextView)v.findViewById(R.id.vendidos_total_rep);

        quantPassecarros       =  (TextView)v.findViewById(R.id.quant_passecarros);
        medPassecarros         =  (TextView)v.findViewById(R.id.med_passecarros);
        maxPassecarros         =  (TextView)v.findViewById(R.id.max_passecarros);
        minPassecarros         =  (TextView)v.findViewById(R.id.min_passecarros);
        medDiasPassecarros     =  (TextView)v.findViewById(R.id.med_dias_passecarros);
        repPassecarros         =  (TextView)v.findViewById(R.id.rep_passecarros);

        quantFipe              =  (TextView)v.findViewById(R.id.quant_fipe);
        medFipe                =  (TextView)v.findViewById(R.id.med_fipe);
        maxFipe                =  (TextView)v.findViewById(R.id.max_fipe);
        minFipe                =  (TextView)v.findViewById(R.id.min_fipe);
        medDiasFipe            =  (TextView)v.findViewById(R.id.med_dias_fipe);
        repFipe                =  (TextView)v.findViewById(R.id.rep_fipe);

        return v;
    }

}

