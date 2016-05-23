package br.com.carmaix.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.domain.Avaliacao;

/**
 * Created by fernando on 22/05/16.
 */
public class AvaliacaoAdapter extends RecyclerView.Adapter<AvaliacaoAdapter.AvaliacaoViewHolder> {
    private final Context context;
    private final List<Avaliacao> avaliacaoList;
    private Avaliacao avaliacaoSelecionada;
    public AvaliacaoAdapter(Context context, List<Avaliacao> avaliacaoList) {
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
        avaliacaoSelecionada = avaliacaoList.get(position);
        holder.avaliacaoNome.setText(avaliacaoSelecionada.getNome());
        holder.avaliacaoId.setText(avaliacaoSelecionada.getId().toString());
        /*holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context,avaliacaoSelecionada.getNome()+avaliacaoSelecionada.getId(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return this.avaliacaoList.size();
    }

    public static class AvaliacaoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView avaliacaoId;
        private TextView avaliacaoNome;

        public AvaliacaoViewHolder(View itemView) {
            super(itemView);
            avaliacaoId = (TextView) itemView.findViewById(R.id.avaliacaoId);
            avaliacaoNome = (TextView) itemView.findViewById(R.id.avaliacaoNome);

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
}
