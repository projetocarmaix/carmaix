package br.com.carmaix.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliacaoVisualizarActivity;
import br.com.carmaix.activities.AvaliarActivity;
import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.domain.Avaliacao;
import br.com.carmaix.services.AvaliationReturn;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 22/05/16.
 */
public class AvaliacaoAdapter extends RecyclerView.Adapter<AvaliacaoAdapter.AvaliacaoViewHolder> {
    private final Context context;

    private final ArrayList<AvaliationReturn> avaliacaoList;

    public AvaliacaoAdapter(Context context, ArrayList<AvaliationReturn> avaliacaoList) {
        this.context = context;
        this.avaliacaoList = avaliacaoList;
    }

    @Override
    public AvaliacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.avaliacao_adapter,parent,false);
        AvaliacaoViewHolder avaliacaoViewHolder = new AvaliacaoViewHolder(view,context);
        return avaliacaoViewHolder;
    }

    @Override
    public void onBindViewHolder(AvaliacaoViewHolder holder, int position) {

        AvaliationReturn avaliacaoSelecionada = avaliacaoList.get(position);

        holder.avaliacaoNome.setText(avaliacaoSelecionada.getNome());
        holder.avaliacaoId.setText(avaliacaoSelecionada.getId());
        holder.avaliacaoAno.setText(avaliacaoSelecionada.getAno());
        holder.avaliacaoAvaliacao.setText(avaliacaoSelecionada.getValor());
        holder.avaliacaoClasse.setText(avaliacaoSelecionada.getClasse());
        holder.avaliacaoData.setText(avaliacaoSelecionada.getData());
        holder.avaliacaoMarca.setText(avaliacaoSelecionada.getMarca());
        holder.avaliacaoModelo.setText(avaliacaoSelecionada.getModelo());
        holder.avaliacaoPlaca.setText(avaliacaoSelecionada.getPlaca());
        holder.setSituacao(avaliacaoSelecionada.getSituacao());
    }


    @Override
    public int getItemCount() {
        return this.avaliacaoList.size();
    }

    public static class AvaliacaoViewHolder extends RecyclerView.ViewHolder  {
        private TextView avaliacaoId;
        private TextView avaliacaoData;
        private TextView avaliacaoMarca;
        private TextView avaliacaoModelo;
        private TextView avaliacaoPlaca;
        private TextView avaliacaoAno;
        private TextView avaliacaoClasse;
        private TextView avaliacaoAvaliacao;
        private TextView avaliacaoNome;
        private String situacao;

        public AvaliacaoViewHolder(View itemView, final Context context) {
            super(itemView);
            avaliacaoId = (TextView) itemView.findViewById(R.id.avaliacao_id);
            avaliacaoData = (TextView) itemView.findViewById(R.id.avaliacao_data);
            avaliacaoMarca = (TextView) itemView.findViewById(R.id.avaliacao_marca);
            avaliacaoModelo = (TextView) itemView.findViewById(R.id.avaliacao_modelo);
            avaliacaoNome = (TextView) itemView.findViewById(R.id.avaliacao_nome);
            avaliacaoPlaca = (TextView) itemView.findViewById(R.id.avaliacao_placa);
            avaliacaoAno = (TextView) itemView.findViewById(R.id.avaliacao_ano);
            avaliacaoClasse = (TextView) itemView.findViewById(R.id.avaliacao_classe);
            avaliacaoAvaliacao = (TextView) itemView.findViewById(R.id.avaliacao_avaliacao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final TextView avaliacaoId = (TextView)view.findViewById(R.id.avaliacao_id);
                    TextView avaliacaoMarca = (TextView)view.findViewById(R.id.avaliacao_marca);
                    TextView avaliacaoModelo = (TextView)view.findViewById(R.id.avaliacao_modelo);
                    TextView avaliacaoPlaca = (TextView)view.findViewById(R.id.avaliacao_placa);
                    String situacao = getSituacao();

                    AlertDialog.Builder alerBuilder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View options = inflater.inflate(R.layout.dialog_options,null);

                    TextView optionAvaliacaoModelo = (TextView)options.findViewById(R.id.dialog_modelo);
                    optionAvaliacaoModelo.setText(String.format(context.getResources().getString(R.string.modelo), avaliacaoModelo.getText()));

                    TextView optionAvaliacaoMarca = (TextView)options.findViewById(R.id.dialog_marca);
                    optionAvaliacaoMarca.setText(String.format(context.getResources().getString(R.string.marca), avaliacaoMarca.getText()));

                    TextView optionAvaliacao = (TextView)options.findViewById(R.id.dialog_avaliacao);
                    optionAvaliacao.setText(String.format(context.getResources().getString(R.string.avaliacao), avaliacaoId.getText()));

                    TextView optionPlaca = (TextView)options.findViewById(R.id.dialog_placa);
                    optionPlaca.setText(String.format(context.getResources().getString(R.string.placa), avaliacaoPlaca.getText()));


                    TextView revalidar = (TextView)options.findViewById(R.id.dialog_revalidar);
                    if(exibeRevalidar(context)) {
                        revalidar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context, AvaliarActivity.class);
                                intent.putExtra("avaliacaoId",avaliacaoId.getText());
                                intent.putExtra("action",Constants.ACTION_REVALIDAR);
                                context.startActivity(intent);
                            }
                        });
                    }else {
                        revalidar.setVisibility(View.GONE);
                    }

                    TextView avaliar = (TextView) options.findViewById(R.id.dialog_avaliar);
                    if(situacao.equals(Constants.SITUACAO_NAO_AVALIADO)) {
                        avaliar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context, AvaliarActivity.class);
                                intent.putExtra("avaliacaoId",avaliacaoId.getText());
                                intent.putExtra("action",Constants.ACTION_AVALIAR);
                                context.startActivity(intent);
                            }
                        });
                    }else {
                        avaliar.setVisibility(View.GONE);
                    }

                    TextView visualizar = (TextView)options.findViewById(R.id.dialog_visualizar);
                    visualizar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, AvaliacaoVisualizarActivity.class);
                            intent.putExtra("avaliacaoId",avaliacaoId.getText());
                            context.startActivity(intent);
                        }
                    });

                    LinearLayout optionsGroup = (LinearLayout)options.findViewById(R.id.options_group);
                    this.setbackgroundColor(optionsGroup);
                    alerBuilder.setView(options);
                    alerBuilder.create().show();
                }

                private void setbackgroundColor(LinearLayout optionsGroup) {
                    String situacao = getSituacao();
                    if(situacao.equals(Constants.SITUACAO_NAO_AVALIADO)) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_cinza));
                    }else if(situacao.equals(Constants.SITUACAO_AVALIADO)) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_vermelha));
                    }else if(situacao.equals(Constants.SITUACAO_PROPOSTA)) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_laranja));
                    }else if(situacao.equals(Constants.SITUACAO_ESTOQUE)) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_verde));
                    }else if(situacao.equals(Constants.SITUACAO_VENDIDO)) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_roxo));
                    }
                }
            });

        }

        public void setSituacao(String situacao) {
            this.situacao = situacao;
        }

        public String getSituacao() {
            return situacao;
        }

        private Boolean exibeRevalidar(Context context) {
            String situacao = getSituacao();
            ApplicationCarmaix application = (ApplicationCarmaix) context.getApplicationContext();
            return (situacao.equals(Constants.SITUACAO_AVALIADO) && (application.getLoginTable().getUserRevalida()).equals("1"));
        }
    }

    public void addItems(ArrayList<AvaliationReturn> avaliationReturns){

        if (avaliationReturns != null){
            this.avaliacaoList.addAll(avaliationReturns);
        }

        this.notifyDataSetChanged();

    }



}