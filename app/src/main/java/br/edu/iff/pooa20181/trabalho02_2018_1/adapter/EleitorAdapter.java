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

    public EleitorAdapter(List<Eleitor> eventos, Context context,ClickRecyclerViewListener clickRecyclerViewListener) {
                this.eleitores = eventos;
                this.context = context;
                this.clickRecyclerViewListener = clickRecyclerViewListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                                .inflate(R.layout.item_eleitor, parent, false);
                EleitorViewHolder eleitorViewHolder = new EleitorViewHolder(view);

                return eleitorViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        EleitorViewHolder eleitorHolder = (EleitorViewHolder) viewHolder;

        Eleitor eleitor = eleitores.get(position);

        eleitorHolder.nomeEleitor.setText(eleitor.getNome());
        eleitorHolder.nomeMaeEleitor.setText(eleitor.getNomeMae());
        eleitorHolder.dataNascimento.setText(eleitor.getDataNascimento().toString());

        Log.i("------aaaa------", eleitor.getNome());




    }

    @Override
    public int getItemCount() {
        return this.eleitores.size();
    }

    public class EleitorViewHolder extends RecyclerView.ViewHolder{

        private final TextView nomeEleitor;
        private final TextView nomeMaeEleitor;
        private final TextView dataNascimento;

        public EleitorViewHolder(View itemView){
            super(itemView);

            nomeEleitor = (TextView) itemView.findViewById(R.id.tvNomeEleitor);
            nomeMaeEleitor = (TextView) itemView.findViewById(R.id.tvNomeMaeEleitor);
            dataNascimento = (TextView) itemView.findViewById(R.id.tvDataNascimentoEleitor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(eleitores.get(getLayoutPosition()));
                }
            });
        }
    }
}
