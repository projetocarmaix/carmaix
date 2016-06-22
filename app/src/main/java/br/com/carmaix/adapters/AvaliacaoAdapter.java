package br.com.carmaix.adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.domain.Avaliacao;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 22/05/16.
 */
public class AvaliacaoAdapter extends RecyclerView.Adapter<AvaliacaoAdapter.AvaliacaoViewHolder> {
    private final Context context;
    private final List<Avaliacao> avaliacaoList;
    private Avaliacao avaliacaoSelecionada;
    private int tabIndex;
    public AvaliacaoAdapter(Context context, List<Avaliacao> avaliacaoList, int tabIndex) {
        this.context = context;
        this.avaliacaoList = avaliacaoList;
        this.tabIndex = tabIndex;
    }

    @Override
    public AvaliacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.avaliacao_adapter,parent,false);
        AvaliacaoViewHolder avaliacaoViewHolder = new AvaliacaoViewHolder(view,context, tabIndex);
        return avaliacaoViewHolder;
    }

    @Override
    public void onBindViewHolder(AvaliacaoViewHolder holder, int position) {
        avaliacaoSelecionada = avaliacaoList.get(position);
        holder.avaliacaoNome.setText(avaliacaoSelecionada.getAvaliacaoNome());
        holder.avaliacaoId.setText(avaliacaoSelecionada.getAvaliacaoId());
        holder.avaliacaoAno.setText(avaliacaoSelecionada.getAvaliacaoAno());
        holder.avaliacaoAvaliacao.setText(avaliacaoSelecionada.getAvaliacaoAvaliacao());
        holder.avaliacaoClasse.setText(avaliacaoSelecionada.getAvaliacaoClasse());
        holder.avaliacaoData.setText(avaliacaoSelecionada.getAvaliacaoData());
        holder.avaliacaoMarca.setText(avaliacaoSelecionada.getAvaliacaoMarca());
        holder.avaliacaoModelo.setText(avaliacaoSelecionada.getAvaliacaoModelo());
        holder.avaliacaoPlaca.setText(avaliacaoSelecionada.getAvaliacaoPlaca());
    }


    @Override
    public int getItemCount() {
        return this.avaliacaoList.size();
    }

    public Context getContext() {
        return context;
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

        public AvaliacaoViewHolder(View itemView, final Context context, final int tabIndex) {
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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    TextView avaliacaoId = (TextView)view.findViewById(R.id.avaliacao_id);
                    TextView avaliacaoMarca = (TextView)view.findViewById(R.id.avaliacao_marca);
                    TextView avaliacaoModelo = (TextView)view.findViewById(R.id.avaliacao_modelo);
                    TextView avaliacaoPlaca = (TextView)view.findViewById(R.id.avaliacao_placa);

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
                    revalidar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context,"revalidar",Toast.LENGTH_SHORT).show();
                        }
                    });

                    TextView avaliar = (TextView)options.findViewById(R.id.dialog_avaliar);
                    avaliar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context,"avaliar",Toast.LENGTH_SHORT).show();
                        }
                    });

                    TextView visualizar = (TextView)options.findViewById(R.id.dialog_visualizar);
                    visualizar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context,"visualizar",Toast.LENGTH_SHORT).show();
                        }
                    });

                    LinearLayout optionsGroup = (LinearLayout)options.findViewById(R.id.options_group);
                    this.setbackgroundColor(tabIndex, optionsGroup);
                    alerBuilder.setView(options);
                    alerBuilder.create().show();

                    return false;
                }

                private void setbackgroundColor(int tabIndex, LinearLayout optionsGroup) {
                    if(tabIndex == Constants.TAB_CINZA) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_cinza));
                    }else if(tabIndex == Constants.TAB_VERMELHA) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_vermelha));
                    }else if(tabIndex == Constants.TAB_LARANJA) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_laranja));
                    }else if(tabIndex == Constants.TAB_VERDE) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_verde));
                    }else if(tabIndex == Constants.TAB_ROXO) {
                        optionsGroup.setBackgroundColor(context.getResources().getColor(R.color.tab_roxo));
                    }
                }
            });

        }
    }
}