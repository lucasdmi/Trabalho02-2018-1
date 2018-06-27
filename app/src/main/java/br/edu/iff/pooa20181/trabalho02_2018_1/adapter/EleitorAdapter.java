package br.edu.iff.pooa20181.trabalho02_2018_1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.EventListener;
import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;

public class EleitorAdapter extends RecyclerView.Adapter{
    private List<Eleitor> eleitores;
    private Context context;
    private static  ClickRecyclerViewListener clickRecyclerViewListener;

    public EleitorAdapter(List<Eleitor> eleitores, Context context,ClickRecyclerViewListener clickRecyclerViewListener) {
                this.eleitores = eleitores;
                this.context = context;
                this.clickRecyclerViewListener = clickRecyclerViewListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                                .inflate(R.layout.item_eleitor_cv, parent, false);
                EleitorViewHolder eleitorViewHolder = new EleitorViewHolder(view);
                return eleitorViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        EleitorViewHolder eleitorHolder = (EleitorViewHolder) viewHolder;

        Eleitor eleitor = this.eleitores.get(position);

        eleitorHolder.nomeEleitor.setText(eleitor.getNome());
        eleitorHolder.numeroTitulo.setText(eleitor.getNumeroTitulo());
        eleitorHolder.zona.setText(eleitor.getZona());
        eleitorHolder.secao.setText(eleitor.getSecao());






    }

    @Override
    public int getItemCount() {
        return eleitores.size();
    }

    public class EleitorViewHolder extends RecyclerView.ViewHolder{

        private final TextView nomeEleitor;
        private final TextView numeroTitulo;
        private final TextView zona;
        private final TextView secao;

        public EleitorViewHolder(View itemView){
            super(itemView);

            nomeEleitor = (TextView) itemView.findViewById(R.id.tvNomeEleitor);
            numeroTitulo = (TextView) itemView.findViewById(R.id.tvNumeroTitulo);
            zona = (TextView) itemView.findViewById(R.id.tvZona);
            secao = (TextView) itemView.findViewById(R.id.tvSecao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(eleitores.get(getLayoutPosition()));
                }
            });
        }
    }
}
