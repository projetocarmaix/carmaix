package br.com.carmaix.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.domain.Avaliacao;
import br.com.carmaix.services.AvaliationReturn;

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
        AvaliacaoViewHolder avaliacaoViewHolder = new AvaliacaoViewHolder(view);
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

    }


    @Override
    public int getItemCount() {
        return this.avaliacaoList.size();
    }

    public static class AvaliacaoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView avaliacaoId;
        private TextView avaliacaoData;
        private TextView avaliacaoMarca;
        private TextView avaliacaoModelo;
        private TextView avaliacaoPlaca;
        private TextView avaliacaoAno;
        private TextView avaliacaoClasse;
        private TextView avaliacaoAvaliacao;
        private TextView avaliacaoNome;

        public AvaliacaoViewHolder(View itemView) {
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

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Selecione uma opção");
            contextMenu.add(0, view.getId(), 0, "Visualizar");//groupId, itemId, order, title
            contextMenu.add(0, view.getId(), 0, "Avalidar");
            contextMenu.add(0, view.getId(), 0, "Revalidar");
        }
    }

    public void addItems(ArrayList<AvaliationReturn> avaliationReturns){

        if (avaliationReturns != null){
            this.avaliacaoList.addAll(avaliationReturns);
        }

        this.notifyDataSetChanged();

    }

}